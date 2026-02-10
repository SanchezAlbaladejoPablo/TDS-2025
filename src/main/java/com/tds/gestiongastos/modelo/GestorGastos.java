package com.tds.gestiongastos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

  //Obtener los gastos de cada miembro de la cuenta
    public List<Gasto> obtenerGastosPorPersona(Persona p){
    	return cuentas.stream()
                .flatMap(cuenta -> cuenta.obtenerGastos().stream())
                .filter(gasto -> gasto.getPersona().equals(p))
                .collect(Collectors.toList());
    }

    
    
}
