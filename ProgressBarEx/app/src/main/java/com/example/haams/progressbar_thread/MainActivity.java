package com.example.haams.progressbar_thread;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements Runnable {

    private ProgressBar progressBar;
    Thread thread;
    int mProgress;
    IntentFilter mFilter;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProgressBar();
    }

    private void initProgressBar() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setProgress(0);
        thread = new Thread(this);
        intent = new Intent(this, SecondActivity.class);
    }

    @Override
    protected void onStart() {
        thread.start();
        super.onStart();
    }

    @Override
    public void run() {
        mProgress = 0;
        while (mProgress < 10) {
            mProgress++;
            progressBar.setProgress(mProgress);
            try {
                thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        startActivity(intent);
    }

}
