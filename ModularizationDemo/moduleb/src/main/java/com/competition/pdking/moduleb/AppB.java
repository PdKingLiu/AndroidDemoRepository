package com.competition.pdking.moduleb;

import android.app.Application;
import android.util.Log;

/**
 * @author liupeidong
 * Created on 2019/5/30 21:23
 */
public class AppB extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Lpp", "onCreate: AppB");
    }
}
