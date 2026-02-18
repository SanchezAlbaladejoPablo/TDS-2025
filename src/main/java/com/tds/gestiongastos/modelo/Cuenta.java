package com.tds.gestiongastos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo_cuenta")
@JsonSubTypes({ @JsonSubTypes.Type(value = Cuenta.class, name = "individual"),
		@JsonSubTypes.Type(value = CuentaCompartida.class, name = "compartida") })
public class Cuenta {

	@JsonProperty("nombreCuenta")
	private String nombreCuenta;

	@JsonProperty("titular")
	private Persona titular;

	@JsonProperty("gastos")
	private List<Gasto> gastos;

	// Constructor vacío para Jackson
	public Cuenta() {
		this.gastos = new ArrayList<>();
	}

	public Cuenta(String nombreCuenta, Persona titular) {
		this.nombreCuenta = nombreCuenta;
		this.titular = titular;
		this.gastos = new ArrayList<>();
	}

	public void añadirGasto(Gasto gasto) {
		if (gasto != null) {
			gastos.add(gasto);
		}
	}

	public boolean eliminarGasto(Gasto gasto) {
		return gastos.remove(gasto);
	}

	public List<Gasto> obtenerGastos() {
		return new ArrayList<>(gastos);
	}

	public List<Gasto> obtenerGastosPorPersona(Persona persona) {
		return gastos.stream().filter(g -> g.getPersona().equals(persona)).collect(Collectors.toList());
	}

	public double calcularTotal() {
		return gastos.stream().mapToDouble(Gasto::getValor).sum();
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public Persona getTitular() {
		return titular;
	}
}