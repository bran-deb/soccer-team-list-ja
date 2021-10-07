package org.bpadilla.java.jdbc;

import org.bpadilla.java.jdbc.modelo.Team;
import org.bpadilla.java.jdbc.repositorio.TeamRepositorioImpl;
import org.bpadilla.java.jdbc.repositorio.Repositorio;
import org.bpadilla.java.jdbc.util.ConexionBasedatos;

import java.sql.*;

public class EjemploJdbc {
    public static void main(String[] args) {
        //auto close conn, stmt, resultado dentro del try para hacer una sola connection
        try (Connection conn = ConexionBasedatos.getInstance()){
            //listamos el repositorio
            Repositorio<Team> repositorio = new TeamRepositorioImpl();
            repositorio.listar().forEach(p-> System.out.println(p.getNombre()));

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
