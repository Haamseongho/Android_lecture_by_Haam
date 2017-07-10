package com.example.haams.networkcheckfragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by haams on 2017-06-28.
 */

public class NetworkCheckFragment extends Fragment {
    public static final String TAG = NetworkCheckFragment.class.getSimpleName();
    public static final String ACTION_CHECK_INTERNET = "ACTION_CHECK_INTERNET";
    public static final String KEY_CHECK_INTERNET = "KEY_CHECK_INTERNET";
    private IntentFilter mIntentFilter;

    public NetworkCheckFragment() {
    }

    public static NetworkCheckFragment newInstance() {
        return new NetworkCheckFragment();
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // BroadCastReceiver로 값을 받아올 때 인텐트로 받게 되는데,
            // 거기서 파생된 액션을 불러오는 것

            if (ACTION_CHECK_INTERNET.equals(action)) {
                // 네트워크 연결 변경에 따른 공통 처리
                boolean isConnected = intent.getBooleanExtra(KEY_CHECK_INTERNET, false);
                // intent에서 비롯된 것 중 KEY_CHECK_INTERNET으로 넘어온 것을 찾는다.
                // null이 넘어올 경우 false
                if (isConnected) {
                    Toast.makeText(context, "인터넷 연결이 있습니다.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "인터넷 연결이 없습니다.", Toast.LENGTH_LONG).show();
                }

            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setRetainInstance(true);
        // 프레그먼트가 재생성 되는 것을 방지하고자 설정
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (mIntentFilter == null) {
            mIntentFilter = new IntentFilter(ACTION_CHECK_INTERNET);
            // null일 경우 ACTION_CHECK_INTERNET 라는 문자열을 받는 필터를 설정
            // IntentFilter의 오브젝트가 들어가거나 String 문자열이 들어간다.

        }
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mReceiver, mIntentFilter);
        // 다시 시작할 경우 (ACTION_CHECK_INTERNET) 으로 설정된 IntentFilter
        // BroadCastReceiver로 가지고 온 mReceiver를 등록해준다.
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mReceiver);
        // 일시정지할 경우 등록한 BroadCastReceiver를 등록해지한다.
    }

    public static boolean isInternetConnected(Context context) {
        return isWifiConnected(context) || isMobileConnected(context);
    }

    public static boolean isWifiConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // ConnectivityManager -- 액티비티 연결된 곳에서 (context) 연결 서비스를 찾아 관리한다.
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
            // Network정보가 null 이 아니고 연결이 되어 있으며 그 타입이 와이파이일 경우
        }
        return false;
    }

    public static boolean isMobileConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE)
            return true;
        return false;
    }
}


