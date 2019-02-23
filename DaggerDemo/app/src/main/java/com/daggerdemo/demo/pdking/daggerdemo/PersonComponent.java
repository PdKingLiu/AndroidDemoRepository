package com.daggerdemo.demo.pdking.daggerdemo;

import dagger.Component;

@Component(modules = PersonModel.class)
public interface PersonComponent {
    Person get();
}
