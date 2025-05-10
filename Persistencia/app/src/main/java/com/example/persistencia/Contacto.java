package com.example.persistencia;

public class Contacto {
    private int id;
    private String nombre;
    private String descripcion;
    private int imagenid;

    public Contacto (int id, String nombre, String descripcion, int imagenid) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenid = imagenid;
    }

    public Contacto (String nombre, String descripcion, int imagenid) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenid = imagenid;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
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

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }
}
