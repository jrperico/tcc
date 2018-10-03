package com.example.cliente.pedalaapp2;

import android.app.ActionBar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ProdIngredCadastro extends AppCompatActivity {

    private LinearLayout llProdIngredCadastro;
    private Button btProdIngredSalvar;
    private Button btProdIngredCancelar;
    private Ingrediente ingrediente = new Ingrediente(this);
    private ArrayList<Ingrediente> ingredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_ingred_cadastro);

        llProdIngredCadastro = (LinearLayout) findViewById(R.id.llProdIngredCadastro);
        btProdIngredSalvar = (Button) findViewById(R.id.btProdIngredSalvar);
        btProdIngredCancelar = (Button) findViewById(R.id.btProdIngredCancelar);

        ingredientes = ingrediente.getIngredientes();

        for (int i=0; i<ingredientes.size();i++){
            int auxId = ingredientes.get(i).getId();
            String auxNome = ingredientes.get(i).getNome();
            int color = ContextCompat.getColor(this, R.color.black);

            CheckBox cbProdIngredCadastro = new CheckBox(this);
            cbProdIngredCadastro.setId(auxId);
            cbProdIngredCadastro.setText(auxNome);
            cbProdIngredCadastro.setTextSize(24);
            cbProdIngredCadastro.setTextColor(color);

            llProdIngredCadastro.addView(cbProdIngredCadastro);
        }

        btProdIngredCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
