package com.tds.gestiongastos.persistencia;

public interface Repositorio {

	void guardar(DatosApp datos);

	DatosApp cargar();
}