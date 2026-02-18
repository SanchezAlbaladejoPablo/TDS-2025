package com.tds.gestiongastos.modelo;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Persona {

	@JsonProperty("id")
	private int id;

	@JsonProperty("usuario")
	private String usuario;

	@JsonProperty("password")
	private String password;

	// Constructor vacío para Jackson
	public Persona() {
	}

	public Persona(int id, String usuario, String password) {
		this.id = id;
		this.usuario = usuario;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Persona{id=" + id + ", usuario='" + usuario + "'}";
	}
}