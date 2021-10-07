package org.bpadilla.java.jdbc.repositorio;

import jdk.jshell.Snippet;
import org.bpadilla.java.jdbc.modelo.Team;
import org.bpadilla.java.jdbc.util.ConexionBasedatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TeamRepositorioImpl implements Repositorio<Team> {

    private Connection getConnection() throws SQLException {
        return ConexionBasedatos.getInstance();
    }


    @Override
    public List<Team> listar() {
        List<Team> equipos = new ArrayList<>();
        try(Statement stmt = getConnection().createStatement();                         //no cerramos el connect conn
            ResultSet rs = stmt.executeQuery("SELECT * FROM teams")) {

            while(rs.next()){
                Team t = new Team();
                t.setId(rs.getLong("id"));
                t.setNombre(rs.getString("nombre"));
                t.setValor(rs.getInt("valor"));
                equipos.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    @Override
    public Team porId(Long id) {
        return null;
    }

    @Override
    public void guardar(Team team) {

    }

    @Override
    public void eliminar(Long id) {

    }
}