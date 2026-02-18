package com.tds.gestiongastos.modelo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nombre")
public class Categoria {

	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("tipo")
	private TipoCate tipo;

	@JsonProperty("activa")
	private boolean activa;

	// Constructor vacío para Jackson
	public Categoria() {
	}

	// Categoría predefinida
	public Categoria(CatPredef predefinida) {
		this.nombre = predefinida.getNombre();
		this.activa = true;
		this.tipo = TipoCate.PREDEFINIDA;
	}

	// Categoría personalizada
	public Categoria(String nombre) {
		this.nombre = nombre;
		this.activa = true;
		this.tipo = TipoCate.PERSONALIZADA;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoCate getTipo() {
		return tipo;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(nombre, other.nombre) && tipo == other.tipo;
	}

	@Override
	public String toString() {
		return "Categoria [nombre=" + nombre + ", tipo=" + tipo + ", activa=" + activa + "]";
	}
}