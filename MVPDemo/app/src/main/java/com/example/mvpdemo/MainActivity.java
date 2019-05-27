package com.example.mvpdemo;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MvpView {

    ProgressDialog progressDialog;
    TextView text;
    MvpPresenter mvpPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitleMode(getWindow());
        text = findViewById(R.id.text);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        mvpPresenter = new MvpPresenter(this);

    }

    public void setTitleMode(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = window.getDecorView();
            /*int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;*/
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            window.setNavigationBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void getData(View view) {
        mvpPresenter.getData("normal");
    }

    public void getDataForFailure(View view) {
        mvpPresenter.getData("failure");
    }

    public void getDataForError(View view) {
        mvpPresenter.getData("error");
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hidLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showData(String data) {
        text.setText(data);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        text.setText(msg);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
        text.setText("网络异常");
    }
}
