package com.competition.pdking.modularizationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.competition.pdking.moduleb.ModuleBActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goA(View view) throws ClassNotFoundException {
        Class c = Class.forName("com.competition.pdking.modulea.ModuleAActivity");
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    public void goB(View view) {
        Intent intent = new Intent(this, ModuleBActivity.class);
        startActivity(intent);
    }

}
