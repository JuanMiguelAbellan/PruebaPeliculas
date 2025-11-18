package com.Prueba.Peliculas.dao;

import com.Prueba.Peliculas.dao.directores.DAODirectores;
import com.Prueba.Peliculas.dao.directores.DAODirectoresSQL;
import com.Prueba.Peliculas.dao.peliculas.DAOPeliculas;
import com.Prueba.Peliculas.dao.peliculas.DAOPeliculasSQL;

public class DAOFactory {
    private static final DAOFactory daoFactory = new DAOFactory();
    private DAOPeliculas daoPeliculas;
    private DAODirectores daoDirectores;

    private DAOFactory(){}

    public static DAOFactory getInstance() {
        return daoFactory;
    }

    public DAODirectores getDaoDirectores(){
        if(daoDirectores == null){
            daoDirectores = new DAODirectoresSQL();
        }
        return daoDirectores;
    }

    public DAOPeliculas getDaoPeliculas(){
        if(daoPeliculas == null){
            daoPeliculas = new DAOPeliculasSQL();
        }
        return daoPeliculas;
    }
}
