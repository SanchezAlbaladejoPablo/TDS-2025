package com.tds.gestiongastos.modelo;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {

    private static GestorUsuarios instance;
    private Map<String, Persona> usuarios;
    private int nextId = 1; // Contador de IDs

    private GestorUsuarios() {
        usuarios = new HashMap<>();
        // Usuario de prueba por defecto
        registrar("admin", "1234");
    }

    public static GestorUsuarios getInstance() {
        if (instance == null) {
            instance = new GestorUsuarios();
        }
        return instance;
    }

    public boolean autenticar(String usuario, String password) {
        Persona persona = usuarios.get(usuario);
        return persona != null && persona.getPassword().equals(password);
    }

    public boolean registrar(String usuario, String password) {
        if (usuarios.containsKey(usuario)) {
            return false; // Ya existe
        }
        Persona nueva = new Persona(nextId++, usuario, password);
        usuarios.put(usuario, nueva);
        return true;
    }

    public Persona obtenerUsuario(String usuario) {
        return usuarios.get(usuario);
    }

    public Map<String, Persona> getUsuarios() {
        return usuarios;
    }
}
