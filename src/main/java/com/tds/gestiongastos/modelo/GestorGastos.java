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
        List<Gasto> resultado = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            if (cuenta instanceof CuentaCompartida) {
                CuentaCompartida cc = (CuentaCompartida) cuenta;
                // Solo procesar si la persona es participante
                if (cc.getParticipantes().containsKey(persona)) {
                    double porcentaje = cc.getParticipantes().get(persona);
                    for (Gasto gasto : cc.obtenerGastos()) {
                        double valorAjustado = gasto.getValor() * porcentaje / 100.0;
                        Gasto gastoAjustado = new Gasto(
                                valorAjustado,
                                gasto.getConcepto(),
                                gasto.getPersona(),
                                gasto.getCategoria()
                        );
                        gastoAjustado.setFecha(gasto.getFecha());
                        resultado.add(gastoAjustado);
                    }
                }
            } else {
                // Cuenta individual
                if (cuenta.getTitular().equals(persona)) {
                    for (Gasto gasto : cuenta.obtenerGastos()) {
                        resultado.add(gasto);
                    }
                }
            }
        }

        return resultado;
    }

 // Calcula el total de gastos que corresponden a una persona
    public double calcularTotalPorPersona(Persona persona) {
        double total = 0;

        for (Cuenta cuenta : cuentas) {
            if (cuenta instanceof CuentaCompartida) {
                CuentaCompartida cc = (CuentaCompartida) cuenta;
                // Solo procesar si la persona es participante
                if (cc.getParticipantes().containsKey(persona)) {
                    double porcentaje = cc.getParticipantes().get(persona);
                    for (Gasto gasto : cc.obtenerGastos()) {
                        total += gasto.getValor() * porcentaje / 100.0;
                    }
                }
            } else {
                // Cuenta individual
                if (cuenta.getTitular().equals(persona)) {
                    for (Gasto gasto : cuenta.obtenerGastos()) {
                        total += gasto.getValor();
                    }
                }
            }
        }

        return total;
    }


    
    
}
