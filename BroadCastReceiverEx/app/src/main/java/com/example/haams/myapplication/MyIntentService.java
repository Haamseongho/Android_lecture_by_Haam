package com.example.haams.myapplication;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by haams on 2017-07-24.
 */

public class MyIntentService extends IntentService{
    public static final String TAG  = "MY_INTENT_SERVICE";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try{
            try {
                // WakefulIntent가 성공적으로 이루어질 때 작업
                // 성공적으로 이루어 졌을 때 이에 대한 내용을 SharedPreference에 저장한다.
                // 나중에 이를 getSharedPreference() 로 불러와서 값을 넣을 것이다.
                Thread.currentThread().sleep(30*1000);
                SharedPreferences sharedPreferences
                        = getApplicationContext().getSharedPreferences("test", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("KEY","SAVED");
                // KEY 값 --> 값 SAVED 보내기
                editor.apply(); // 적용
                Log.d(TAG,"onHandleIntent saved");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            // WakefulService를 인텐트로 확인하기
            WakefulBroadcastReceiver.completeWakefulIntent(intent);
        }
    }

}
