package com.competition.pdking.modulea;

import android.app.Application;
import android.util.Log;

/**
 * @author liupeidong
 * Created on 2019/5/30 20:52
 */
public class AppA extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Lpp", "onCreate: AppA");
    }
}
