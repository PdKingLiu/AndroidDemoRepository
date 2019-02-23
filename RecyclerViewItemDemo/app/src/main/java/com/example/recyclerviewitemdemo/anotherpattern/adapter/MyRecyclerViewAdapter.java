package com.example.recyclerviewitemdemo.anotherpattern.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewitemdemo.anotherpattern.decorateclass.Visitable;
import com.example.recyclerviewitemdemo.anotherpattern.factory.ItemTypeFactory;
import com.example.recyclerviewitemdemo.anotherpattern.viewholder.BaseViewHolder;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<Visitable> visitableList;

    private ItemTypeFactory itemTypeFactory;

    public MyRecyclerViewAdapter(List<Visitable> visitableList) {

        itemTypeFactory = new ItemTypeFactory();

        this.visitableList = visitableList;

    }

    @Override
    public int getItemViewType(int position) {
        return visitableList.get(position).type(itemTypeFactory);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        return itemTypeFactory.createViewHolder(i, view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return visitableList.size();
    }

}
