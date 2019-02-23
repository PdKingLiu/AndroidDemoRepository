package com.daggerdemo.demo.pdking.daggerdemo;

import dagger.Component;

@ApplicationScope
@Component(modules = {GsonModel.class, CarModel.class},dependencies = PersonComponent.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(SecondActivity secondActivity);

}
