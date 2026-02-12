package com.tds.gestiongastos.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class AlertaMensual implements EstrategiaAlerta {

    @Override
    public double calcularGastoEnPeriodo(List<Gasto> gastos) {
        LocalDateTime inicioMes = LocalDateTime.now().minusMonths(1);
        return gastos.stream()
            .filter(g -> g.getFecha().isAfter(inicioMes))
            .mapToDouble(Gasto::getValor)
            .sum();
    }

    @Override
    public String getDescripcion() {
        return "Mensual";
    }
}