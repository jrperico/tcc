package com.example.cliente.pedalaapp2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText edtLogLogin;
    EditText edtLogSenha;
    Button btLogEntrar;
    DBHelper dbHelper = new DBHelper(this);
    SQLiteDatabase sqLiteDatabase = null;
    Cursor cursor = null;
    Usuario u = new Usuario(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLogLogin = (EditText) findViewById(R.id.edtLogLogin);
        edtLogSenha = (EditText) findViewById(R.id.edtLogSenha);
        btLogEntrar = (Button) findViewById(R.id.btLogEntrar);
        btLogEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edtLogLogin.getText().toString();
                String senha = edtLogSenha.getText().toString();

                sqLiteDatabase = dbHelper.getReadableDatabase();
                cursor = sqLiteDatabase.query("usuario",null,"login = "+login,null,null,null,null);
                while(cursor.moveToNext()){
                    u.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    u.setLogin(cursor.getString(cursor.getColumnIndex("login")));
                    u.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
                    u.setTipo(cursor.getString(cursor.getColumnIndex("tipo")));
                }





            }
        });

    }
}
