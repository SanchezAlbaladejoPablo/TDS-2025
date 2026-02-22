package com.tds.gestiongastos.modelo;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Gasto {

	@JsonProperty("id")
	private String id; 

	@JsonProperty("valor")
	private double valor;

	@JsonProperty("persona")
	private Persona persona;

	@JsonProperty("concepto")
	private String concepto;
	
	@JsonProperty("cuenta")
	private Cuenta cuenta; 

	@JsonProperty("fecha")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fecha;

	@JsonProperty("categoria")
	private Categoria categoria;


	public Gasto() {
		this.id = UUID.randomUUID().toString();
	}

	// Constructor normal (fecha = ahora)
	public Gasto(double valor, String concepto, Persona persona, Categoria categoria) {
		this(valor, concepto, persona, categoria, LocalDateTime.now());
	}

	// Constructor con fecha personalizada (para importación)
	public Gasto(double valor, String concepto, Persona persona, Categoria categoria, LocalDateTime fecha) {
		this.id = UUID.randomUUID().toString();
		this.valor = valor;
		this.concepto = concepto;
		this.persona = persona;
		this.categoria = categoria;
		this.fecha = fecha;
	}
	
	//getters
	
	public String getId() {
		return id;
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
	
	public Cuenta getCuenta() {
	    return cuenta;
	}

	//setters
	
	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	public void setCuenta(Cuenta cuenta) {
	    this.cuenta = cuenta;
	}
	
	
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Gasto gasto = (Gasto) o;
		return Objects.equals(id, gasto.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		// He añadido protección contra nulls por si acaso imprimes un gasto incompleto
		String nombrePersona = (persona != null) ? persona.getUsuario() : "Desconocido";
		String nombreCategoria = (categoria != null) ? categoria.getNombre() : "Sin categoría";
		
		return "Gasto [id=" + id + ", concepto=" + concepto + ", valor=" + valor + ", persona=" + nombrePersona 
				+ ", fecha=" + fecha + ", categoria=" + nombreCategoria + "]";
	}
}