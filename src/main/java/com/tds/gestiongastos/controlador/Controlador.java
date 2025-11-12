package com.tds.gestiongastos.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Parent;
import javafx.stage.Stage;
import com.tds.gestiongastos.modelo.Persona;
import com.tds.gestiongastos.modelo.GestorUsuarios;

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
        cargarVista("/vista/ventana_menu.fxml", "Menú principal");
    }

    private void cargarVista(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            loader.setController(this); 
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

    /* ============================
       EVENTOS DE LOGIN
       ============================ */

    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoPassword;

    @FXML
    private void handleLogin() {
        String usuario = campoUsuario.getText();
        String password = campoPassword.getText();

        if (autenticarUsuario(usuario, password)) {
            mostrarMenu();
        } else {
            mostrarAlerta(AlertType.ERROR, "Error de autenticación", "Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    private void handleRegistro() {
        mostrarRegistro();
    }

    /* ============================
       EVENTOS DE REGISTRO
       ============================ */

    @FXML private PasswordField campoConfirmar;

    @FXML
    private void handleRegistrar() {
        String usuario = campoUsuario.getText();
        String password = campoPassword.getText();
        String confirmar = campoConfirmar.getText();

        if (usuario == null || usuario.isEmpty() || password.isEmpty() || confirmar.isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Campos vacíos", "Debes rellenar todos los campos.");
            return;
        }

        if (!password.equals(confirmar)) {
            mostrarAlerta(AlertType.ERROR, "Error", "Las contraseñas no coinciden.");
            return;
        }

        boolean exito = registrarUsuario(usuario, password);
        if (exito) {
            mostrarAlerta(AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente.");
            mostrarLogin();
        } else {
            mostrarAlerta(AlertType.ERROR, "Error", "El nombre de usuario ya existe.");
        }
    }

    @FXML
    private void handleVolverLogin() {
        mostrarLogin();
    }

    /* ============================
       UTILIDAD GENERAL
       ============================ */

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
