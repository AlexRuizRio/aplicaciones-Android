package com.example.gramola;

public class Cancion {
    private String titulo;
    private String autor;
    private String disco;
    private int anio;
    private String url;

    public Cancion (String titulo, String autor, String disco, int anio, String url){
        this.titulo = titulo;
        this.autor = autor;
        this.disco = disco;
        this.anio = anio;
        this.url = url;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getDisco() {
        return disco;
    }

    public int getAnio() {
        return anio;
    }

    public String getUrl() {
        return url;
    }
}
