package com.example.viewexplore;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * @author liupeidong
 * Created on 2019/4/11 10:20
 */
public class App extends Application {

    private String TAG = "Lpp";
    
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {

            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.d("Lpp", "onViewInitFinished: " + b);
            }
        });
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        Log.d("Lpp", "onTerminate");
        super.onTerminate();
    }


    @Override
    public void onLowMemory() {
        // 低内存的时候执行
        Log.d(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 程序在内存清理的时候执行（回收内存）
        // HOME键退出应用程序、长按MENU键，打开Recent TASK都会执行
        Log.d(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

}
