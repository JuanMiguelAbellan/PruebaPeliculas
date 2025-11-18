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
    List<Pelicula> getPeliculasTitulo(String titulo);
    List<Pelicula> getPeliculasDirector(int idDirector);
    List<Pelicula> getPeliculasAño(int año);
    List<Pelicula> getPeliculasGenero(Genero genero);
}
