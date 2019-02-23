package com.example.recyclerviewitemdemo.anotherpattern;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.recyclerviewitemdemo.R;
import com.example.recyclerviewitemdemo.anotherpattern.adapter.MyRecyclerViewAdapter;
import com.example.recyclerviewitemdemo.anotherpattern.bean.BeanOne;
import com.example.recyclerviewitemdemo.anotherpattern.bean.BeanThree;
import com.example.recyclerviewitemdemo.anotherpattern.bean.BeanTwo;
import com.example.recyclerviewitemdemo.anotherpattern.decorateclass.Visitable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnotherActivity extends AppCompatActivity {


    private List<Visitable> visitableList;

    private RecyclerView mRecyclerView;

    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    myRecyclerViewAdapter.notifyDataSetChanged();
                    break;
            }
            return true;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        initView();

        init();

    }


    private void init() {

        Random random = new Random();

        visitableList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            int j = random.nextInt(3) + 1;
            Log.d("Lpp", "+" + j);
            switch (j) {
                case 1:
                    visitableList.add(new BeanOne());
                    break;
                case 2:
                    visitableList.add(new BeanTwo());
                    break;
                case 3:
                    visitableList.add(new BeanThree());
                    break;
            }
        }

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(visitableList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        Message message = new Message();
        message.what = 1;
        mHandler.sendMessage(message);
    }

    private void initView() {

        mRecyclerView = findViewById(R.id.another_rv);

    }

}
