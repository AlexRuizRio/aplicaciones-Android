package com.example.aplicacion;

import java.io.Serializable;

public class Receta implements Serializable {

    public String nombre;
    public String imagenUrl;

    public Receta(String nombre, String imagenUrl) {
        this.nombre = nombre;
        this.imagenUrl = imagenUrl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

}
