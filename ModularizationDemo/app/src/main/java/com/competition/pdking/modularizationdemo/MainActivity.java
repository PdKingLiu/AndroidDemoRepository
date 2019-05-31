package com.competition.pdking.modularizationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;


@Route(path = "/app_/app")
public class MainActivity extends AppCompatActivity {

    @Autowired
    String isReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ARouter.getInstance().inject(this);
        if (isReceive != null) {
            Toast.makeText(this, isReceive, Toast.LENGTH_SHORT).show();
        }
    }

    public void goA(View view) throws ClassNotFoundException {
        /*Class c = Class.forName("com.competition.pdking.modulea.ModuleAActivity");
        Intent intent = new Intent(this, c);
        startActivity(intent);*/
        ARouter.getInstance().build("/module_a/a").withString("isReceive", "由APP到了A").navigation(this);
    }

    public void goB(View view) {
        /*Intent intent = new Intent(this, ModuleBActivity.class);
        startActivity(intent);*/
        ARouter.getInstance().build("/module_b/b").withString("isReceive", "由APP到了B").navigation(this);
    }

}
