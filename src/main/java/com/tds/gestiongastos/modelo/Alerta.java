package com.tds.gestiongastos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Alerta {

    @JsonProperty("limite")
    private double limite;

    @JsonProperty("categoria")
    private Categoria categoria;

    @JsonProperty("estrategia")
    private EstrategiaAlerta estrategia;

    @JsonProperty("notificaciones")
    private List<Notificacion> notificaciones;

    // Constructor vacío para Jackson
    public Alerta() {
        this.notificaciones = new ArrayList<>();
    }

    public Alerta(double limite, EstrategiaAlerta estrategia, Categoria categoria) {
        this.limite = limite;
        this.estrategia = estrategia;
        this.categoria = categoria;
        this.notificaciones = new ArrayList<>();
    }

    public Alerta(double limite, EstrategiaAlerta estrategia) {
        this(limite, estrategia, null);
    }

    public void comprobar(List<Gasto> gastos) {
        List<Gasto> gastosFiltrados = gastos;
        if (categoria != null) {
            gastosFiltrados = gastos.stream()
                .filter(g -> g.getCategoria().equals(categoria))
                .collect(Collectors.toList());
        }

        double total = estrategia.calcularGastoEnPeriodo(gastosFiltrados);

        if (total > limite) {
            String mensaje = String.format(
                "Alerta %s: Has superado el límite de %.2f€%s. Gasto actual: %.2f€",
                estrategia.getDescripcion(),
                limite,
                categoria != null ? " en " + categoria.getNombre() : "",
                total
            );
            notificaciones.add(new Notificacion(mensaje));
        }
    }

    public List<Notificacion> getNotificaciones() {
        return new ArrayList<>(notificaciones);
    }

    public double getLimite() { return limite; }
    public Categoria getCategoria() { return categoria; }
    public EstrategiaAlerta getEstrategia() { return estrategia; }
}