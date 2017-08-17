package com.example.haams.mvvm_test;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by haams on 2017-08-10.
 */

public class User {
    public ObservableField<String> name = new ObservableField<>();
    public ObservableInt likes = new ObservableInt();

    public User(String name3){
        name.set(name3);
        likes.set(0);
    }

    public void onClickLike(View view) {
        likes.set(likes.get() + 1);
    }
}
