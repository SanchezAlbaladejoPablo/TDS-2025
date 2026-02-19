package com.tds.gestiongastos.modelo;

import java.util.HashMap;
import java.util.Map;

public class GestorCategoria {

	private static GestorCategoria instance;
	private Map<String, Categoria> categorias;

	private GestorCategoria() {
		categorias = new HashMap<>();
		// Inicializar categorias predefinidas
		for (CatPredef predef : CatPredef.values()) {
			Categoria cat = new Categoria(predef);
			categorias.put(cat.getNombre(), cat);
		}
	}

	public static GestorCategoria getInstance() {
		if (instance == null) {
			instance = new GestorCategoria();
		}
		return instance;
	}

	public Categoria obtenerOCrearCategoria(String nombre) {
		// Buscar si ya existe
		Categoria existente = categorias.values().stream().filter(c -> c.getNombre().equalsIgnoreCase(nombre))
				.findFirst().orElse(null);
		if (existente != null) {
			return existente;
		}
		// Si no existe, crear nueva
		Categoria nueva = new Categoria(nombre);
		categorias.put(nombre, nueva);
		return nueva;
	}

	public Map<String, Categoria> getCategorias() {
		return categorias;
	}

	// Añadir este método
	public void cargarCategoria(Categoria categoria) {
		categorias.put(categoria.getNombre(), categoria);
	}
}