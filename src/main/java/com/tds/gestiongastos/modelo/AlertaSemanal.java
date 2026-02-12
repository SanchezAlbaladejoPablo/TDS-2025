package com.tds.gestiongastos.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class AlertaSemanal implements EstrategiaAlerta {

    @Override
    public double calcularGastoEnPeriodo(List<Gasto> gastos) {
        LocalDateTime inicioSemana = LocalDateTime.now().minusWeeks(1);
        return gastos.stream()
            .filter(g -> g.getFecha().isAfter(inicioSemana))
            .mapToDouble(Gasto::getValor)
            .sum();
    }

    @Override
    public String getDescripcion() {
        return "Semanal";
    }
}