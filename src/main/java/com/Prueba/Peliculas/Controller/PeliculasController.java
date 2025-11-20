package com.Prueba.Peliculas.Controller;

import com.Prueba.Peliculas.dao.DAOFactory;
import com.Prueba.Peliculas.dao.peliculas.DAOPeliculas;
import com.Prueba.Peliculas.entities.Director;
import com.Prueba.Peliculas.entities.Genero;
import com.Prueba.Peliculas.entities.Pelicula;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PeliculasController {

    @GetMapping("/")
    public String verIndex(Model model){
        List<Pelicula> peliculas= DAOFactory.getInstance().getDaoPeliculas().getPeliculas();
        List<Director> directorList = DAOFactory.getInstance().getDaoDirectores().getDirectores();

        model.addAttribute("directores", directorList);
        model.addAttribute("peliculas", peliculas);
        return "index";
    }

    @PostMapping("/peliculas/filtro")
    public String peliculasFiltro(@RequestParam(required = false) String titulo,
                                  @RequestParam(required = false) String director,
                                  @RequestParam(required = false) String año,
                                  @RequestParam(required = false) String genero,
                                  Model model){
        int idDirector = DAOFactory.getInstance().getDaoDirectores().getId(director);
        int añoInt = 0;
        if(año != ""){
            añoInt = Integer.parseInt(año);
        }
        Genero generoString=null;
        if (genero != ""){
            generoString = Genero.valueOf(genero);
        }
        List<Pelicula> peliculaList= DAOFactory.getInstance().getDaoPeliculas().filtroPeliculas(titulo, idDirector, añoInt, generoString);
        List<Director> directorList = DAOFactory.getInstance().getDaoDirectores().getDirectores();

        model.addAttribute("directores", directorList);
        model.addAttribute( "peliculas", peliculaList);
        return "index";
    }
}
