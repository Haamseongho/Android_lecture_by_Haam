package com.example.haams.pallete_ninepatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;

public class SubActivity extends AppCompatActivity {

    @BindView(R.id.btnType)
    Button btnType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
    }
}
