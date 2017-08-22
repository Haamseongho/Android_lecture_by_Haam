package com.example.haams.multimemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.haams.multimemo.database.*;
import com.example.haams.multimemo.database.DBHelper;
import com.example.haams.multimemo.items.Memo;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemoActivity extends AppCompatActivity {

    com.example.haams.multimemo.database.DBHelper dbHelper;
    String[] memoList;
    private static final String TAG = "MemoActivity";
    private ArrayList<Memo> memoList2;
    @BindView(R.id.btn_dropDB)
    Button btnDrop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        dbHelper = new DBHelper(this, "memoDB", null, 1);
        memoList = new String[3];
        Log.e(TAG,dbHelper.getMemo());
        memoList[0] = dbHelper.getMemo().split("=")[0]; // title
        memoList[1] = dbHelper.getMemo().split("=")[1]; // memo
        memoList[2] = dbHelper.getMemo().split("=")[2]; // imagePath

        Log.i(TAG, "제목 : " + memoList[0] + "/" + "메모 : " + memoList[1] + "/" + "이미지 경로 :" + memoList[2]);
        memoList2 = new ArrayList<>();

        memoList2.add(new Memo(memoList[0],memoList[1],memoList[2]));

    }

    @OnClick(R.id.btn_dropDB)
    public void dropDB(){
        dbHelper.getWritableDatabase().execSQL("DROP TABLE memoTable");
        dbHelper.close();
    }

}
