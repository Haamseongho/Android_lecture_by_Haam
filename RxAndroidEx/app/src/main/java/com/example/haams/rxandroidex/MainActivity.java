package com.example.haams.rxandroidex;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.mTxtView)
    TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initObservable();
    }

    private void initObservable() {
        // 데이터의 강을 만들자!
        rx.Observable<String> simpleObservable =
                rx.Observable.create(new rx.Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        // 데이터의 강에서 필요 데이터만 뽑아보자!
                        subscriber.onNext("새로운 데이터 전달");
                        subscriber.onCompleted(); // 스트림 종료
                    }
                });
        // Observable 생성 이 후 Subscribe 호출 시 새로운 데이터 전달 후 스트림 종료

        simpleObservable.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s.toUpperCase();
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mainText.setText("abCdEfG");
            }
        });
       /* simpleObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG,"Messaging passed successful");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG,"Error  "+e.getMessage());
            }

            @Override
            public void onNext(String text) {
                mainText.setText("최종 전달");
            }
        });*/
    }
}
