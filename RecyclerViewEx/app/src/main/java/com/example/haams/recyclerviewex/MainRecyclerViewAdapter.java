package com.example.haams.recyclerviewex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.example.haams.recyclerviewex.events.OnButtonClick;
import com.example.haams.recyclerviewex.items.NoteItems;

import java.io.BufferedOutputStream;
import java.util.ArrayList;

/**
 * Created by haams on 2017-07-19.
 */

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {

    private ArrayList<NoteItems> mainItemList;
    private Context context;

    public MainRecyclerViewAdapter(ArrayList<NoteItems> mainItemList, Context context) {
        this.mainItemList = mainItemList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private EditText contents;
        private Button btnSave;
        private Button btnLoad;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.simple_title);
            contents = (EditText)itemView.findViewById(R.id.simple_contents);
            btnSave = (Button)itemView.findViewById(R.id.saveDiary);
            btnLoad = (Button)itemView.findViewById(R.id.loadDiary);
        }
    }

    @Override
    public MainRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items, parent, false);

        ViewHolder vh = new ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.title.setText(mainItemList.get(position).getTitle());
        holder.contents.setText(mainItemList.get(position).getContents().toString());
        holder.btnSave.setOnClickListener(new OnButtonClick(holder.title,holder.contents,context,position));
        holder.btnLoad.setOnClickListener(new OnButtonClick(holder.title,holder.contents,context,position));

    }



    @Override
    public int getItemCount() {
        return mainItemList.size();
    }


    private class onImageClick implements View.OnClickListener {
        int position;

        public onImageClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rippleView:
                    // do nothing;
                    break;
            }
        }
    }
    public void addAtPosition(int position,String title,String contents){
        if(position > mainItemList.size()){
            // 전체 사이즈보다 클 경우 .. 맨 뒤로!!
            position = mainItemList.size();
        }
        mainItemList.add(position,new NoteItems(title,contents));
        notifyItemInserted(position);
    }
    public void removeAtPosition(int position){
        if(position < mainItemList.size()){
            mainItemList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void move(int fromPosition,int toPosition){
        final String title = mainItemList.get(fromPosition).getTitle();
        final String contents = mainItemList.get(fromPosition).getContents();
        mainItemList.remove(fromPosition);
        mainItemList.add(toPosition,new NoteItems(title,contents));
        notifyItemMoved(fromPosition,toPosition);
    }
}


