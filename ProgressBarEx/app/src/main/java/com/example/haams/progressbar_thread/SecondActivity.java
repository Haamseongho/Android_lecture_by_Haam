package com.example.haams.progressbar_thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        image = (ImageView)findViewById(R.id.image2);
        image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        image.setImageResource(R.drawable.iu2);
    }
}
