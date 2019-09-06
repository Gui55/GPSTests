package com.example.merggps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> usuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsuarioViewHolder(

                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_usuario_linha, parent, false)

        );
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {

        Usuario usuario = usuarios.get(position);

        holder.nome.setText(usuario.getNome());

        //OS TEXTVIEWS DO HOLDER RECEBERÃO OS VALORES DA INSTÂNCIA DA CLASSE USUÁRIO FEITA NESTE MÉTODO

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    protected class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView nome;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = (TextView)itemView.findViewById(R.id.nomeRow);

        }
    }


}
