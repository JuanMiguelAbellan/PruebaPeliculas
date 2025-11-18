package com.Prueba.Peliculas.dao.directores;

import com.Prueba.Peliculas.context.DBConnection;
import com.Prueba.Peliculas.entities.Director;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAODirectoresSQL implements DAODirectores{
    @Override
    public List<Director> getDirectores() {
        List<Director> directors= new ArrayList<>();
        String query= "select * from Directores";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Director director = new Director(rs.getString("nombre"), rs.getInt("edad"));
                director.setId(rs.getInt("id_director"));
                directors.add(director);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return directors;
    }

    @Override
    public Director getDirector(int id) {
        Director director = null;
        String query= "select * from Directores where id_director = ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                 director = new Director(rs.getString("nombre"), rs.getInt("edad"));
                director.setId(rs.getInt("id_director"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return director;
    }

    @Override
    public void insertDirector(Director director) {
        String query= "insert into Directores values(?, ?)";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setString(1, director.getNombre());
            statement.setInt(2, director.getEdad());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Director modificarDirector(int idAntiguo, Director nuevo) {
        String query= "update table Directores set nombre = ?, edad = ? where id_director = ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setString(1, nuevo.getNombre());
            statement.setInt(2, nuevo.getEdad());
            statement.setInt(3, idAntiguo);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getDirector(idAntiguo);
    }

    @Override
    public void eliminarDirector(Director director) {
        String query= "delete * from Directores where id_director = ?";

        try{
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(query);
            statement.setInt(1, director.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
