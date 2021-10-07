package org.bpadilla.java.jdbc.modelo;

//esta clase representa los datos de a tabla
public class Team {
    private Long id;
    private String nombre;
    private Integer valor;

    public Team() {
    }

    public Team(Long id, String nombre, Integer valor) {
        this.id = id;
        this.nombre = nombre;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }
}
