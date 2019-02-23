package com.daggerdemo.demo.pdking.daggerdemo;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModel {

    @Provides
    @ApplicationScope
//    @Singleton
    public Gson provideGson() {
        return new Gson();
}


}
