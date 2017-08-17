package com.example.haams.fcm_message.firebase;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.haams.fcm_message.FcmTestActivity;
import com.example.haams.fcm_message.MainActivity;
import com.example.haams.fcm_message.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by haams on 2017-07-27.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage);
    }

    private void showNotification(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, FcmTestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1001, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                .setContentTitle(remoteMessage.getFrom())
                .setContentIntent(pendingIntent);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setVibrate(new long[]{0, 1000, 250, 1000});
        NotificationManagerCompat.from(this).notify(1001, builder.build());
    }
}
