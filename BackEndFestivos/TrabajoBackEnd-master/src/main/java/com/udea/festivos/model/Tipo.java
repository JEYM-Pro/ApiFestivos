package com.udea.festivos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Tipo {
    @Id
    private Integer id;
    private String tipo;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}