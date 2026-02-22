package com.tds.gestiongastos.controlador;

import java.util.List;
import java.util.Map;

import com.tds.gestiongastos.modelo.*;
import com.tds.gestiongastos.persistencia.GestorPersistencia;

public class Controlador {

    private static Controlador instance;
    private Persona usuarioActual;

    private Controlador() {
        GestorPersistencia.getInstance().cargarDatos();
    }

    public static Controlador getInstance() {
        if (instance == null) {
            instance = new Controlador();
        }
        return instance;
    }

    // --- Usuarios ---
    public boolean autenticar(String usuario, String password) {
        GestorUsuarios gestor = GestorUsuarios.getInstance();
        if (gestor.autenticar(usuario, password)) {
            this.usuarioActual = gestor.obtenerUsuario(usuario);
            return true;
        }
        return false;
    }

    public boolean registrar(String usuario, String password) {
        boolean exito = GestorUsuarios.getInstance().registrar(usuario, password);
        if (exito) {
            guardar();
        }
        return exito;
    }

    public Persona getUsuarioActual() {
        return usuarioActual;
    }

    // --- Cuentas ---
    public void crearCuenta(String nombre) {
        Cuenta cuenta = new Cuenta(nombre, usuarioActual);
        GestorGastos.getInstance().añadirCuenta(cuenta);
        guardar();
    }

    public void crearCuentaCompartida(String nombre, List<Persona> participantes) {
        CuentaCompartida cuenta = new CuentaCompartida(nombre, usuarioActual, participantes);
        GestorGastos.getInstance().añadirCuenta(cuenta);
        guardar();
    }

    public void crearCuentaCompartida(String nombre, Map<Persona, Double> porcentajes) {
        CuentaCompartida cuenta = new CuentaCompartida(nombre, usuarioActual, porcentajes);
        GestorGastos.getInstance().añadirCuenta(cuenta);
        guardar();
    }

    public List<Cuenta> obtenerCuentas() {
        return GestorGastos.getInstance().obtenerCuentas();
    }

    // --- Gastos ---
    public void registrarGasto(Cuenta cuenta, double valor, String concepto, String categoriaNombre) {
        Categoria categoria = GestorCategoria.getInstance().obtenerOCrearCategoria(categoriaNombre);
        Gasto gasto = new Gasto(valor, concepto, usuarioActual, categoria);
        cuenta.añadirGasto(gasto);
        guardar();
    }

    public List<Gasto> obtenerGastosUsuarioActual() {
        return GestorGastos.getInstance().obtenerGastosPorPersona(usuarioActual);
    }

    public double obtenerTotalUsuarioActual() {
        return GestorGastos.getInstance().calcularTotalPorPersona(usuarioActual);
    }
    
    public boolean borrarGasto(Gasto gasto) {
        boolean exito = GestorGastos.getInstance().borrarGasto(gasto);
        if (exito) {
            guardar();
        }
        return exito;
    }

    // --- Categorías ---
    public List<Categoria> obtenerCategorias() {
        return List.copyOf(GestorCategoria.getInstance().getCategorias().values());
    }

    // --- Alertas ---
    public void crearAlerta(double limite, EstrategiaAlerta estrategia, Categoria categoria) {
        Alerta alerta = new Alerta(limite, estrategia, categoria);
        GestorGastos.getInstance().añadirAlerta(alerta);
        guardar();
    }

    public void crearAlerta(double limite, EstrategiaAlerta estrategia) {
        Alerta alerta = new Alerta(limite, estrategia);
        GestorGastos.getInstance().añadirAlerta(alerta);
        guardar();
    }

    public List<Alerta> obtenerAlertas() {
        return GestorGastos.getInstance().obtenerAlertas();
    }

    public void comprobarAlertas() {
        GestorGastos.getInstance().comprobarAlertas(usuarioActual);
    }

    // --- Importar ---
    public int importarGastos(Cuenta cuenta, String formato, String ruta) {
        ImportadorGastos importador = FactoriaImportador.crearImportador(formato);
        List<Gasto> gastos = importador.importar(ruta);
        for (Gasto gasto : gastos) {
            cuenta.añadirGasto(gasto);
        }
        guardar();
        return gastos.size();
    }

    // --- Persistencia ---
    private void guardar() {
        GestorPersistencia.getInstance().guardarDatos();
    }
}