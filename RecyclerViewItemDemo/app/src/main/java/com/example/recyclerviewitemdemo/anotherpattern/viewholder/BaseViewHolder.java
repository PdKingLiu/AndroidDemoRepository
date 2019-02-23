package com.example.recyclerviewitemdemo.anotherpattern.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    SparseArray<View> viewArray;

    View itemView;

    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        viewArray = new SparseArray<>();
    }

    public View getView(int resId) {

        View view = viewArray.get(resId);

        if (view == null) {
            view = this.itemView.findViewById(resId);
            viewArray.put(resId, view);
        }
        return view;
    }

    public abstract void bindViewData(T data);

}
