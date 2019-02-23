package com.daggerdemo.demo.pdking.daggerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.Lazy;

public class SecondActivity extends AppCompatActivity {


    @Inject
    Gson gson;

    String TAG = "Lpp";

    @Inject
    @BigCarAnn
    Car mCar;

    @Inject
    @SmallCarAnn
    Car mSmallCar;

    @Inject
    Person mPerson;

    @Inject
    Lazy<Person> personLazy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        Log.d("Lpp", "onCreate: Second"+getApplicationContext());
//        Log.d("Lpp", "onCreate: Second"+getApplication());
        App.get(SecondActivity.this).getActivityComponent().inject(this);
        Log.d(TAG, "onCreate: SecondActivity " + gson.hashCode());
        Log.d(TAG, "onCreate: Car" + mCar);
        Log.d(TAG, "onCreate: SmallCar" + mSmallCar);

        Log.d(TAG, "onCreate: Person" + mPerson);
        Log.d(TAG, "onCreate: Person1" + personLazy.get());
        Log.d(TAG, "onCreate: Person2" + personLazy.get());
        Log.d(TAG, "onCreate: Person3" + personLazy.get());
        Log.d(TAG, "onCreate: Person4" + personLazy.get());

    }
}
