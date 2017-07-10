package com.example.haams.networkcheckfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private NetworkCheckFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragment = (NetworkCheckFragment) getSupportFragmentManager().findFragmentByTag(NetworkCheckFragment.TAG);
        // TAG값으로 프레그먼트 찾기

        if(mFragment == null){
            mFragment = mFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(mFragment,NetworkCheckFragment.TAG)
                    .commit();
        }
    }
}
