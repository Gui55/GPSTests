package com.example.merggps;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoServices {

    @GET("produto")
    Call<List<Produto>> buscaTodos();

}
