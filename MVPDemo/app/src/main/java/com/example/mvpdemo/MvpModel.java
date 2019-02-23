package com.example.mvpdemo;

import android.os.Handler;

public class MvpModel {
    // 获取网络数据
    public static void getData(final String param, final Callbak callbak) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (param) {
                    case "normal":
                        callbak.onSuccess("成功");
                        break;
                    case "failure":
                        callbak.onFailure("失败");
                        break;
                    case "error":
                        callbak.onFailure("异常");
                        break;
                }
                callbak.onComplete();

            }
        }, 2000);
    }
}
