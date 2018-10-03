package com.example.cliente.pedalaapp2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UsuAdapter extends ArrayAdapter<Usuario> {

    private ArrayList<Usuario> usuarios;

    public UsuAdapter(@NonNull Context context, @NonNull ArrayList<Usuario> usuarios) {
        super(context, 0, usuarios);

        this.usuarios = usuarios;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Usuario usuario = usuarios.get(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario,null);

        TextView txtLogin = (TextView)convertView.findViewById(R.id.txtLogin);
        TextView txtTipo = (TextView)convertView.findViewById(R.id.txtTipo);

        txtLogin.setText(usuario.getLogin().toString());
        txtTipo.setText(usuario.getTipo().toString());

        return convertView;

    }
}
