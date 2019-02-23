package com.butterknife.demo.pdking.butterknife;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.btn_1)
    Button mButton;
    // 绑定控件     需要注意的是不能用static private等修饰

    // 绑定资源

//    @BindString()
//    @BindArray()
//    @BindBool()
//    @BindColor()
//    @BindDimen()
//    ···等等


    // 绑定监听
    @OnClick(R.id.btn_1)    //还有OnClick绑定长按事件
    void btn_1_Listener() {
        Toast.makeText(this, "Bind", Toast.LENGTH_SHORT).show();
    }

    // 监听EditText 的变化
//    @OnTextChanged(value = R.id.ed_,callback = OnTextChanged.Callback.BEFORE_TEXT_CHANGED)
//    @OnTextChanged(value = R.id.ed_,callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
//    @OnTextChanged(value = R.id.ed_,callback = OnTextChanged.Callback.TEXT_CHANGED)


    // OnTouch事件
//     @OnTouch(R.id.btn_1)

//    @OnItemClick          监听ListView

    //  在Fragment和Adapter中使用ButterKnife
    // 在OnCreateView 中构建完View 使用        ButterKnife.bind(this, view);

    // 在ViewHolder里面
//    class ViewHolder {
//        @BindView(R.id.btn_1)
//        Button button;
//        public ViewHolder(View view) {
//            ButterKnife.bind(this,view );
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
}
