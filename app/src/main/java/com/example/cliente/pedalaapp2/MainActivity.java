package com.example.cliente.pedalaapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btUsu;
    private Button btProd;
    private Button btIngred;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btUsu = (Button) findViewById(R.id.btUsu);
        btProd = (Button) findViewById(R.id.btProd);
        btIngred = (Button) findViewById(R.id.btIngred);

        btUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,UsuLista.class);
                startActivity(i);
            }
        });
        btProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ProdLista.class);
                startActivity(i);
            }
        });
        btIngred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,IngredLista.class);
                startActivity(i);
            }
        });


    }
}
