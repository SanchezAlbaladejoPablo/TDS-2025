package com.tds.gestiongastos.modelo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AlertaMensual implements EstrategiaAlerta {

    // Constructor vacío para Jackson
    public AlertaMensual() { }

    @Override
    @JsonIgnore
    public double calcularGastoEnPeriodo(List<Gasto> gastos) {
        LocalDateTime inicioMes = LocalDateTime.now().minusMonths(1);
        return gastos.stream()
            .filter(g -> g.getFecha().isAfter(inicioMes))
            .mapToDouble(Gasto::getValor)
            .sum();
    }

    @Override
    @JsonIgnore
    public String getDescripcion() {
        return "Mensual";
    }
}