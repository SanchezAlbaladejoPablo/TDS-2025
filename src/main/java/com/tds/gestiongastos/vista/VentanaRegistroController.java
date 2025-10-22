package com.tds.gestiongastos.vista;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.tds.gestiongastos.controlador.Controlador;

public class VentanaRegistroController {

    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoPassword;
    @FXML private PasswordField campoConfirmar;

    @FXML
    private void handleRegistrar() {
        String usuario = campoUsuario.getText();
        String password = campoPassword.getText();
        String confirmar = campoConfirmar.getText();

        if (usuario.isEmpty() || password.isEmpty() || confirmar.isEmpty()) {
            mostrarAlerta(AlertType.WARNING, "Campos vacíos", "Debes rellenar todos los campos.");
            return;
        }

        if (!password.equals(confirmar)) {
            mostrarAlerta(AlertType.ERROR, "Error", "Las contraseñas no coinciden.");
            return;
        }

        boolean exito = Controlador.getInstance().registrarUsuario(usuario, password);
        if (exito) {
            mostrarAlerta(AlertType.INFORMATION, "Registro exitoso", "Usuario registrado correctamente.");
            Controlador.getInstance().mostrarLogin();
        } else {
            mostrarAlerta(AlertType.ERROR, "Error", "El nombre de usuario ya existe.");
        }
    }

    @FXML
    private void handleVolverLogin() {
        Controlador.getInstance().mostrarLogin();
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
