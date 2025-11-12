package com.tds.gestiongastos.modelo;

public class Categoria {

	protected String nombre;
	protected String descripcion;
	protected int id_usuario;

	public Categoria(String nombre, String descripcion, int id_usuario) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.id_usuario = id_usuario;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public int getId_usuario() {
		return id_usuario;
	}

	@Override
	public String toString() {
		return "Categoria [nombre=" + nombre + ", descripcion=" + descripcion + ", id_usuario=" + id_usuario + "]";
	}


	
	
}
