package com.tds.gestiongastos.vista;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import com.tds.gestiongastos.controlador.Controlador;

public class LoginViewController {

    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoPassword;

    @FXML
    private void handleLogin() {
        String usuario = campoUsuario.getText();
        String password = campoPassword.getText();

        if (Controlador.getInstance().autenticar(usuario, password)) {
            SceneManager.getInstance().mostrarMenu();
        } else {
            mostrarAlerta(AlertType.ERROR, "Error", "Usuario o contraseña incorrectos.");
        }
    }

    @FXML
    private void handleRegistro() {
        SceneManager.getInstance().mostrarRegistro();
    }

    private void mostrarAlerta(AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}