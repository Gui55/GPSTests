package com.example.merggps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText enterNome, enterSenha;

    //public UsuarioAdapter usuarioAdapter;
    private UsuarioDAO usuarioDAO;
    public static RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //INSTANCIAR UM ADAPTER PARA JÁ MOSTRAR A RECYCLERVIEW

        UsuarioAdapter ua = new UsuarioAdapter(usuarioDAO.getUsuarios());

        recyclerView.setAdapter(ua);

        ua.notifyDataSetChanged();

        //SOBRE OS EDITTEXTS

        enterNome=(EditText)findViewById(R.id.tryNome);
        enterSenha =(EditText)findViewById(R.id.trySenha);


        //SOBRE O ROOM

        //Instância do DAO
        usuarioDAO = Room.databaseBuilder(getApplicationContext(),
                UsuarioDatabase.class, //Classe do banco de dados
                "usuarioDB") //Nome dado ao banco de dados
                .allowMainThreadQueries() //Permitir Queries na Thread principal apenas para testes
                .build() //Construir o banco de dados SQLite / Room
                .usuarioDAO(); // Pegar o DAO deste mesmo banco de dados construído


        //SOBRE O RECYCLERVIEW

        //Instância do adaptador

        /*usuarioAdapter = new UsuarioAdapter(usuarioDAO.getUsuarios());  Instanciando um adaptador,
        anexando uma lista à ele. Nesse caso, a lista de todos os usuarios do banco de dados.
        Essa lista é fornecida pelo DAO */


        //Instância do RecyclerView em si

        recyclerView = (RecyclerView)findViewById(R.id.recycle);


        // Criando um LayoutManager e fornecendo ele ao RecyclerView

        RecyclerView.LayoutManager aLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(aLayoutManager);


        //Definindo o ItemAnimator do RecyclerView. Nesse caso, é um ItemAnimator padrão

        recyclerView.setItemAnimator(new DefaultItemAnimator());


        /*Fornecendo o adapatador criado (o objeto usuarioAdapter) ao RecyclerView, ou seja,
        estamos falando para o RecyclerView que o adapatador dele é o objeto usuarioAdapter
        */
        //recyclerView.setAdapter(usuarioAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuzinho, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();

        switch(itemId){

            case R.id.meuItemMenu:
                Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void cadastrarUsuario(View view) {



        usuarioDAO.insert(

                new Usuario(

                        enterNome.getText().toString(),
                        enterSenha.getText().toString()

                )

        );

        UsuarioAdapter ua = new UsuarioAdapter(usuarioDAO.getUsuarios());

        recyclerView.setAdapter(ua);

        ua.notifyDataSetChanged();

        //usuarioAdapter.notifyDataSetChanged(); //Notifica ao adapatador que a lista de usuários mudou

    }

    public void clearList(View view) {

        usuarioDAO.apagarTudo();

        UsuarioAdapter ua = new UsuarioAdapter(usuarioDAO.getUsuarios());

        recyclerView.setAdapter(ua);

        ua.notifyDataSetChanged();

    }

    public void autenticar(View view) {

        int seHa = usuarioDAO.codeSeTem(

                enterNome.getText().toString(),
                enterSenha.getText().toString()

        );

        if(seHa == 1){

            startActivity(new Intent(this, DepoisDoLoginActivity.class));

        } else {

            Toast.makeText(this, "Usuário e senha incorretos", Toast.LENGTH_SHORT).show();

        }

    }
}
