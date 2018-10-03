package com.example.cliente.pedalaapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ProdLista extends AppCompatActivity {

    private ListView lvProdList;
    private Button btProdListCad;
    private Button btProdListCancelar;
    private ArrayList<Produto> produtos;
    private ProdAdapter prodAdapter;
    private Produto prodEdicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prod_lista);

        lvProdList = (ListView) findViewById(R.id.lvProdList);
        produtos = new Produto(this).getProdutos();
        ProdAdapter prodAdapter = new ProdAdapter(this,produtos);
        lvProdList.setAdapter(prodAdapter);
        lvProdList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto = produtos.get(position);
                Intent i = new Intent(ProdLista.this,ProdCadastro.class);
                i.putExtra("consulta",produto.getId());
                prodEdicao = produto;
                startActivity(i);
            }
        });

        btProdListCad = (Button) findViewById(R.id.btProdListCad);
        btProdListCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProdLista.this,ProdCadastro.class);
                startActivity(i);
                finish();
            }
        });

        btProdListCancelar = (Button) findViewById(R.id.btProdListCancelar);
        btProdListCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prodEdicao!=null) {
            prodEdicao.carregaProdutoPeloId(prodEdicao.getId());
            if (prodEdicao.isExcluido())
                produtos.remove(prodEdicao);
            prodAdapter.notifyDataSetChanged();
        }
    }
}
