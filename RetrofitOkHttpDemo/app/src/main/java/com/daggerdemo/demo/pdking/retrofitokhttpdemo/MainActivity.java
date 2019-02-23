package com.daggerdemo.demo.pdking.retrofitokhttpdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tv_msg;

    private Button btn_msg;

    private Map<String, String> map;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == R.id.tv_msg) {
                tv_msg.setText((String) msg.getData().get("msg"));
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_msg = findViewById(R.id.tv_msg);

        btn_msg = findViewById(R.id.btn_sendMsg);

        map = new HashMap<>();
        map.put("q", "a");
        map.put("le", "eng");
        map.put("num", "15");
        map.put("ver", "2.0");
        map.put("doctype", "json");
        map.put("keyfrom", "mdict.7.2.0.android");
        map.put("model", "honor");
        map.put("mid", "5.6.1");
        map.put("imei", "659135764921685");
        map.put("vendor", "wandoujia");
        map.put("screen", "1080x1800");
        map.put("ssid", "superman");
        map.put("abtest", "2");

        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<MsgModel> msgCall = RetrofitUtils.getMsgCall(map);
                msgCall.enqueue(new Callback<MsgModel>() {
                    @Override
                    public void onResponse(Call<MsgModel> call, Response<MsgModel> response) {
                        Message msg = new Message();
                        msg.what = R.id.tv_msg;
                        Bundle bundle = new Bundle();
                        Log.d("Lpp", "onResponse: " + response);
                        bundle.putString("msg", "" + response.body().data.entries);
                        msg.setData(bundle);
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onFailure(Call<MsgModel> call, Throwable t) {
                        Log.d("Lpp", "onFailure: " + t.getMessage());
                    }
                });
            }
        });

    }
}
