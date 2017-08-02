package com.example.haams.surfaceviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(new MySurfaceView(this)); --> SurfaceView
        myView = (MyView) findViewById(R.id.myView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.changeColor:
                changeColor();
                break;
            case R.id.drawItems:
                drawItems();
                break;
            case R.id.eraser:
                eraseItems();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void changeColor() {
        Log.i(TAG, "changeColor() 호출");
    }

    private void drawItems() {
        Log.i(TAG, "drawItems() 호출");
    }

    private void eraseItems() {
        Log.i(TAG, "eraseItems() 호출");
    }
}
