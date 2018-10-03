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

public class IngredAdapter extends ArrayAdapter<Ingrediente> {

    private ArrayList<Ingrediente> ingredientes;

    public IngredAdapter(@NonNull Context context, @NonNull ArrayList<Ingrediente> ingredientes) {
        super(context, 0, ingredientes);

        this.ingredientes = ingredientes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ingrediente ingrediente = ingredientes.get(position);

        convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ingrediente,null);

        TextView txtIngredNome = (TextView)convertView.findViewById(R.id.txtIngredNome);

        txtIngredNome.setText(ingrediente.getNome());
        return convertView;

    }
}

