package com.example.haams.multimemo.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haams.multimemo.R;
import com.example.haams.multimemo.items.Memo;

import java.util.ArrayList;

/**
 * Created by haams on 2017-08-16.
 */

public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> {
    private ArrayList<Memo> memoList;
    private Context context;

    public MemoAdapter(ArrayList<Memo> memoList, Context context) {
        this.memoList = memoList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView memoTitle;
        private ImageView memoImage;


        public ViewHolder(View itemView) {
            super(itemView);
            memoTitle = (TextView) itemView.findViewById(R.id.memoTitle);
            memoImage = (ImageView) itemView.findViewById(R.id.memoImage);
        }
    }

    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_memo_lists, parent, false);
        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MemoAdapter.ViewHolder holder, int position) {
        holder.memoTitle.setText(memoList.get(position).getMemoTitle());
        Bitmap bitmap = BitmapFactory.decodeFile(memoList.get(position).getMemoImagePath());
        holder.memoImage.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return memoList.size();
    }

}
