package com.aprendiz.ragp.strooperpro.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GestorDB extends SQLiteOpenHelper{
    public GestorDB(Context context) {
        super(context, "JuegoP.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DATOS (CORRECTAS TEXT, PORCENTAJE TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
