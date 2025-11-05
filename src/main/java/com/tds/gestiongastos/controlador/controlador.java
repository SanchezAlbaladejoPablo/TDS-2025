package com.tds.gestiongastos.controlador;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.tds.gestiongastos.modelo.*;

public class Controlador {

    private Stage stage;
    private static Controlador instance;
    
    private Persona usuarioActual;

    public Controlador(Stage stage) {
        instance = this;
        this.stage = stage;
        mostrarLogin();
    }

    public static Controlador getInstance() {
        return instance;
    }
    
    /* ============================
    MÉTODOS DE GESTIÓN DE VISTAS
    ============================ */
    
    public void mostrarLogin() {
        cargarVista("/vista/ventana_login.fxml", "Iniciar sesión");
    }
    
    public void mostrarRegistro() {
        cargarVista("/vista/ventana_registro.fxml", "Registro de usuario");
    }

    public void mostrarMenu() {
        cargarVista("/vista/ventana_menu.fxml", "Menu");
    }


    private void cargarVista(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/estilos/estilos.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    /* ============================
    LÓGICA DE USUARIOS
    ============================ */
    
    // Autenticación
    public boolean autenticarUsuario(String usuario, String password) {
        GestorUsuarios gestor = GestorUsuarios.getInstance();
        if (gestor.autenticar(usuario, password)) {
            this.usuarioActual = gestor.obtenerUsuario(usuario);
            return true;
        }
        return false;
    }
    
    // Registro de nuevo usuario
    public boolean registrarUsuario(String usuario, String password) {
        GestorUsuarios gestor = GestorUsuarios.getInstance();
        boolean exito = gestor.registrar(usuario, password);
        if (exito) {
            this.usuarioActual = gestor.obtenerUsuario(usuario);
            System.out.println("✅ Usuario registrado con éxito: " + usuario);
        } else {
            System.out.println("⚠️ El usuario '" + usuario + "' ya existe.");
        }
        return exito;
    }
    
    public Persona getUsuarioActual() {
        return usuarioActual;
    }
    
}
