package com.example.viewexplore;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * @author liupeidong
 * Created on 2019/5/5 21:01
 */
public class SocketThread extends Thread {

    private static final long HEART_BEAT_RATE = 5 * 1000;//每隔15秒进行一次对长连接的心跳检测
    private static final String WEBSOCKET_HOST_AND_PORT = "ws://www.shidongxuan" +
            ".top:8080/smartMeeting_Web/socket/1004";//可替换为自己的主机名和端口号
    private WebSocket mWebSocket;
    private Handler mHandler = new Handler();

    private long sendTime = 0L;

    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            if (System.currentTimeMillis() - sendTime >= HEART_BEAT_RATE) {
                boolean isSuccess = mWebSocket.send("测试");//发送一个空消息给服务器，通过发送消息的成功失败来判断长连接的连接状态
                Log.d(TAG, "run: isSuccess" + isSuccess);
                if (!isSuccess) {//长连接已断开
                    mHandler.removeCallbacks(heartBeatRunnable);
                    mWebSocket.cancel();//取消掉以前的长连接
                    new SocketThread().start();//创建一个新的连接
                } else {//长连接处于连接状态

                }
                sendTime = System.currentTimeMillis();
            }
            mHandler.postDelayed(this, HEART_BEAT_RATE);//每隔一定的时间，对长连接进行一次心跳检测
        }
    };

    @Override
    public void run() {
        initSocket();
    }

    private String TAG = "Lpp";

    private void initSocket() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(WEBSOCKET_HOST_AND_PORT).build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                mWebSocket = webSocket;
                try {
                    Log.d(TAG, "onOpen: " + response.body().string());
                } catch (IOException e) {
                    Log.d(TAG, "IOException: ");
                    e.printStackTrace();
                }
                Log.d(TAG, "onOpen: 2");
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                Log.d(TAG, "onMessage: " + text);
            }

            @Override
            public void onMessage(WebSocket webSocket, ByteString bytes) {
                Log.d(TAG, "onMessage: " + bytes.toString());
            }

            @Override
            public void onClosing(WebSocket webSocket, int code, String reason) {
                Log.d(TAG, "onClosing: ");
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                Log.d(TAG, "onClosed: ");
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                try {
                    Log.d(TAG, "onFailure: " + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        client.dispatcher().executorService().shutdown();
        mHandler.postDelayed(heartBeatRunnable, HEART_BEAT_RATE);//开启心跳检测
    }
}
