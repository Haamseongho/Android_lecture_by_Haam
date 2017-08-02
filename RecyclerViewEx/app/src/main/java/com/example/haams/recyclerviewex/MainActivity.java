package com.example.haams.recyclerviewex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.haams.recyclerviewex.items.MainItems;
import com.example.haams.recyclerviewex.items.NoteItems;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager manager;
    private MainRecyclerViewAdapter mAdapter;
    private ArrayList<NoteItems> mainItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        chkBtns();
    }

    private void chkBtns() {
        findViewById(R.id.btnAdd).setOnClickListener(this);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setHasFixedSize(true);

        mainItemList = new ArrayList<>();
        mainItemList.add(new NoteItems("제목1", "일정1"));
        mainItemList.add(new NoteItems("제목2", "일정2"));
        mainItemList.add(new NoteItems("제목3", "일정3"));

        mAdapter = new MainRecyclerViewAdapter(mainItemList, this);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        // 드래그로 지우기
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                mAdapter.move(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mAdapter.removeAtPosition(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                //
                mAdapter.addAtPosition(0, "제목입력", "오늘의 하루");
                break;
        }
    }
}
