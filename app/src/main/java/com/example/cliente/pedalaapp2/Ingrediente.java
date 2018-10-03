package com.example.cliente.pedalaapp2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class Ingrediente {

    private int id;
    private String nome;
    private Context context;
    private boolean excluido;

    public boolean isExcluido() {
        return excluido;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public Ingrediente(Context context) {
        super();
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public boolean salvar(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            String sql = "";
            sql = "INSERT INTO ingrediente (nome) VALUES (?)";
            sqLiteDatabase.beginTransaction();
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, nome);
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
            sqLiteDatabase.delete("ingrediente","id = ?", new String[]{String.valueOf(id)});
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
    public ArrayList<Ingrediente> getIngredientes (){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();

        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("ingrediente",null,null,null,null,null,null);
            while(cursor.moveToNext()){

                Ingrediente ingrediente = new Ingrediente(context);
                ingrediente.id = cursor.getInt(cursor.getColumnIndex("id"));
                ingrediente.nome = cursor.getString(cursor.getColumnIndex("nome"));
                ingredientes.add(ingrediente);
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

        return ingredientes;
    }

    public void carregaIngredientePeloId (int id){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;

        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("ingrediente",null,"id = ?",new String[]{String.valueOf(id)},null,null,null);
            excluido = true;
            while(cursor.moveToNext()){

                this.id = cursor.getInt(cursor.getColumnIndex("id"));
                nome = cursor.getString(cursor.getColumnIndex("nome"));
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
