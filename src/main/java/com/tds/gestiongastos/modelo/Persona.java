package com.tds.gestiongastos.modelo;

import java.util.Objects;

public class Persona {

    private final int id;
    private String usuario;
    private String password;

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
		return id == other.id && Objects.equals(password, other.password) && Objects.equals(usuario, other.usuario);
	}

	@Override
    public String toString() {
        return "Persona{id=" + id + ", usuario='" + usuario + "'}";
    }
}
