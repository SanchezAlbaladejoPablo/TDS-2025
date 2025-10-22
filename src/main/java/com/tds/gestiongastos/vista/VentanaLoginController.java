package com.tds.gestiongastos.vista;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.tds.gestiongastos.controlador.Controlador;

public class VentanaLoginController {

    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoPassword;

    @FXML
    private void handleLogin() {
        String usuario = campoUsuario.getText();
        String password = campoPassword.getText();

        if (Controlador.getInstance().autenticarUsuario(usuario, password)) {
            Controlador.getInstance().mostrarMenu();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error de autenticación");
            alert.setHeaderText(null);
            alert.setContentText("Usuario o contraseña incorrectos.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleRegistro() {
        Controlador.getInstance().mostrarRegistro();
    }
}
