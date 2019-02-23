package com.example.mvpdemo;

public interface MvpView {

    void showLoading();

    void hidLoading();

    void showData(String data);

    void showFailureMessage(String msg);

    void showErrorMessage();
}
