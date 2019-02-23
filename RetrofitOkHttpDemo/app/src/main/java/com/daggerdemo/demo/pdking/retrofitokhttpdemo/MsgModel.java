package com.daggerdemo.demo.pdking.retrofitokhttpdemo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MsgModel {

    @SerializedName("result")
    public ResultBean result;
    @SerializedName("data")
    public DataBean data;

    public static class ResultBean {
        @SerializedName("code")
        public int code;
        @SerializedName("msg")
        public String msg;
    }

    public static class DataBean {
        @SerializedName("query")
        public String query;
        @SerializedName("language")
        public String language;
        @SerializedName("entries")
        public List<EntriesBean> entries;

        public static class EntriesBean {
            @SerializedName("explain")
            public String explain;
            @SerializedName("entry")
            public String entry;
        }
    }
}
