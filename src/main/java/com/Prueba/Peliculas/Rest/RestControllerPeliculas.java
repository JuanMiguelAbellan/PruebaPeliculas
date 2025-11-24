package com.Prueba.Peliculas.Rest;

import com.Prueba.Peliculas.dao.DAOFactory;
import com.Prueba.Peliculas.entities.Genero;
import com.Prueba.Peliculas.entities.Pelicula;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestControllerPeliculas {
    @GetMapping("/api/peliculas")
    public List<Pelicula> lista(@RequestBody(required = false) Pelicula filtro){
        if(filtro == null) filtro = new Pelicula();
        return DAOFactory.getInstance().getDaoPeliculas().filtroPeliculas(filtro.getTitulo(), filtro.getDirector(), filtro.getAño(), filtro.getGenero());
    }


    @PostMapping("/api/peliculas")
    public Pelicula insertarPelicula(@RequestBody Pelicula pelicula){
        DAOFactory.getInstance().getDaoPeliculas().insertPelicula(pelicula);
        return pelicula;
    }


}
