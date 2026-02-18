package com.tds.gestiongastos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorGastos {

    private static GestorGastos instance;
    private List<Cuenta> cuentas;

    private GestorGastos() {
        this.cuentas = new ArrayList<>();
    }

    public static GestorGastos getInstance() {
        if (instance == null) {
            instance = new GestorGastos();
        }
        return instance;
    }

    public void añadirCuenta(Cuenta cuenta) {
        if (cuenta != null) {
            cuentas.add(cuenta);
        }
    }

    public boolean eliminarCuenta(Cuenta cuenta) {
        return cuentas.remove(cuenta);
    }

    public List<Cuenta> obtenerCuentas() {
        return new ArrayList<>(cuentas);
    }

    public List<Gasto> obtenerGastosPorPersona(Persona persona) {
        return cuentas.stream()
            .flatMap(c -> c.obtenerGastosPorPersona(persona).stream())
            .collect(Collectors.toList());
    }

    public double calcularTotalPorPersona(Persona persona) {
        return obtenerGastosPorPersona(persona).stream()
            .mapToDouble(Gasto::getValor)
            .sum();
    }
}