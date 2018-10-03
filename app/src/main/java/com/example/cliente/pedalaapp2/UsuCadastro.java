package com.example.cliente.pedalaapp2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UsuCadastro extends AppCompatActivity {

    private EditText edtUsuCadLogin;
    private EditText edtUsuCadSenha;
    private EditText edtUsuCadConfirmaSenha;
    private Spinner spUsuCadTipo;
    private Button btUsuCadSalvar;
    private Button btUsuCadExcluir;
    private Button btUsuCadCancelar;
    private ArrayAdapter<CharSequence> spAdapter;
    private Usuario u = new Usuario(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usu_cadastro);

        edtUsuCadLogin = (EditText) findViewById(R.id.edtUsuCadLogin);
        edtUsuCadSenha = (EditText) findViewById(R.id.edtUsuCadSenha);
        edtUsuCadConfirmaSenha = (EditText) findViewById(R.id.edtUsuCadConfimaSenha);
        spUsuCadTipo = (Spinner) findViewById(R.id.spUsuCadTipo);
        btUsuCadSalvar = (Button) findViewById(R.id.btUsuCadSalvar);
        btUsuCadExcluir = (Button) findViewById(R.id.btUsuCadExcluir);
        btUsuCadCancelar = (Button) findViewById(R.id.btUsuCadCancelar);



       spAdapter = ArrayAdapter.createFromResource(this,R.array.spUsuCadTipo,android.R.layout.simple_list_item_1);
       spAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spUsuCadTipo.setAdapter(spAdapter);

        if(getIntent().getExtras()!= null){
            //editando usuario
            int id = getIntent().getExtras().getInt("consulta");
            u.carregaUsuarioPeloId(id);

            edtUsuCadLogin.setText(u.getLogin());
            edtUsuCadSenha.setText(u.getSenha());
        }

        btUsuCadExcluir.setEnabled(true);
        if(u.getId() == -1) {
            btUsuCadExcluir.setEnabled(false);
        }
        btUsuCadSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean valido = true;
                String senha = edtUsuCadSenha.getText().toString();
                String confirmasenha = edtUsuCadConfirmaSenha.getText().toString();
                String login = edtUsuCadLogin.getText().toString();
                String tipo = spUsuCadTipo.getSelectedItem().toString();

                u.setLogin(login);
                u.setSenha(senha);
                u.setTipo(tipo);

                if(!senha.equals(confirmasenha)){
                    Toast.makeText(UsuCadastro.this, "Senhas não correspondem", Toast.LENGTH_LONG).show();
                    edtUsuCadConfirmaSenha.setError("Senhas não correspondem");
                    valido = false;
                }

                if (login.equals("")){
                    edtUsuCadLogin.setError("Campo Obrigatorio");
                    valido = false;
                }

                if (tipo.equals("Selecionar")){
                    TextView errorText = (TextView)spUsuCadTipo.getSelectedView();
                    errorText.setError("anything here, just to add the icon");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Selecionar a função do usuário");
                    valido = false;
                }

                if (valido){
                    u.salvar();
                    Toast.makeText(UsuCadastro.this, "Operação realizada com sucesso", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UsuCadastro.this,UsuLista.class);
                    startActivity(i);
                    finish();
                }
            }
        });


        btUsuCadCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UsuCadastro.this, UsuLista.class);
                startActivity(i);
                finish();
            }
        });

        btUsuCadExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.excluir();
                finish();
            }
        });


    }
}
