package org.bpadilla.java.jdbc;

import org.bpadilla.java.jdbc.modelo.Team;
import org.bpadilla.java.jdbc.repositorio.Repositorio;
import org.bpadilla.java.jdbc.repositorio.TeamRepositorioImpl;
import org.bpadilla.java.jdbc.util.ConexionBasedatos;

import java.sql.Connection;
import java.sql.SQLException;

public class UsaDBDelete {
    public static void main(String[] args) {
        //auto close conn, stmt, resultado dentro del try para hacer una sola connection (solo define recursos que usen autoClose)
        try (Connection conn = ConexionBasedatos.getInstance()){
            //listamos el repositorio
            Repositorio<Team> repositorio = new TeamRepositorioImpl();


            //listar tabla completa
            System.out.println("==========Listar==========");
            repositorio.listar().forEach(System.out::println);

            //insert team
            System.out.println("======Insertar team========");
            repositorio.eliminar(5L);
            System.out.println("Team eliminado con exito");
            repositorio.listar().forEach(System.out::println);

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
