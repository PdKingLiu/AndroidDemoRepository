package com.example.viewexplore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SlideView mSlideView;

    private Button button;

    private ListView lv_one;

    private ListView lv_two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSlideView = findViewById(R.id.my_view);

        button = findViewById(R.id.button_1);

        lv_one = findViewById(R.id.lv_one);

        lv_two = findViewById(R.id.lv_two);

        initList();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               mSlideView.setAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
                // 通过动画实现滑动

                mSlideView.smoothScrollTo(-500, 0); // 通过Scroller滑动

            }
        });


    }

    private void initList() {
        String[] strings1 = new String[20];
        String[] strings2 = new String[30];
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            strings1[i] = String.valueOf(random.nextInt());
        }
        for (int i = 0; i < 30; i++) {
            strings2[i] = String.valueOf(random.nextInt());
        }
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout
                .simple_list_item_1, strings1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout
                .simple_list_item_1, strings2);
        lv_one.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
        lv_two.setAdapter(adapter2);
    }
}
