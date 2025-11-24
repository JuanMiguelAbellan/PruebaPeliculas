package com.Prueba.Peliculas.Rest;

import com.Prueba.Peliculas.dao.DAOFactory;
import com.Prueba.Peliculas.entities.Director;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestControllerDirectores {

    @GetMapping("/api/directores")
    public List<Director> lista(){
        return DAOFactory.getInstance().getDaoDirectores().getDirectores();
    }
}
