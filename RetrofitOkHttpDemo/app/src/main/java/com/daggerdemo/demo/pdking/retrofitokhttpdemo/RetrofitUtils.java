package com.daggerdemo.demo.pdking.retrofitokhttpdemo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static String baseUrl = "http://dict.youdao.com/";

    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static MsgService msgService = retrofit.create(MsgService.class);

    public static Call<MsgModel> getMsgCall(Map<String,String> q) {
        return msgService.getMsg(q);
    }

}
