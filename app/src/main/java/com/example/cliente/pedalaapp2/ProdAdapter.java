package com.example.cliente.pedalaapp2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ProdAdapter extends ArrayAdapter<Produto> {

    private ArrayList<Produto> produtos;

    public ProdAdapter(@NonNull Context context, @NonNull ArrayList<Produto> produtos) {
        super(context, 0, produtos);
        this.produtos = produtos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Produto produto = produtos.get(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_usuario,null);

        TextView txtLogin = (TextView)convertView.findViewById(R.id.txtLogin);
        TextView txtTipo = (TextView)convertView.findViewById(R.id.txtTipo);

        txtLogin.setText(produto.getNome().toString());
        txtTipo.setText(produto.getValor().toString());

        return convertView;


    }
}
