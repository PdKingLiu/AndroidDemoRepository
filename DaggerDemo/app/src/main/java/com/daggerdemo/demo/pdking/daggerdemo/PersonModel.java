package com.daggerdemo.demo.pdking.daggerdemo;



import dagger.Module;
import dagger.Provides;

@Module
public class PersonModel {

    @Provides
    public Person get() {
        return new Person();
    }

}
