package com.daggerdemo.demo.pdking.daggerdemo;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class App extends Application {

    ActivityComponent activityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        activityComponent = DaggerActivityComponent.builder().personComponent
                (DaggerPersonComponent.builder().build()).build();
        ;

//        Log.d("Lpp", "onCreate: App" + getApplicationContext());
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
