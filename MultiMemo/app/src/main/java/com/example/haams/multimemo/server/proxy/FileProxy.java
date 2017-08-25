package com.example.haams.multimemo.server.proxy;

import com.example.haams.multimemo.items.Memo;
import com.example.haams.multimemo.server.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

/**
 * Created by haams on 2017-08-21.
 */

public class FileProxy {
    private Service service;
    public FileProxy(Retrofit retrofit){
        service = retrofit.create(Service.class);
    }
    public void uploadFileToServer(String memoTitle, String memoContent, String memoImagePath, Callback<Memo> callback){
        Call<Memo> call = service.uploadFile(memoTitle,memoContent,memoImagePath);
        call.enqueue(callback);
    }
}
