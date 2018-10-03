package com.example.cliente.pedalaapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class UsuLista extends AppCompatActivity {

    private ListView lvUsuList;
    private Button btUsuListCad;
    private Button btUsuListCancelar;
    private ArrayList<Usuario> usuarios;
    private UsuAdapter usuAdapter ;
    private Usuario usuEdicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_lista);

        lvUsuList = (ListView) findViewById(R.id.lvUsuList);
        usuarios = new Usuario (this).getUsuarios();
        usuAdapter = new UsuAdapter(this,usuarios);
        lvUsuList.setAdapter(usuAdapter);
        lvUsuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario usuario = usuarios.get(position);
                Intent i = new Intent(UsuLista.this,UsuCadastro.class);
                i.putExtra("consulta", usuario.getId());
                usuEdicao = usuario;
                startActivity(i);
            }
        });

        btUsuListCad = (Button) findViewById(R.id.btUsuListCad);
        btUsuListCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UsuLista.this,UsuCadastro.class);
                startActivity(i);
                finish();
            }
        });


        btUsuListCancelar = (Button) findViewById(R.id.btUsuListCancelar);
        btUsuListCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (usuEdicao!=null) {
            usuEdicao.carregaUsuarioPeloId(usuEdicao.getId());
            if (usuEdicao.isExcluido())
                usuarios.remove(usuEdicao);
            usuAdapter.notifyDataSetChanged();
        }
    }


}
