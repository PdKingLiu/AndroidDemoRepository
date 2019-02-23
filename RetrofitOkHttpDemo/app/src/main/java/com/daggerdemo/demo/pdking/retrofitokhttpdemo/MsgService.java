package com.daggerdemo.demo.pdking.retrofitokhttpdemo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MsgService {

    @GET("suggest?")
    Call<MsgModel> getMsg(@QueryMap Map<String,String > map);

}
