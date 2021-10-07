package org.bpadilla.java.jdbc.repositorio;

import jdk.jshell.Snippet;
import org.bpadilla.java.jdbc.modelo.Team;
import org.bpadilla.java.jdbc.util.ConexionBasedatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRepositorioImpl implements Repositorio<Team> {

    private Connection getConnection() throws SQLException {
        return ConexionBasedatos.getInstance();
    }


    @Override
    public List<Team> listar() {
        List<Team> equipos = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement();                         //no cerramos el connect conn
             ResultSet rs = stmt.executeQuery("SELECT * FROM teams")) {

            while (rs.next()) {
                Team t = crearTeam(rs);
                equipos.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipos;
    }


    @Override
    public Team porId(Long id) {
        Team team = null;
        try (PreparedStatement stmt = getConnection().                        //creamos el stmt con la plantilla
                prepareStatement("SELECT * FROM teams WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {                        //usamos try anidado para usar autoClose
                if (rs.next()) {
                    team = crearTeam(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }


    @Override
    public void guardar(Team team) {
        String sql;
        if (team.getId() != null && team.getId() > 0) {
            sql = "UPDATE teams SET nombre=?,valor=? WHERE id=?";                 //update

        } else {
            sql = "INSERT INTO teams(nombre,valor) VALUES(?,?)";                 //insert

        }
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, team.getNombre());
            stmt.setInt(2,team.getValor());

            if (team.getId() != null && team.getId() > 0){
                stmt.setLong(3,team.getId());
            }

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void eliminar(Long id) {
        try (PreparedStatement stmt = getConnection().prepareStatement("DELETE FROM teams WHERE id=?")){
            stmt.setLong(1,id);
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //method para reutilizar crearTeam
    private Team crearTeam(ResultSet rs) throws SQLException {
        Team t = new Team();
        t.setId(rs.getLong("id"));
        t.setNombre(rs.getString("nombre"));
        t.setValor(rs.getInt("valor"));
        return t;
    }
}