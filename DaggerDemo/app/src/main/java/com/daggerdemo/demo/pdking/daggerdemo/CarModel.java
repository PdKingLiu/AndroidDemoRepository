package com.daggerdemo.demo.pdking.daggerdemo;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class CarModel {

    @Provides
    @ApplicationScope
//    @Named("BigCar")
    @BigCarAnn
    public Car provideBigCar() {
        return new BigCar();
    }

    @Provides
    @ApplicationScope
    @SmallCarAnn
//    @Named("SmallCar")
    public Car provideSmallCar() {
        return new SmallCar();
    }

}
