package com.tds.gestiongastos.modelo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CuentaCompartida extends Cuenta {

    private final Map<Persona, Double> participantes; // Participante -> porcentaje

    
    
    public CuentaCompartida(String nombreCuenta, Map<Persona, Double> porcentajePorParticipante) {
        super(nombreCuenta, porcentajePorParticipante.keySet().iterator().next()); // Titular es el primero del mapa
        double suma = porcentajePorParticipante.values().stream().mapToDouble(Double::doubleValue).sum();
        // esto deberia comprobarse antes de llegar aquí, pero nunca se sabe.
        if (Math.abs(suma - 100.0) > 0.0001) {
            throw new IllegalArgumentException("La suma de los porcentajes debe ser 100");
        }
        this.participantes = new HashMap<>(porcentajePorParticipante); // Inmutable después de creación
    }

    // Obtener participantes (inmutable)
    public Map<Persona, Double> getParticipantes() {
        return Collections.unmodifiableMap(participantes);
    }

    @Override
    public void añadirGasto(Gasto gasto) {
        if (gasto != null && participantes.containsKey(gasto.getPersona())) {
            super.añadirGasto(gasto);
        } else {
            throw new IllegalArgumentException("El gasto no pertenece a un participante de esta cuenta");
        }
    }

    /**
     * Calcular total de gastos de la cuenta.
     */
    @Override
    public double calcularTotal() {
        return super.calcularTotal();
    }

    /**
     * Calcular la parte de cada participante según su porcentaje.
     */
    public Map<Persona, Double> calcularTotalPorParticipante() {
        double total = calcularTotal();
        Map<Persona, Double> totales = new HashMap<>();
        for (Persona p : participantes.keySet()) {
            double porcentaje = participantes.get(p);
            totales.put(p, total * porcentaje / 100.0);
        }
        return totales;
    }
}
