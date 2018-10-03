package com.example.cliente.pedalaapp2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProdCadastro extends AppCompatActivity {

    private EditText edtProdCadNome;
    private EditText edtProdCadValor;
    private Spinner spProdCadTipo;
    private Button btProdCadSalvar;
    private Button btProdCadExcluir;
    private Button btProdCadCancelar;
    private ArrayAdapter<CharSequence> spAdapter;
    private Produto p = new Produto(this);
    private ArrayList<Ingrediente> ingredientes;
    private Ingrediente ingred = new Ingrediente(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_cadastro);

        edtProdCadNome = (EditText) findViewById(R.id.edtProdCadNome);
        edtProdCadValor = (EditText) findViewById(R.id.edtProdCadValor);
        spProdCadTipo = (Spinner) findViewById(R.id.spProdCadTipo);
        btProdCadSalvar = (Button) findViewById(R.id.btProdCadSalvar);
        btProdCadExcluir = (Button) findViewById(R.id.btProdCadExcluir);
        btProdCadCancelar = (Button) findViewById(R.id.btProdCadCancelar);

        spAdapter = ArrayAdapter.createFromResource(this,R.array.spProdCadTipo,android.R.layout.simple_list_item_1);
        spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProdCadTipo.setAdapter(spAdapter);

        if(getIntent().getExtras()!= null){
            //editando usuario
            int id = getIntent().getExtras().getInt("consulta");
            p.carregaProdutoPeloId(id);

            edtProdCadNome.setText(p.getNome());
            edtProdCadValor.setText(p.getValor().toString());

        }

        btProdCadExcluir.setEnabled(true);
        if(p.getId() == -1) {
            btProdCadExcluir.setEnabled(false);
        }
        btProdCadSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valido = true;
                String nome = edtProdCadNome.getText().toString();
                Float valor = Float.valueOf(edtProdCadValor.getText().toString());
                String tipo = spProdCadTipo.getSelectedItem().toString();

                p.setNome(nome);
                p.setValor(valor);
                p.setTipo(tipo);


                if (nome.equals("")){
                    edtProdCadNome.setError("Campo Obrigatorio");
                    valido = false;
                }
                if (valor == null){
                    edtProdCadValor.setError("Campo Obrigatorio");
                    valido = false;
                }
                if (tipo.equals("Selecionar")){
                    TextView errorText = (TextView)spProdCadTipo.getSelectedView();
                    errorText.setError("anything here, just to add the icon");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Selecionar o tipo do produto");
                    valido = false;
                }
                if (tipo.equals("Lanche")){
                    Intent i = new Intent(ProdCadastro.this,ProdIngredCadastro.class);
                    startActivity(i);
                }else  if (valido){
                        p.salvar();
                        Toast.makeText(ProdCadastro.this, "Operação realizada com sucesso", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(ProdCadastro.this, ProdLista.class);
                        startActivity(i);
                        finish();
                }
            }
        });


        btProdCadCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProdCadastro.this,ProdLista.class);
                startActivity(i);
                finish();
            }
        });

        btProdCadExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.excluir();
                finish();
            }
        });


    }
}

