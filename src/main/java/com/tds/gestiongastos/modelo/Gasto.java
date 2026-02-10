package com.tds.gestiongastos.modelo;

import java.time.LocalDateTime;

public class Gasto {

	private final double valor;
	private final Persona persona;
	private final String concepto;
	private final LocalDateTime fecha;
	private final Categoria categoria;
	
	public Gasto( double valor, String concepto, Persona p, Categoria categoria) {
		this.valor = valor;
		this.concepto = concepto;
		this.fecha = LocalDateTime.now();
		this.persona = p;
		this.categoria = categoria;
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
		return "Gasto [concepto=" + concepto + ", valor=" + valor + ", persona=" + persona.getUsuario() 
				+ ", fecha=" + fecha + ", categoria=" + categoria + "]";
	}

	
}
