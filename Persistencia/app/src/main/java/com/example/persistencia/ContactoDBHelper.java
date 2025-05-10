package com.example.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContactoDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contactos.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "contactos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_IMAGEN_ID = "imagen_id";

    public ContactoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_DESCRIPCION + " TEXT, " +
                COLUMN_IMAGEN_ID + " INTEGER)";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertarContacto(Contacto contacto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, contacto.getNombre());
        values.put(COLUMN_DESCRIPCION, contacto.getDescripcion());
        values.put(COLUMN_IMAGEN_ID, contacto.getImagenId());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Contacto> obtenerContactos() {
        List<Contacto> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRE));
                String telefono = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPCION));
                int imagenId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGEN_ID));
                lista.add(new Contacto(id, nombre, telefono, imagenId));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }
    public Contacto obtenerContactoPorId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("contactos", null, "id = ?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Contacto c = new Contacto(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    cursor.getString(cursor.getColumnIndexOrThrow("descripcion")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("imagen_id"))
            );
            cursor.close();
            return c;
        }

        return null;
    }
    public void actualizarContacto(Contacto contacto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, contacto.getNombre());
        values.put(COLUMN_DESCRIPCION, contacto.getDescripcion());
        values.put(COLUMN_IMAGEN_ID, contacto.getImagenId());

        db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(contacto.getId())});
        db.close();
    }

    public void eliminarContacto(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }



}
