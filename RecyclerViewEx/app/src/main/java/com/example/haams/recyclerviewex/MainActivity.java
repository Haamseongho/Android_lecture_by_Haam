package com.example.haams.recyclerviewex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.haams.recyclerviewex.items.MainItems;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager manager;
    private MainRecyclerViewAdapter mAdapter;
    private ArrayList<MainItems> mainItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setHasFixedSize(true);

        mainItemList = new ArrayList<>();
        mainItemList.add(new MainItems(R.drawable.pup1));
        mainItemList.add(new MainItems(R.drawable.pup2));
        mainItemList.add(new MainItems(R.drawable.pup3));

        mAdapter = new MainRecyclerViewAdapter(mainItemList, this);

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Position => " + mRecyclerView.getChildAdapterPosition(v)
                        , Toast.LENGTH_LONG).show();
            }
        });
    }
}
