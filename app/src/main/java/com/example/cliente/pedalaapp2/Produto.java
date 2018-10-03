package com.example.cliente.pedalaapp2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class Produto {
    private int id;
    private String nome;
    private Float valor;
    private String tipo;
    private boolean excluido;
    private Context context;

    public Produto(Context context) {
        this.context = context;
        id = -1;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluir) {
        this.excluido = excluir;
    }


    public boolean salvar(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            String sql = "";
            if (id==-1){
                sql = "INSERT INTO produto (nome, valor, tipo) VALUES (?, ?, ?)";
            }else{
                sql = "UPDATE produto SET nome = ?, valor = ?, tipo = ? WHERE id = ?";
            }
            sqLiteDatabase.beginTransaction();
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, nome);
            sqLiteStatement.bindDouble(2, valor);
            sqLiteStatement.bindString(3, tipo);

            if (id!=-1){
                sqLiteStatement.bindString(4,String.valueOf(id));
            }
            sqLiteStatement.executeInsert();
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {

            if (sqLiteDatabase!=null){
                sqLiteDatabase.close();
            }
            if (dbHelper!=null){
                dbHelper.close();
            }
        }

    }


    public boolean excluir(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();

            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.delete("produto","id = ?", new String[]{String.valueOf(id)});
            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();
            return true;

        }catch (Exception e){
            e.printStackTrace();
            sqLiteDatabase.endTransaction();
            return false;
        }finally {

            if (sqLiteDatabase!=null){
                sqLiteDatabase.close();
            }
            if (dbHelper!=null){
                dbHelper.close();
            }
        }
    }

    public ArrayList<Produto> getProdutos (){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<Produto> produtos = new ArrayList<>();

        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("produto",null,null,null,null,null,null);
            while(cursor.moveToNext()){

                Produto p = new Produto(context);
                p.id = cursor.getInt(cursor.getColumnIndex("id"));
                p.nome = cursor.getString(cursor.getColumnIndex("nome"));
                p.valor = cursor.getFloat(cursor.getColumnIndex("valor"));
                p.tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                produtos.add(p);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ((cursor!=null)&&(!cursor.isClosed())){
                cursor.close();
            }
            if (sqLiteDatabase!=null){
                sqLiteDatabase.close();
            }
            if (dbHelper!=null){
                dbHelper.close();
            }
        }

        return produtos;
    }


    public void carregaProdutoPeloId (int id){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;

        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("produto",null,"id = ?",new String[]{String.valueOf(id)},null,null,null);
            excluido = true;
            while(cursor.moveToNext()){

                this.id = cursor.getInt(cursor.getColumnIndex("id"));
                nome = cursor.getString(cursor.getColumnIndex("nome"));
                valor = cursor.getFloat(cursor.getColumnIndex("valor"));
                tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                excluido = false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if ((cursor!=null)&&(!cursor.isClosed())){
                cursor.close();
            }
            if (sqLiteDatabase!=null){
                sqLiteDatabase.close();
            }
            if (dbHelper!=null){
                dbHelper.close();
            }
        }

    }

}
