package com.logindemo.demo.pdking.logindemo;

import android.app.Application;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;

/**
 * Created on 18/9/25 10:42
 *
 * @author zhangchaozhou
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * 在这里绑定申请Application ID
         * */
        Bmob.initialize(this,"4527189a38e9393bebbaaf3940982580");

    }
}
