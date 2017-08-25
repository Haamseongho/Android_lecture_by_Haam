package com.example.haams.multimemo.server;

import com.example.haams.multimemo.items.Memo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by haams on 2017-08-21.
 */

public interface Service {
    @FormUrlEncoded
    @POST("/file/upload")
    public Call<Memo> uploadFile(
            @Field("memoTitle") String memoTitle,
            @Field("memoContent") String memoContent,
            @Field("memoImagePath") String memoImagePath
    );
}
