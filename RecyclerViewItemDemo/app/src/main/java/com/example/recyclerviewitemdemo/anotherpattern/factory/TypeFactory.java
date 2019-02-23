package com.example.recyclerviewitemdemo.anotherpattern.factory;

import android.view.View;

import com.example.recyclerviewitemdemo.anotherpattern.bean.BeanOne;
import com.example.recyclerviewitemdemo.anotherpattern.bean.BeanThree;
import com.example.recyclerviewitemdemo.anotherpattern.bean.BeanTwo;
import com.example.recyclerviewitemdemo.anotherpattern.viewholder.BaseViewHolder;

public interface TypeFactory {


    int type(BeanOne beanOne);
    int type(BeanTwo beanTne);
    int type(BeanThree beanThree);

    BaseViewHolder createViewHolder(int type, View view);

}
