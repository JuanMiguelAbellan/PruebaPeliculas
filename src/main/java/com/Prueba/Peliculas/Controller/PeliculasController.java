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
        Genero[] generoList= Genero.values();

        model.addAttribute("directores", directorList);
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("generos", generoList);
        return "index";
    }

    @PostMapping("/peliculas/filtro")
    public String peliculasFiltro(@RequestParam(required = false) String titulo,
                                  @RequestParam(required = false) Integer director,
                                  @RequestParam(required = false) Integer año,
                                  @RequestParam(required = false) String genero,
                                  Model model){
        List<Pelicula> peliculas= DAOFactory.getInstance().getDaoPeliculas().filtroPeliculas(titulo, director, año, genero);
        List<Director> directorList = DAOFactory.getInstance().getDaoDirectores().getDirectores();
        Genero[] generoList= Genero.values();

        model.addAttribute("directores", directorList);
        model.addAttribute( "peliculas", peliculas);
        model.addAttribute("generos", generoList);
        return "index";
    }

    @GetMapping("/nueva")
    public String mostrarFormNueva(Model model){
        List<Director> directorList = DAOFactory.getInstance().getDaoDirectores().getDirectores();
        Genero[] generoList= Genero.values();

        model.addAttribute("directores", directorList);
        model.addAttribute("generos", generoList);
        return "nueva";
    }

    @PostMapping("/nueva")
    public String insertarPelicula(@RequestParam(name = "titulo") String titulo,
                                   @RequestParam(name = "año") Integer año,
                                   @RequestParam(name = "director") Integer director,
                                   @RequestParam(name = "genero") String genero) {
        Pelicula pelicula = new Pelicula();
        pelicula.setDirector(DAOFactory.getInstance().getDaoDirectores().getDirector(director));
        pelicula.setAño(año);
        pelicula.setTitulo(titulo);
        pelicula.setGenero(Genero.valueOf(genero));
        DAOFactory.getInstance().getDaoPeliculas().insertPelicula(pelicula);
        return "redirect:/";
    }
}
