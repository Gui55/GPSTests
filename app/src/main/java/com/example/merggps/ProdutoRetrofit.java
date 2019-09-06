package com.example.merggps;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProdutoRetrofit {

    private final ProdutoServices produtoServices;

    public ProdutoRetrofit() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.236.58:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        produtoServices = retrofit.create(ProdutoServices.class);

    }

    public ProdutoServices getProdutoServices(){
        return produtoServices;
    }

}
