package com.competition.pdking.moduleb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/module_b/b")
public class ModuleBActivity extends AppCompatActivity {

    @Autowired
    public String isReceive = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_b);
        ARouter.getInstance().inject(this);
        Toast.makeText(this, isReceive, Toast.LENGTH_SHORT).show();
    }

    public void goApp(View view) throws ClassNotFoundException {
        /*Intent intent = new Intent(this, Class.forName("com.competition.pdking.modularizationdemo" +
                ".MainActivity"));
        startActivity(intent);*/
        ARouter.getInstance().build("/app_/app").withString("isReceive","由B到了APP").navigation(this);
    }

    public void goA(View view) throws ClassNotFoundException {
        /*Intent intent = new Intent(this, Class.forName("com.competition.pdking.modulea.ModuleAActivity"));
        startActivity(intent);*/
        ARouter.getInstance().build("/module_a/a").withString("isReceive","由B到了A").navigation(this);
    }

}
