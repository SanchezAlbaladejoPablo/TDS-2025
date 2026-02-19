package com.tds.gestiongastos.persistencia;

import java.util.ArrayList;

import com.tds.gestiongastos.modelo.*;

public class GestorPersistencia {

    private static GestorPersistencia instance;
    private final Repositorio repositorio;

    private GestorPersistencia() {
        this.repositorio = new RepositorioJSON("datos_app.json");
    }

    public static GestorPersistencia getInstance() {
        if (instance == null) {
            instance = new GestorPersistencia();
        }
        return instance;
    }

    // Carga datos del JSON y reparte a cada gestor
    public void cargarDatos() {
        DatosApp datos = repositorio.cargar();

        // 1. Primero usuarios (otros dependen de ellos)
        GestorUsuarios gestorU = GestorUsuarios.getInstance();
        for (Persona p : datos.getUsuarios()) {
            gestorU.cargarUsuario(p);
        }

        // 2. Después categorías
        GestorCategoria gestorC = GestorCategoria.getInstance();
        for (Categoria c : datos.getCategorias()) {
            gestorC.cargarCategoria(c);
        }

        // 3. Cuentas con sus gastos
        GestorGastos gestorG = GestorGastos.getInstance();
        for (Cuenta c : datos.getCuentas()) {
            gestorG.añadirCuenta(c);
        }

        // 4. Alertas
        for (Alerta a : datos.getAlertas()) {
            gestorG.añadirAlerta(a);
        }
    }

    // Recoge datos de todos los gestores y guarda en JSON
    public void guardarDatos() {
        GestorUsuarios gestorU = GestorUsuarios.getInstance();
        GestorCategoria gestorC = GestorCategoria.getInstance();
        GestorGastos gestorG = GestorGastos.getInstance();

        DatosApp datos = new DatosApp(
            new ArrayList<>(gestorU.getUsuarios().values()),
            new ArrayList<>(gestorC.getCategorias().values()),
            gestorG.obtenerCuentas(),
            gestorG.obtenerAlertas()
        );

        repositorio.guardar(datos);
    }
}