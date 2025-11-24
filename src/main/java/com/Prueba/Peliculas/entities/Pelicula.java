package com.Prueba.Peliculas.entities;

import java.sql.Date;

public class Pelicula {
    private int id;
    public String titulo;
    public Director director;
    public Genero genero;
    public int año;

    public Pelicula() {
    }

    public Pelicula id(int id){
        this.id=id;
        return this;
    }
    public Pelicula titulo(String titulo){
        this.titulo=titulo;
        return this;
    }
    public Pelicula director(Director director){
        this.director=director;
        return this;
    }
    public Pelicula genero(Genero genero){
        this.genero=genero;
        return this;
    }
    public Pelicula año(int año){
        this.año=año;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Director getDirector() {
        return director;
    }

    public Genero getGenero() {
        return genero;
    }

    public int getAño() {
        return año;
    }
}
