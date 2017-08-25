package com.example.haams.multimemo.server;

import com.example.haams.multimemo.server.proxy.FileProxy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by haams on 2017-08-21.
 */

public class Network {
    public static Network network;
    private FileProxy fileProxy;
    private Retrofit retrofit;
    public static Network getNetworkInstance() {
        if (network == null) {
            network = new Network();
        }
        return network;
    }

    public Network() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl("http://127.0.0.1:3000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build()).build();

        fileProxy = new FileProxy(retrofit);
    }

    public FileProxy getFileProxy() {
        return fileProxy;
    }
}
