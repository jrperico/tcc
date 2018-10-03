package com.example.cliente.pedalaapp2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

        private static String NAME = "PedalaApp2.db";
        private static int VERSION = 1;

        public DBHelper (Context context){
            super(context,NAME,null,VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    "CREATE TABLE [usuario] (\n" +
                            "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                            "[login] VARCHAR(20)  NOT NULL,\n" +
                            "[senha] VARCHAR(32)  NOT NULL,\n" +
                            "[tipo] VARCHAR(20)  NOT NULL\n" +
                             ")"
            );
            db.execSQL(
                    "CREATE TABLE [produto] (\n" +
                            "[id] INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                            "[nome] VARCHAR(50)  NOT NULL,\n" +
                            "[valor] floAT  NOT NULL,\n" +
                            "[tipo] VARCHAR(20)  NOT NULL\n" +
                            ")"
            );
            db.execSQL(
                    "CREATE TABLE [ingrediente] (\n" +
                            "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                            "[nome] varCHAR(50)  NOT NULL\n" +
                            ")"
            );
            db.execSQL(
                    "CREATE TABLE [lanche_ingrediente] (\n" +
                            "[id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                            "[idLanche] INTEGER  NOT NULL,\n" +
                            "[idIngrediente] inTEGER  NOT NULL\n" +
                            ")"
            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        }
}


