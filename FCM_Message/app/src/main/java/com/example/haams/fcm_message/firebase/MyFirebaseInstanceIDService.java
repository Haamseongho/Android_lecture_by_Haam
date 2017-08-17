package com.example.haams.fcm_message.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by haams on 2017-07-27.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseToken";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
       // Toast.makeText(MyFirebaseInstanceIDService.this,refreshToken,Toast.LENGTH_LONG).show();
        Log.d(TAG,  " refreshToken = " + refreshToken);
        sendRegistrationToServer(refreshToken);
    }

    private void sendRegistrationToServer(String refreshToken) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",refreshToken)
                .build();

    }
}
