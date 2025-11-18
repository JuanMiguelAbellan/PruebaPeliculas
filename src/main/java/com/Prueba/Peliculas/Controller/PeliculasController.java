package com.Prueba.Peliculas.Controller;

import com.Prueba.Peliculas.dao.DAOFactory;
import com.Prueba.Peliculas.dao.peliculas.DAOPeliculas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PeliculasController {

    @GetMapping("/")
    public String verIndex(Model model){
        DAOFactory.getInstance().getDaoPeliculas().getPeliculas();
        return "index";
    }

    @PostMapping("/peliculas/filtro")
    public String peliculasFiltro(@RequestParam String titulo, @RequestParam String director, @RequestParam String año, @RequestParam String genero, Model model){
        DAOFactory.getInstance().getDaoPeliculas().getPeliculas();
        return "index";
    }
}
