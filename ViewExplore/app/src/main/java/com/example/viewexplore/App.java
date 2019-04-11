package com.example.viewexplore;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

/**
 * @author liupeidong
 * Created on 2019/4/11 10:20
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
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
}
