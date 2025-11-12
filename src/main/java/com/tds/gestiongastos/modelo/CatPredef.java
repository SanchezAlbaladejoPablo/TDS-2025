package com.tds.gestiongastos.modelo;

public enum CatPredef {

	ALIMENTACION("Alimentacion"), TRANSPORTE("Transporte"), ENTRETENIMIENTO("Entretenimiento");
	
	private String nombre;
	
	
	CatPredef(String nombre) {
		this.nombre = nombre;
	}


	public String getNombre() {
		return nombre;
	}
	
	
}
