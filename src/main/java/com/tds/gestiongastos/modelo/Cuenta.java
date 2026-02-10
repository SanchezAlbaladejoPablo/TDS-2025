package com.tds.gestiongastos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cuenta {

    private String nombreCuenta;
    private Persona titular;
    private List<Gasto> gastos;

    public Cuenta(String nombreCuenta, Persona titular) {
        this.nombreCuenta = nombreCuenta;
        this.titular = titular;
        this.gastos = new ArrayList<>();
    }

    // Añadir gasto
    public void añadirGasto(Gasto gasto) {
        if (gasto != null) {
            gastos.add(gasto);
        }
    }

    // Eliminar gasto
    public boolean eliminarGasto(Gasto gasto) {
        return gastos.remove(gasto);
    }

    // Obtener lista de gastos
    public List<Gasto> obtenerGastos() {
        return new ArrayList<>(gastos);
    }
    
    
    // Calcular total de gastos
    public double calcularTotal() {
        return gastos.stream().mapToDouble(Gasto::getValor).sum();
    }
    
    public double calcularTotalPorPersona(Persona persona) {
        if (!titular.equals(persona)) {
            return 0.0;
        }
        return calcularTotal();
    }

    // Getters
    public String getNombreCuenta() { return nombreCuenta; }
    public Persona getTitular() { return titular; }
    
    
}

