package com.udea.festivos.service.dto;

public class FestivoDTO {

    private String nombre;
    private String fecha;
    private String tipo;

    public FestivoDTO() {
    }

    public FestivoDTO(String nombre, String fecha, String tipo) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
