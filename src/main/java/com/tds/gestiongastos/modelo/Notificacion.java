package com.tds.gestiongastos.modelo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Notificacion {

    @JsonProperty("mensaje")
    private String mensaje;

    @JsonProperty("fecha")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;

    @JsonProperty("leida")
    private boolean leida;

    // Constructor vacío para Jackson
    public Notificacion() { }

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
        this.fecha = LocalDateTime.now();
        this.leida = false;
    }

    public String getMensaje() { return mensaje; }
    public LocalDateTime getFecha() { return fecha; }
    public boolean isLeida() { return leida; }

    public void marcarComoLeida() {
        this.leida = true;
    }

    @Override
    public String toString() {
        return "Notificacion [" + fecha + "] " + mensaje
            + (leida ? " (leída)" : " (no leída)");
    }
}