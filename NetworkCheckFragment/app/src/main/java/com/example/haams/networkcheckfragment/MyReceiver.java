package com.example.haams.networkcheckfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by haams on 2017-06-28.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MyReceiver","onReceive");
        Intent i = new Intent(NetworkCheckFragment.ACTION_CHECK_INTERNET);
        // 인텐트를 보내는 데 NetworkCheckFragment에서의 TAG에다 보내는 것..!
        i.putExtra(NetworkCheckFragment.KEY_CHECK_INTERNET
        ,NetworkCheckFragment.isInternetConnected(context));
        // 연결 변경 알림
        LocalBroadcastManager.getInstance(context).sendBroadcast(i);
        // LocalBroadCastManager에서 브로드 캐스트 방식으로 인텐트를 보낸다.
    }
}
