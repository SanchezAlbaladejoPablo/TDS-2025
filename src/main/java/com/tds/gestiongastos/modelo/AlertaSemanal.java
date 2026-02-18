package com.tds.gestiongastos.modelo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AlertaSemanal implements EstrategiaAlerta {

	// Constructor vacío para Jackson
	public AlertaSemanal() {
	}

	@Override
	@JsonIgnore
	public double calcularGastoEnPeriodo(List<Gasto> gastos) {
		LocalDateTime inicioSemana = LocalDateTime.now().minusWeeks(1);
		return gastos.stream().filter(g -> g.getFecha().isAfter(inicioSemana)).mapToDouble(Gasto::getValor).sum();
	}

	@Override
	@JsonIgnore
	public String getDescripcion() {
		return "Semanal";
	}
}