package com.example.mvpdemo;

public interface Callbak {

     void onSuccess(String data);

     void onFailure(String data);

     void onError(String data);

     void onComplete();
}
