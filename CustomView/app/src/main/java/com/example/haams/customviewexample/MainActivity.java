package com.example.haams.customviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText cntStar;
    private StarIndicatorView sIndicator;
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        cntStar = (EditText)findViewById(R.id.edtStar);
        sIndicator = (StarIndicatorView) findViewById(R.id.indicator);
        findViewById(R.id.btnScore).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnScore:
                Log.i(TAG, String.valueOf(sIndicator.getSelected()));
                int selected = sIndicator.getSelected();
                if(selected == 5){
                    selected = 0;
                }else{
                    selected = 0;
                    selected += Integer.parseInt(cntStar.getText().toString());
                }
                sIndicator.setSelected(selected);
        }
    }
}
