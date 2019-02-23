package com.example.recyclerviewitemdemo.anotherpattern.factory;

import android.view.View;

import com.example.recyclerviewitemdemo.R;
import com.example.recyclerviewitemdemo.anotherpattern.bean.BeanOne;
import com.example.recyclerviewitemdemo.anotherpattern.bean.BeanThree;
import com.example.recyclerviewitemdemo.anotherpattern.bean.BeanTwo;
import com.example.recyclerviewitemdemo.anotherpattern.viewholder.BaseViewHolder;
import com.example.recyclerviewitemdemo.anotherpattern.viewholder.ViewHolderOne;
import com.example.recyclerviewitemdemo.anotherpattern.viewholder.ViewHolderThree;
import com.example.recyclerviewitemdemo.anotherpattern.viewholder.ViewHolderTwo;

public class ItemTypeFactory implements TypeFactory {

    public static final int LAYOUT_ONE = R.layout.layout_one;

    public static final int LAYOUT_TWO = R.layout.layout_two;

    public static final int LAYOUT_THREE = R.layout.layout_three;

    @Override
    public int type(BeanOne beanOne) {
        return LAYOUT_ONE;
    }

    @Override
    public int type(BeanTwo beanTne) {
        return LAYOUT_TWO;
    }

    @Override
    public int type(BeanThree beanThree) {
        return LAYOUT_THREE;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View view) {
        switch (type) {
            case LAYOUT_ONE:
                return new ViewHolderOne(view);
            case LAYOUT_TWO:
                return new ViewHolderTwo(view);
            case LAYOUT_THREE:
                return new ViewHolderThree(view);
        }
        return null;
    }
}
