package com.Prueba.Peliculas.dao.directores;

import com.Prueba.Peliculas.entities.Director;

import java.util.List;

public interface DAODirectores {
    List<Director> getDirectores();
    Director getDirector(int id);
    void insertDirector(Director director);
    Director modificarDirector(int idAntiguo, Director nuevo);
    void eliminarDirector(Director director);
    int getId(String nombre);
}
