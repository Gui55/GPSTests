package com.example.merggps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityDoRetrofit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_retrofit);

        final TextView texto = findViewById(R.id.retrofitText);

        ProdutoServices produtoServices = new ProdutoRetrofit().getProdutoServices();
        Call<List<Produto>> listCall = produtoServices.buscaTodos();

        listCall.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if(response!=null){
                    List<Produto> body = response.body();

                    Produto produto = body.get(0);

                    texto.setText(produto.getNome());
                } else {
                    Toast.makeText(ActivityDoRetrofit.this,
                            "Verifique sua URL",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                Toast.makeText(ActivityDoRetrofit.this, "Não tem internet!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
