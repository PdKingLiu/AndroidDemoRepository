package com.competition.pdking.modulea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

@Route(path = "/module_a/a")
public class ModuleAActivity extends AppCompatActivity {

    @Autowired
    public String isReceive = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_a);
        ARouter.getInstance().inject(this);
        Toast.makeText(this, isReceive, Toast.LENGTH_SHORT).show();
    }

    public void goApp(View view) throws ClassNotFoundException {
        /*Intent intent = new Intent(this, Class.forName("com.competition.pdking.modularizationdemo" +
                ".MainActivity"));
        startActivity(intent);*/
        ARouter.getInstance().build("/app_/app").withString("isReceive","由A到了APP").navigation(this);
    }

    public void goB(View view) throws ClassNotFoundException {
        /*Intent intent = new Intent(this, Class.forName("com.competition.pdking.moduleb" +
                ".ModuleBActivity"));
        startActivity(intent);*/
        ARouter.getInstance().build("/module_b/b").withString("isReceive","由A到了B").navigation(this);
    }

}
