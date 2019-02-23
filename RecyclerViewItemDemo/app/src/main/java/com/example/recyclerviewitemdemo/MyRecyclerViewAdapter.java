package com.example.recyclerviewitemdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

class MyRecyclerViewAdapter extends RecyclerView.Adapter {

    public static final int TYPE_ONE = 1;

    public static final int TYPE_TWO = 2;

    public static final int TYPE_THREE = 3;

    public MyRecyclerViewAdapter(List<MyData> myDataList) {
        this.myDataList = myDataList;
    }

    private List<MyData> myDataList;


    class ViewHoldOne extends RecyclerView.ViewHolder {
        public ViewHoldOne(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ViewHoldTwo extends RecyclerView.ViewHolder {
        public ViewHoldTwo(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ViewHoldThree extends RecyclerView.ViewHolder {
        public ViewHoldThree(@NonNull View itemView) {
            super(itemView);
        }
    }


    @Override
    public int getItemViewType(int position) {

        int type = 0;
        MyData myData = myDataList.get(position);
        switch (myData.type) {
            case 1:
                type = TYPE_ONE;
                break;
            case 2:
                type = TYPE_TWO;
                break;
            case 3:
                type = TYPE_THREE;
                break;
        }

        return type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;

        switch (i) {
            case TYPE_ONE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_one,
                        viewGroup, false);
                return new ViewHoldOne(view);
            case TYPE_TWO:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_two,
                        viewGroup, false);
                return new ViewHoldTwo(view);
            case TYPE_THREE:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_three,
                        viewGroup, false);
                return new ViewHoldThree(view);
            default:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_one,
                        viewGroup, false);
                return new ViewHoldOne(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return myDataList.size();
    }

}
