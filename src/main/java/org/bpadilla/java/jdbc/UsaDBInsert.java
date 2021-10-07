package org.bpadilla.java.jdbc;

import org.bpadilla.java.jdbc.modelo.Team;
import org.bpadilla.java.jdbc.repositorio.TeamRepositorioImpl;
import org.bpadilla.java.jdbc.repositorio.Repositorio;
import org.bpadilla.java.jdbc.util.ConexionBasedatos;

import java.sql.*;

public class UsaDBInsert {
    public static void main(String[] args) {
        //auto close conn, stmt, resultado dentro del try para hacer una sola connection (solo define recursos que usen autoClose)
        try (Connection conn = ConexionBasedatos.getInstance()){
            //listamos el repositorio
            Repositorio<Team> repositorio = new TeamRepositorioImpl();


            //listar tabla completa
            System.out.println("==========Listar==========");
            repositorio.listar().forEach(System.out::println);

            //listar un elemento
            System.out.println("======Obtener por id=======");
            System.out.println(repositorio.porId(1L));

            //insert team
            System.out.println("======Insertar team========");
            Team team = new Team();
            team.setNombre("España");
            team.setValor(8);
            repositorio.guardar(team);
            System.out.println("Team creado con exito");
            repositorio.listar().forEach(System.out::println);

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
