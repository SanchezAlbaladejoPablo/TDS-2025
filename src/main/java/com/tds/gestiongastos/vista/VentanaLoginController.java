package com.tds.gestiongastos.vista;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import com.tds.gestiongastos.*;

public class VentanaLoginController {

    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoPassword;

    @FXML
    private void handleLogin() {
        if ("admin".equals(campoUsuario.getText()) && "1234".equals(campoPassword.getText())) {
            Controlador.getInstance().mostrarMenu();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Usuario o contraseña incorrectos");
            alert.showAndWait();
        }
    }
}
