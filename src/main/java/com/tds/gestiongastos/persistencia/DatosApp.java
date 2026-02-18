package com.tds.gestiongastos.persistencia;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tds.gestiongastos.modelo.*;

public class DatosApp {

	@JsonProperty("usuarios")
	private List<Persona> usuarios;

	@JsonProperty("categorias")
	private List<Categoria> categorias;

	@JsonProperty("cuentas")
	private List<Cuenta> cuentas;

	@JsonProperty("alertas")
	private List<Alerta> alertas;

	// Constructor vacío para Jackson
	public DatosApp() {
		this.usuarios = new ArrayList<>();
		this.categorias = new ArrayList<>();
		this.cuentas = new ArrayList<>();
		this.alertas = new ArrayList<>();
	}

	public DatosApp(List<Persona> usuarios, List<Categoria> categorias, List<Cuenta> cuentas, List<Alerta> alertas) {
		this.usuarios = usuarios;
		this.categorias = categorias;
		this.cuentas = cuentas;
		this.alertas = alertas;
	}

	public List<Persona> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Persona> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public List<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}
}