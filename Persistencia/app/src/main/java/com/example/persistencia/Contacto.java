package com.example.persistencia;

public class Contacto {
    private int id;
    private String nombre;
    private String telefono;
    private int imagenid;

    public Contacto (int id, String nombre, String telefono, int imagenid) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.imagenid = imagenid;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public int getImagenId() {
        return imagenid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }
}
