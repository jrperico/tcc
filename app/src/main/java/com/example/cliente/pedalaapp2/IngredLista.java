package com.example.cliente.pedalaapp2;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class IngredLista extends AppCompatActivity {

    private ListView lvIngredList;
    private Button btIngredListCad;
    private Button btIngredListCancelar;
    private AlertDialog alerta;
    private String ingredNome;
    private Ingrediente ingrediente = new Ingrediente(this);
    private ArrayList<Ingrediente> ingredientes;
    private IngredAdapter ingredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingred_lista);

        lvIngredList = (ListView) findViewById(R.id.lvIngredList);
        btIngredListCad = (Button) findViewById(R.id.btIngredListCad);

        ingredientes = ingrediente.getIngredientes();
        ingredAdapter = new IngredAdapter(this,ingredientes);
        lvIngredList.setAdapter(ingredAdapter);


        btIngredListCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = getLayoutInflater();
                final View view = layoutInflater.inflate(R.layout.alerta_ingrediente,null);

                view.findViewById(R.id.btAlertIngredCancelar).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alerta.dismiss();
                    }
                });

                view.findViewById(R.id.btAlertIngredSalvar).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText nomeIngred = (EditText) view.findViewById(R.id.edtAlertIngredNome);
                        ingredNome =  nomeIngred.getText().toString();
                        ingrediente.setNome(ingredNome);
                        if (ingrediente.salvar()) {
                            Toast.makeText(IngredLista.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                            recreate();
                        }
                        alerta.hide();
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(IngredLista.this);
                builder.setTitle("Insira o nome do ingrediente");
                builder.setView(view);
                alerta = builder.create();
                alerta.show();

            }
        });

        lvIngredList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ingrediente.carregaIngredientePeloId(ingredientes.get(position).getId());
                if(ingrediente.excluir()) {
                    Toast.makeText(IngredLista.this, "Ingrediente excluido com sucesso", Toast.LENGTH_SHORT).show();
                    recreate();
                }
                return false;
            }
        });

        btIngredListCancelar = (Button) findViewById(R.id.btIngredListCancelar);
        btIngredListCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
