package com.tds.gestiongastos.modelo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Gasto {

	@JsonProperty("valor")
	private double valor;

	@JsonProperty("persona")
	private Persona persona;

	@JsonProperty("concepto")
	private String concepto;

	@JsonProperty("fecha")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fecha;

	@JsonProperty("categoria")
	private Categoria categoria;

	// Constructor vacío para Jackson
	public Gasto() {
	}

	// Constructor normal (fecha = ahora)
	public Gasto(double valor, String concepto, Persona persona, Categoria categoria) {
		this(valor, concepto, persona, categoria, LocalDateTime.now());
	}

	// Constructor con fecha personalizada (para importación)
	public Gasto(double valor, String concepto, Persona persona, Categoria categoria, LocalDateTime fecha) {
		this.valor = valor;
		this.concepto = concepto;
		this.persona = persona;
		this.categoria = categoria;
		this.fecha = fecha;
	}

	public double getValor() {
		return valor;
	}

	public Persona getPersona() {
		return persona;
	}

	public String getConcepto() {
		return concepto;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		return "Gasto [concepto=" + concepto + ", valor=" + valor + ", persona=" + persona.getUsuario() + ", fecha="
				+ fecha + ", categoria=" + categoria + "]";
	}
}