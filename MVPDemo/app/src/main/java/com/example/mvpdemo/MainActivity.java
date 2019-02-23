package com.example.mvpdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        text = findViewById(R.id.text);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading");
        mvpPresenter = new MvpPresenter(this);

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
