package com.competition.pdking.modulea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ModuleAActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_a);
    }

    public void goApp(View view) throws ClassNotFoundException {
        Intent intent = new Intent(this, Class.forName("com.competition.pdking.modularizationdemo" +
                ".MainActivity"));
        startActivity(intent);
    }

    public void goB(View view) throws ClassNotFoundException {
        Intent intent = new Intent(this, Class.forName("com.competition.pdking.moduleb.ModuleBActivity"));
        startActivity(intent);
    }

}
