package com.example.haams.fcm_message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.haams.fcm_message.firebase.MyFirebaseInstanceIDService;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

public class FcmTestActivity extends AppCompatActivity {

    MyFirebaseInstanceIDService myFirebaseInstanceIDService;
    private static final String TAG = "FcmTestActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm_test);
        getFcmToken();
    }

    private void getFcmToken() {
        myFirebaseInstanceIDService = new MyFirebaseInstanceIDService();
        myFirebaseInstanceIDService.onTokenRefresh();

        FirebaseMessaging.getInstance().subscribeToTopic("news");
        FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, FirebaseInstanceId.getInstance().getToken());
    }
}
