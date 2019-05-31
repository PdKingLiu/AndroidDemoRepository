package com.competition.pdking.moduleb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ModuleBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_b);
    }

    public void goApp(View view) throws ClassNotFoundException {
        Intent intent = new Intent(this, Class.forName("com.competition.pdking.modularizationdemo" +
                ".MainActivity"));
        startActivity(intent);
    }

    public void goA(View view) throws ClassNotFoundException {
        Intent intent = new Intent(this, Class.forName("com.competition.pdking.modulea.ModuleAActivity"));
        startActivity(intent);
    }

}
