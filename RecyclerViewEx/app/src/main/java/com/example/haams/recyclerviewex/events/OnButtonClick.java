package com.example.haams.recyclerviewex.events;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haams.recyclerviewex.R;
import com.example.haams.recyclerviewex.sqlite.MyDBHelper;

/**
 * Created by haams on 2017-07-21.
 */
public class OnButtonClick implements View.OnClickListener {
    int position;
    Context context;
    MyDBHelper dbhelper;
    TextView title, diary_contents;
    EditText contents;
    String diary;
    AlertDialog.Builder dlg;
    View itemView;

    public OnButtonClick(TextView title, EditText contents, Context context, int position) {
        this.title = title;
        this.contents = contents;
        this.context = context;
        this.position = position;

        initDB();
    }

    private void initDB() {
        dbhelper = new MyDBHelper(context, MyDBHelper.dbname, null, 1);
        dlg = new AlertDialog.Builder(context);
        itemView = LayoutInflater.from(context).inflate(R.layout.diary_contents, null);
        diary_contents = (TextView)itemView.findViewById(R.id.diary_contents);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveDiary:
                SaveDataIntoDB();
                break;

            case R.id.loadDiary:
                LoadDataFromDB();
                break;
        }
    }


    private void SaveDataIntoDB() {
        dbhelper.insertData(title.getText().toString(), contents.getText().toString());
        Toast.makeText(context, "일정 저장이 완료되었습니다.", Toast.LENGTH_LONG).show();
        // clear
    }

    private void LoadDataFromDB() {
        diary = dbhelper.loadData(title.getText().toString());
        diary_contents.setText(diary);
        dlg.setView(itemView);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,"일정을 확인하였습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        dlg.show();
    }
}
