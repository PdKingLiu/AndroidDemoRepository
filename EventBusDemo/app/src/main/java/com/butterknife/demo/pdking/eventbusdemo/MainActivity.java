package com.butterknife.demo.pdking.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Bus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView tv_text;
    private Button btn_1;
    private Button btn_2;
    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bus = OttoBus.getInstance();
        bus.register(MainActivity.this);
        initView();
        buttonListener();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        tv_text.setText(messageEvent.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onStickyEvent(MessageEvent messageEvent) {
        tv_text.setText(messageEvent.getMsg());
    }

    @Subscribe
    public void onOttoEvent(OttoBusMsg ottoBusMsg) {
        Log.d("Lpp", "onOttoEvent: ");
        tv_text.setText(ottoBusMsg.getMsg());
    }

    private void buttonListener() {
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().register(MainActivity.this);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        bus.unregister(MainActivity.this);
    }

    private void initView() {
        tv_text = findViewById(R.id.tv_textView);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
    }
}
