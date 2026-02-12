package com.tds.gestiongastos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Alerta {

    private final double limite;
    private final Categoria categoria; // puede ser null (alerta general)
    private final EstrategiaAlerta estrategia;
    private final List<Notificacion> notificaciones;

    public Alerta(double limite, EstrategiaAlerta estrategia, Categoria categoria) {
        this.limite = limite;
        this.estrategia = estrategia;
        this.categoria = categoria;
        this.notificaciones = new ArrayList<>();
    }

    // Constructor para alerta sin categoría
    public Alerta(double limite, EstrategiaAlerta estrategia) {
        this(limite, estrategia, null);
    }

    public void comprobar(List<Gasto> gastos) {
        // Si hay categoría, filtrar solo esos gastos
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
