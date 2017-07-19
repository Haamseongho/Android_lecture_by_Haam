package com.example.haams.recyclerviewex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.andexert.library.RippleView;
import com.bumptech.glide.Glide;
import com.example.haams.recyclerviewex.items.MainItems;

import java.util.ArrayList;

/**
 * Created by haams on 2017-07-19.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private ArrayList<MainItems> mainItemList;
    private Context context;

    public MainRecyclerViewAdapter(ArrayList<MainItems> mainItemList, Context context) {
        this.mainItemList = mainItemList;
        this.context = context;
    }

    public MainRecyclerViewAdapter(ArrayList<MainItems> mainItemList) {
        this.mainItemList = mainItemList;
    }

    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items,parent,false);

        ViewHolder vh = new ViewHolder(itemView);
        mainItemList = new ArrayList<>();
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(mainItemList.get(position).getmImage())
                .fitCenter()
                .skipMemoryCache(true)
                .into(holder.mImage);

        holder.mImage.setOnClickListener(new onImageClick(position));
        holder.rippleView.setOnClickListener(new onImageClick(position));
    }

    @Override
    public int getItemCount() {
        return mainItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private RippleView rippleView;

        public ViewHolder(View itemView) {
            super(itemView);
            mImage = (ImageView)itemView.findViewById(R.id.mImage);
            rippleView = (RippleView)itemView.findViewById(R.id.rippleView);
        }
    }

    private class onImageClick implements View.OnClickListener {
        int position;

        public onImageClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // rippleView 효과
        }
    }
}


