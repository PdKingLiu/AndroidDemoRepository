package com.daggerdemo.demo.pdking.daggerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

public class MainActivity extends AppCompatActivity {

    private String TAG = "Lpp";

    //    @Named("BigCar")
    @Inject
    @SmallCarAnn
    Car mCar;


    @Inject
    Gson mGson;

    @Inject
    Gson mGson2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btn_1);

//        DaggerMainActivityComponent.create().inject(this);
//        myClass.work();
//        DaggerMainActivity_MainActivityComponent.create().inject(this);
//        mCar.method(this);

        App.get(this).getActivityComponent().inject(this);
        Log.d(TAG, "onCreate: Cat" + mCar);
        Log.d(TAG, "onCreate: Gson"+mGson.hashCode()+"Gson2"+mGson2.hashCode());


//        Log.d(TAG, "onCreate: MainActivity"+getApplicationContext());
//        Log.d(TAG, "onCreate: MainActivity"+getApplication());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });
    }


//    @Singleton
//    @Component(modules = {GsonModel.class, CarModel.class})
//    public interface MainActivityComponent {
//        void inject(MainActivity mainActivity);
//    }

}
