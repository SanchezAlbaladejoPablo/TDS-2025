package com.tds.gestiongastos.modelo;

public class Categoria {

	private String nombre;
	private final TipoCate tipo;
	private boolean activa;

	
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
	public String toString() {
		return "Categoria [nombre=" + nombre + ", tipo=" + tipo + ", activa=" + activa + "]";
	}

}
