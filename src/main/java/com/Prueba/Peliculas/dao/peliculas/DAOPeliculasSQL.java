package com.Prueba.Peliculas.dao.peliculas;

import com.Prueba.Peliculas.context.DBConnection;
import com.Prueba.Peliculas.dao.DAOFactory;
import com.Prueba.Peliculas.entities.Genero;
import com.Prueba.Peliculas.entities.Pelicula;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                Pelicula pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), null, Genero.valueOf(rs.getString("genero")), rs.getInt("año"));
                pelicula.setDirector(DAOFactory.getInstance().getDaoDirectores().getDirector(rs.getInt("id_director")));
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
                pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), null, Genero.valueOf(rs.getString("genero")), rs.getInt("año"));
                pelicula.setDirector(DAOFactory.getInstance().getDaoDirectores().getDirector(rs.getInt("id_director")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pelicula;
    }

    @Override
    public void insertPelicula(Pelicula pelicula) {
        String query= "insert into Peliculas values(?, ?, ?, ?)";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setInt(1, pelicula.getDirector().getId());
            statement.setString(2, pelicula.getTitulo());
            statement.setInt(3, pelicula.getAño());
            statement.setString(4, pelicula.getGenero().toString());
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
            statement.setInt(3, nueva.getAño());
            statement.setString(4, nueva.getGenero().toString());
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
    public List<Pelicula> getPeliculasTitulo(String titulo) {
        List<Pelicula> peliculas= new ArrayList<>();
        Pelicula pelicula = null;
        String query= "select * from Peliculas where titulo like ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setString(1, titulo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), null, Genero.valueOf(rs.getString("genero")), rs.getInt("año"));
                pelicula.setDirector(DAOFactory.getInstance().getDaoDirectores().getDirector(rs.getInt("id_director")));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }

    @Override
    public List<Pelicula> getPeliculasDirector(int idDirector) {
        List<Pelicula> peliculas= new ArrayList<>();
        Pelicula pelicula = null;
        String query= "select * from Peliculas where id_director = ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setInt(1, idDirector);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), null, Genero.valueOf(rs.getString("genero")), rs.getInt("año"));
                pelicula.setDirector(DAOFactory.getInstance().getDaoDirectores().getDirector(rs.getInt("id_director")));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }

    @Override
    public List<Pelicula> getPeliculasAño(int año) {
        List<Pelicula> peliculas= new ArrayList<>();
        Pelicula pelicula = null;
        String query= "select * from Peliculas where año <= ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setInt(1, año);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), null, Genero.valueOf(rs.getString("genero")), rs.getInt("año"));
                pelicula.setDirector(DAOFactory.getInstance().getDaoDirectores().getDirector(rs.getInt("id_director")));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }

    @Override
    public List<Pelicula> getPeliculasGenero(Genero genero) {
        List<Pelicula> peliculas= new ArrayList<>();
        Pelicula pelicula = null;
        String query= "select * from Peliculas where genero = ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setString(1, genero.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                pelicula = new Pelicula(rs.getInt("id_pelicula"), rs.getString("titulo"), null, Genero.valueOf(rs.getString("genero")), rs.getInt("año"));
                pelicula.setDirector(DAOFactory.getInstance().getDaoDirectores().getDirector(rs.getInt("id_director")));
                peliculas.add(pelicula);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }
}
