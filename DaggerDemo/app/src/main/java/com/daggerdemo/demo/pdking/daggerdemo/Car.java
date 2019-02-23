package com.daggerdemo.demo.pdking.daggerdemo;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

public class Car {

//    @Inject
//    public Car() {
//    }

    public void method(Context context) {
        Toast.makeText(context, "method", Toast.LENGTH_SHORT).show();
    }
}
