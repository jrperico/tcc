package com.example.cliente.pedalaapp2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

public class Usuario {
    private int id;
    private String login;
    private String senha;
    private String tipo;
    private Context context;
    private boolean excluido;

    public Usuario(Context context) {
        this.context = context;
        id = -1;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }

    public boolean salvar(){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getWritableDatabase();
            String sql = "";
            if (id==-1){
                sql = "INSERT INTO usuario (login, senha, tipo) VALUES (?, ?, ?)";
            }else{
                sql = "UPDATE usuario SET login = ?, senha = ?, tipo = ? WHERE id = ?";
            }
            sqLiteDatabase.beginTransaction();
            SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
            sqLiteStatement.clearBindings();
            sqLiteStatement.bindString(1, login);
            sqLiteStatement.bindString(2, senha);
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
            sqLiteDatabase.delete("usuario","id = ?", new String[]{String.valueOf(id)});
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

    public ArrayList<Usuario> getUsuarios (){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("usuario",null,null,null,null,null,null);
            while(cursor.moveToNext()){

                Usuario usuario = new Usuario(context);
                usuario.id = cursor.getInt(cursor.getColumnIndex("id"));
                usuario.login = cursor.getString(cursor.getColumnIndex("login"));
                usuario.senha = cursor.getString(cursor.getColumnIndex("senha"));
                usuario.tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                usuarios.add(usuario);
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

        return usuarios;
    }

    public void carregaUsuarioPeloId (int id){
        DBHelper dbHelper = null;
        SQLiteDatabase sqLiteDatabase = null;
        Cursor cursor = null;

        try{
            dbHelper = new DBHelper(context);
            sqLiteDatabase = dbHelper.getReadableDatabase();
            cursor = sqLiteDatabase.query("usuario",null,"id = ?",new String[]{String.valueOf(id)},null,null,null);
            excluido = true;
            while(cursor.moveToNext()){

                this.id = cursor.getInt(cursor.getColumnIndex("id"));
                login = cursor.getString(cursor.getColumnIndex("login"));
                senha = cursor.getString(cursor.getColumnIndex("senha"));
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
