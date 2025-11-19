package com.tds.gestiongastos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorGastos {

    private List<Cuenta> cuentas;

    public GestorGastos() {
        this.cuentas = new ArrayList<>();
    }

    // Añadir una cuenta (individual o compartida)
    public void añadirCuenta(Cuenta cuenta) {
        if (cuenta != null) {
            cuentas.add(cuenta);
        }
    }

    // Eliminar cuenta
    public boolean eliminarCuenta(Cuenta cuenta) {
        return cuentas.remove(cuenta);
    }

    // Obtener todas las cuentas
    public List<Cuenta> obtenerCuentas() {
        return new ArrayList<>(cuentas);
    }

    // Añadir gasto a una cuenta
    public void añadirGastoACuenta(Cuenta cuenta, Gasto gasto) {
        if (cuenta != null && cuentas.contains(cuenta)) {
            cuenta.añadirGasto(gasto);
        }
    }


    // Calcular total por cuenta
    public double calcularTotalCuenta(Cuenta cuenta) {
        if (cuenta != null && cuentas.contains(cuenta)) {
            return cuenta.calcularTotal();
        }
        return 0;
    }

    // Calcular total por participante en cuentas compartidas
    public Map<Persona, Double> calcularTotalPorParticipante(CuentaCompartida cuentaCompartida) {
        if (cuentaCompartida != null && cuentas.contains(cuentaCompartida)) {
            return cuentaCompartida.calcularTotalPorParticipante();
        }
        return Map.of();
    }

    public List<Gasto> obtenerGastosPorPersona(Persona persona) {
        return cuentas.stream()
            .<Gasto>flatMap(cuenta -> { // Indicando el tipo de Stream que flatMap debe manejar
                if (cuenta instanceof CuentaCompartida) {
                    CuentaCompartida cc = (CuentaCompartida) cuenta;
                    
                    if (cc.getParticipantes().containsKey(persona)) {
                        final double factor = cc.getParticipantes().get(persona) / 100.0;

                        return cc.obtenerGastos().stream()
                            .map(gasto -> {
                                double valorAjustado = gasto.getValor() * factor;
                                Gasto gastoAjustado = new Gasto(
                                        valorAjustado, gasto.getConcepto(),
                                        gasto.getPersona(), gasto.getCategoria()
                                );
                                gastoAjustado.setFecha(gasto.getFecha());
                                return gastoAjustado;
                            });
                    }
                } else if (cuenta.getTitular().equals(persona)) {
                    return cuenta.obtenerGastos().stream();
                }
                return Stream.empty(); // Ahora el tipo se infiere correctamente
            })
            .collect(Collectors.toList());
    }

    public double calcularTotalPorPersona(Persona persona) {
        return cuentas.stream()
            .mapToDouble((Cuenta cuenta) -> {
                if (cuenta instanceof CuentaCompartida) {
                    CuentaCompartida cc = (CuentaCompartida) cuenta;
                    if (cc.getParticipantes().containsKey(persona)) {
                        double porcentaje = cc.getParticipantes().get(persona);
                        return cc.obtenerGastos().stream()
                            .mapToDouble(gasto -> gasto.getValor() * porcentaje / 100.0)
                            .sum();
                    }
                } else if (cuenta.getTitular().equals(persona)) {
                    return cuenta.obtenerGastos().stream()
                        .mapToDouble(Gasto::getValor)
                        .sum();
                }
                return 0.0;
            })
            .sum();
    }

    
    
}
