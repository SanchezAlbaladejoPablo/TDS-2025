package com.tds.gestiongastos.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo_estrategia")
@JsonSubTypes({ @JsonSubTypes.Type(value = AlertaSemanal.class, name = "semanal"),
		@JsonSubTypes.Type(value = AlertaMensual.class, name = "mensual") })
public interface EstrategiaAlerta {

	double calcularGastoEnPeriodo(List<Gasto> gastos);

	String getDescripcion();
}