package com.Prueba.Peliculas.dao.peliculas;

import com.Prueba.Peliculas.context.DBConnection;
import com.Prueba.Peliculas.dao.DAOFactory;
import com.Prueba.Peliculas.entities.Director;
import com.Prueba.Peliculas.entities.Genero;
import com.Prueba.Peliculas.entities.Pelicula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOPeliculasSQL implements DAOPeliculas{
    @Override
    public List<Pelicula> getPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        String query= "select * from Peliculas";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Pelicula pelicula = new Pelicula();
                pelicula
                        .director(DAOFactory.getInstance().getDaoDirectores().getDirector(rs.getInt("id_director")))
                        .año(rs.getInt("año"))
                        .titulo(rs.getString("titulo"))
                        .genero(Genero.valueOf(rs.getString("genero")));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }

    @Override
    public Pelicula getPelicula(int id) {
        Pelicula pelicula = null;
        String query= "select * from Peliculas where id_pelicula = ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pelicula = new Pelicula();
                pelicula
                        .director(DAOFactory.getInstance().getDaoDirectores().getDirector(rs.getInt("id_director")))
                        .año(rs.getInt("año"))
                        .titulo(rs.getString("titulo"))
                        .genero(Genero.valueOf(rs.getString("genero")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pelicula;
    }

    @Override
    public void insertPelicula(Pelicula pelicula) {
        String query= "insert into Peliculas(id_director, titulo, año, genero) values(?, ?, ?, ?)";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setInt(1, pelicula.getDirector().getId());
            statement.setString(2, pelicula.getTitulo());
            statement.setInt(3, pelicula.año);
            statement.setString(4, pelicula.genero.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pelicula modificarPelicula(int idAntigua, Pelicula nueva) {
        String query= "update table Peliculas set id_director = ?, titulo = ?, año = ?, genero = ? where id_pelicula = ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setInt(1, nueva.getDirector().getId());
            statement.setString(2, nueva.getTitulo());
            statement.setInt(3, nueva.año);
            statement.setString(4, nueva.genero.toString());
            statement.setInt(5, idAntigua);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getPelicula(idAntigua);
    }

    @Override
    public void eliminarPelicula(Pelicula pelicula) {
        String query= "delete * from Peliculas where id_pelicula = ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setInt(1, pelicula.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Pelicula> filtroPeliculas(String titulo, Director director, Integer año, Genero genero){
        List<Pelicula> peliculas= new ArrayList<>();
        Pelicula pelicula = null;
        StringBuilder query = new StringBuilder("select  * from Peliculas where 1=1");
        List<Object> params = new ArrayList<>();
        if (titulo !=null && !titulo.isEmpty()){
            query.append(" and titulo like ?");
            params.add("%" + titulo + "%");
        }
        if (director != null){
            director = DAOFactory.getInstance().getDaoDirectores().getDirectorNombre(director.getNombre());
            query.append(" and id_director = ?");
            params.add(director.getId());
        }
        if (año != null && año != 0){
            query.append(" and año = ?");
            params.add(año);
        }
        if(genero != null && !genero.toString().isEmpty()){
            query.append(" and genero = ?");
            params.add(genero.toString());
        }

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query.toString());
            for(int i =0; i < params.size();i++){
                statement.setObject(i+1, params.get(i));
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pelicula = new Pelicula();
                pelicula
                        .director(DAOFactory.getInstance().getDaoDirectores().getDirector(rs.getInt("id_director")))
                        .año(rs.getInt("año"))
                        .titulo(rs.getString("titulo"))
                        .genero(Genero.valueOf(rs.getString("genero")));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }
}
