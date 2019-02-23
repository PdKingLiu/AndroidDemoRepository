package com.daggerdemo.demo.pdking.daggerdemo;

import javax.inject.Inject;

public class Person {

    @Inject

    public Person() {

    }

    public String returnString() {
        return "这是一个不该停留太久的地方";
    }
}
