package com.grupo2.restaurante_inteligente;

public class Mesa {

    private final int imagen;
    private final String id;
    private final String capacidad;
    private final String estado;

    public Mesa(String id, String capacidad, String estado, int imagen) {
        this.imagen = imagen;
        this.id = id;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Mesa(String id, String capacidad, String estado) {
        this.id = id;
        this.capacidad = capacidad;
        this.estado = estado;
        imagen = 0;
    }

    public int getImagen() {
        return imagen;
    }

    public String getId() {
        return id;
    }
    
    public String getCapacidad() {
        return capacidad;
    }

    public String getEstado() {
        return estado;
    }
}
