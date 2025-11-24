package com.Prueba.Peliculas.entities;

public class Director {
    private int id;
    public String nombre;
    public int edad;

    public Director() {
    }

    public Director id(int id){
        this.id=id;
        return this;
    }
    public Director nombre(String nombre){
        this.nombre=nombre;
        return this;
    }
    public Director edad(int edad){
        this.edad=edad;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}
