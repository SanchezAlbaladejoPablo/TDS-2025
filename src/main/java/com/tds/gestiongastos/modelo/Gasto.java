package com.tds.gestiongastos.modelo;

import java.time.LocalDate;

public class Gasto {

	private final int id;
	private double valor;
	private Persona persona;
	private String articulo;
	private LocalDate fecha;
	
	public Gasto(int id, double valor, Persona persona, String articulo) {
		this.id=id;
		this.persona = persona;
		this.valor = valor;
		this.articulo = articulo;
		this.fecha = LocalDate.now();
	}

	public int getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}

	public Persona getPersona() {
		return persona;
	}

	public String getArticulo() {
		return articulo;
	}

	public LocalDate getFecha() {
		return fecha;
	}	

	
	
	
	
	
}
