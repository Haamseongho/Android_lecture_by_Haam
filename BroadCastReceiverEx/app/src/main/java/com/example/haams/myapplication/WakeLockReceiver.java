package com.example.haams.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.UnsupportedSchemeException;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class WakeLockReceiver extends WakefulBroadcastReceiver {
    private static final String TAG = "WakeLockReceiver";
    private Intent serviceIntent;

    public WakeLockReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            serviceIntent = new Intent(context,MyIntentService.class);
            startWakefulService(context,serviceIntent);
        }catch(UnsupportedOperationException e){
            e.printStackTrace();
            Log.e(TAG,String.valueOf("error--> WakeLockReceived"));
        }
    }
}
