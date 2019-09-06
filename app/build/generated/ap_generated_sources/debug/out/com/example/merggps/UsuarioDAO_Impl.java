package com.example.merggps;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class UsuarioDAO_Impl implements UsuarioDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfUsuario;

  private final SharedSQLiteStatement __preparedStmtOfApagarTudo;

  public UsuarioDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUsuario = new EntityInsertionAdapter<Usuario>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Usuario`(`id`,`nome`,`senha`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Usuario value) {
        stmt.bindLong(1, value.getId());
        if (value.getNome() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getNome());
        }
        if (value.getSenha() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getSenha());
        }
      }
    };
    this.__preparedStmtOfApagarTudo = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM usuario";
        return _query;
      }
    };
  }

  @Override
  public void insert(Usuario... usuarios) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfUsuario.insert(usuarios);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void apagarTudo() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfApagarTudo.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfApagarTudo.release(_stmt);
    }
  }

  @Override
  public List<Usuario> getUsuarios() {
    final String _sql = "SELECT * FROM usuario";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfNome = _cursor.getColumnIndexOrThrow("nome");
      final int _cursorIndexOfSenha = _cursor.getColumnIndexOrThrow("senha");
      final List<Usuario> _result = new ArrayList<Usuario>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Usuario _item;
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final String _tmpSenha;
        _tmpSenha = _cursor.getString(_cursorIndexOfSenha);
        _item = new Usuario(_tmpNome,_tmpSenha);
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int codeSeTem(String nUser, String nPass) {
    final String _sql = "SELECT COUNT(*) FROM usuario WHERE nome LIKE ? AND senha LIKE ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (nUser == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nUser);
    }
    _argIndex = 2;
    if (nPass == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, nPass);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
