package com.butterknife.demo.pdking.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Produce;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {

    private TextView tv_text;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private OttoBus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        bus = OttoBus.getInstance();
//        bus.register(this);
        initView();
        buttonListener();
    }

//    @Produce
//    public OttoBusMsg setInitContent() {
//        return new OttoBusMsg("成功接收EventBus事件By Produce");
//    }

    private void buttonListener() {
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("成功接收EventBus事件"));
                finish();
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new MessageEvent("成功接收黏性事件"));
                finish();
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OttoBus.getInstance().post(new OttoBusMsg("成功接收Otto事件"));
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        bus.register(this);
    }

    private void initView() {
        tv_text = findViewById(R.id.tv_textView);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
    }
}
