package com.example.recyclerviewitemdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.recyclerviewitemdemo.anotherpattern.AnotherActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    private List<MyData> myDataList;

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
        setContentView(R.layout.activity_main);

        initView();

        init();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.another:
                Intent intent = new Intent(this, AnotherActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void init() {
        myDataList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            MyData myData = new MyData(random.nextInt(3) + 1);
            myDataList.add(myData);
        }
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(myDataList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(myRecyclerViewAdapter);
        Message message = new Message();
        message.what = 1;
        mHandler.sendMessage(message);
    }

    private void initView() {

        mRecyclerView = findViewById(R.id.rv);

    }
}
