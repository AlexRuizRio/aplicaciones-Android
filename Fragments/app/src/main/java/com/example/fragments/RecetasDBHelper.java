package com.example.fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class RecetasDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "recetas.db";
    private static final int DATABASE_VERSION = 1;

    public RecetasDBHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE recipes (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, descripcion TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recetas");
    }

    public long insertRecetas (String name, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("descripcion", descripcion);
        long id =db.insert("recetas", null, values);
        db.close();
        return id;
    }

    public List<Receta> getAllRecetas() {
        List<Receta> recetas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM recetas", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                recetas.add(new Receta(id, name, desc));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return recetas;
    }
}
