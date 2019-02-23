package com.example.recyclerviewitemdemo.anotherpattern.bean;

import com.example.recyclerviewitemdemo.anotherpattern.decorateclass.Visitable;
import com.example.recyclerviewitemdemo.anotherpattern.factory.TypeFactory;

public class BeanOne implements Visitable{
    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
