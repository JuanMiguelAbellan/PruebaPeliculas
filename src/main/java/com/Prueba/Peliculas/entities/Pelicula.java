package com.Prueba.Peliculas.entities;

import java.sql.Date;

public class Pelicula {
    private int id;
    public String titulo;
    public Director director;
    public Genero genero;
    public int año;

    public Pelicula(int id, String titulo, Director director, Genero genero, int año) {
        this.id=id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
        this.año = año;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }
}
