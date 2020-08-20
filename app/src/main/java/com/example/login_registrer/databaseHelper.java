package com.example.login_registrer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHelper extends SQLiteOpenHelper {
    public databaseHelper(@Nullable Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuarios(id INTEGER PRIMARY KEY AUTOINCREMENT,usuario TEXT UNIQUE, correo TEXT UNIQUE, password TEXT, repassword TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS usuarios;");
    }

    public boolean insert(String usuario, String correo, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("usuario",usuario);
        contentValues.put("correio",correo);
        contentValues.put("password",password);
        long reg = db.insert("usuarios", null, contentValues);
        if (reg == -1) return false;
        else return true;
    }
    public boolean usuariocheck(String usuario){
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE usuario=?",new String[]{usuario});
    if (cursor.getCount()>0) return false;
    else return false;
    }
    public boolean correoCheck(String correo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE correo=?",new String[]{correo});
        if (cursor.getCount()>0) return false;
        else return false;
    }

    public boolean cheecker(String usuario,String password){
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE usuario = ? and password = ?", new String[]{usuario, password});
    if (cursor.getCount()>0) return true;
    else return false;
    }
}
