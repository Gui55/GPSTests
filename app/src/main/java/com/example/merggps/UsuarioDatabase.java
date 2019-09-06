package com.example.merggps;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class}, version = 1, exportSchema = false)
public abstract class UsuarioDatabase extends RoomDatabase {

    public abstract UsuarioDAO usuarioDAO();

}
