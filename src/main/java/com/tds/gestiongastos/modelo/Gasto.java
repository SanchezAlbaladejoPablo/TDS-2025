package com.tds.gestiongastos.modelo;

import java.time.LocalDateTime;

public class Gasto {

	private double valor;
	private Persona persona;
	private String articulo;
	private LocalDateTime fecha;
	private Categoria categoria;
	
	public Gasto( double valor, String articulo, Persona p, Categoria categoria) {
		this.valor = valor;
		this.articulo = articulo;
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

	public String getArticulo() {
		return articulo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}


	@Override
	public String toString() {
		return "Gasto [articulo=" + articulo + ", valor=" + valor + ", persona=" + persona.getUsuario() 
				+ ", fecha=" + fecha + ", categoria=" + categoria + "]";
	}

	
}
