package com.Prueba.Peliculas.dao.peliculas;

import com.Prueba.Peliculas.entities.Director;
import com.Prueba.Peliculas.entities.Genero;
import com.Prueba.Peliculas.entities.Pelicula;

import java.util.List;

public interface DAOPeliculas {
    List<Pelicula> getPeliculas();
    Pelicula getPelicula(int id);
    void insertPelicula(Pelicula pelicula);
    Pelicula modificarPelicula(int idAntigua, Pelicula nueva);
    void eliminarPelicula(Pelicula pelicula);
    List<Pelicula> filtroPeliculas(String titulo, Integer idDirector, Integer año, String genero);
}
