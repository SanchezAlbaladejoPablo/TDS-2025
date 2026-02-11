package com.tds.gestiongastos.modelo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CuentaCompartida extends Cuenta {

    private final Map<Persona, Double> participantes;

    // Constructor con porcentajes personalizados
    public CuentaCompartida(String nombreCuenta, Persona titular, 
                            Map<Persona, Double> porcentajePorParticipante) {
        super(nombreCuenta, titular);
        double suma = porcentajePorParticipante.values().stream()
            .mapToDouble(Double::doubleValue).sum();
        if (Math.abs(suma - 100.0) > 0.0001) {
            throw new IllegalArgumentException(
                "La suma de los porcentajes debe ser 100");
        }
        this.participantes = new HashMap<>(porcentajePorParticipante);
    }

    // Constructor con distribución equitativa
    public CuentaCompartida(String nombreCuenta, Persona titular, 
                            List<Persona> personas) {
        super(nombreCuenta, titular);
        double porcentaje = 100.0 / personas.size();
        this.participantes = new HashMap<>();
        personas.forEach(p -> participantes.put(p, porcentaje));
    }

    public Map<Persona, Double> getParticipantes() {
        return Collections.unmodifiableMap(participantes);
    }

    @Override
    public void añadirGasto(Gasto gasto) {
        if (gasto != null && participantes.containsKey(gasto.getPersona())) {
            super.añadirGasto(gasto);
        } else {
            throw new IllegalArgumentException(
                "El gasto no pertenece a un participante de esta cuenta");
        }
    }

    @Override
    public List<Gasto> obtenerGastosPorPersona(Persona persona) {
        if (!participantes.containsKey(persona)) {
            return List.of();
        }
        return obtenerGastos().stream()
            .filter(g -> g.getPersona().equals(persona))
            .collect(Collectors.toList());
    }

    public Map<Persona, Double> calcularSaldos() {
        double total = calcularTotal();
        Map<Persona, Double> saldos = new HashMap<>();
        for (Map.Entry<Persona, Double> entry : participantes.entrySet()) {
            Persona persona = entry.getKey();
            double porcentaje = entry.getValue();
            double pagado = obtenerGastos().stream()
                .filter(g -> g.getPersona().equals(persona))
                .mapToDouble(Gasto::getValor)
                .sum();
            double deberiaHaberPagado = total * porcentaje / 100.0;
            saldos.put(persona, pagado - deberiaHaberPagado);
        }
        return saldos;
    }
}