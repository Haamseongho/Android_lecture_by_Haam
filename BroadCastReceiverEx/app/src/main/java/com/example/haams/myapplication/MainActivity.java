package com.example.haams.myapplication;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        // 처음 sharedPreference에 저장할 내용은 없음. 초기화!!
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(30 * 1000);
                    // 현 패키지에 TEST_ACTION으로 보내기
                    sendBroadcast(new Intent("com.example.haams.myapplication.TEST_ACTION"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String result = sharedPreferences.getString("KEY", null);
        // 다시 시작하면 KEY 값으로 SAVED 받기
        if(result == null){
            Toast.makeText(getApplicationContext(),"Not Send receiver",Toast.LENGTH_LONG).show();
        }
        Toast.makeText(getApplicationContext(), result + " / " + "[done]",Toast.LENGTH_LONG).show();
        initNotification();

    }

    private void initNotification() {
        final Intent launchIntent = new Intent(this,MainActivity.class);
        final PendingIntent pendingIntent = PendingIntent.getActivity(this,1001,launchIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        final android.support.v4.app.NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.iu2)
                .setContentTitle("아이유랑 놀아요!")
                .setContentText("아이유 귀여워 ㅋㅋㅋㅋ 만나보고싶다.")
                .setContentIntent(pendingIntent);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setVibrate(new long[]{0,1000,250,1000});
        NotificationManagerCompat.from(this).notify(1001,builder.build());

    }
}
