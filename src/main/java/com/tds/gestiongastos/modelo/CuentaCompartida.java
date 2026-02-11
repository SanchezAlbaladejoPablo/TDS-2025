package com.tds.gestiongastos.modelo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CuentaCompartida extends Cuenta {

	private final Map<Persona, Double> participantes;

	public CuentaCompartida(String nombreCuenta, Persona titular, Map<Persona, Double> porcentajePorParticipante) {
		super(nombreCuenta, titular);

		double suma = porcentajePorParticipante.values().stream().mapToDouble(Double::doubleValue).sum();
		if (Math.abs(suma - 100.0) > 0.0001) {
			throw new IllegalArgumentException("La suma de los porcentajes debe ser 100");
		}

		this.participantes = new HashMap<>(porcentajePorParticipante);
	}

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

	@Override
	public List<Gasto> obtenerGastosPorPersona(Persona persona) {
		if (!participantes.containsKey(persona)) {
			return List.of();
		}
		return obtenerGastos().stream().filter(g -> g.getPersona().equals(persona)).collect(Collectors.toList());
	}

	public Map<Persona, Double> calcularTotalPorParticipante() {
		double total = calcularTotal();
		Map<Persona, Double> totales = new HashMap<>();
		for (Map.Entry<Persona, Double> entry : participantes.entrySet()) {
			totales.put(entry.getKey(), total * entry.getValue() / 100.0);
		}
		return totales;
	}
}