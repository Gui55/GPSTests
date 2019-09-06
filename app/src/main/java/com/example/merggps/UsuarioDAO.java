package com.example.merggps;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsuarioDAO {

    @Insert
    void insert(Usuario... usuarios);

    @Query("DELETE FROM usuario")
    void apagarTudo();

    @Query("SELECT * FROM usuario")
    List<Usuario> getUsuarios();

    @Query("SELECT COUNT(*) FROM usuario WHERE nome LIKE :nUser AND senha LIKE :nPass")
    public int codeSeTem(String nUser, String nPass);

}
