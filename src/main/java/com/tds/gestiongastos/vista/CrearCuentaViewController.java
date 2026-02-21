package com.tds.gestiongastos.vista;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.tds.gestiongastos.controlador.Controlador;

public class CrearCuentaViewController {

    @FXML private TextField campoNombre;
    @FXML private Label labelResultado;

    @FXML
    private void crearCuentaIndividual() {
        String nombre = campoNombre.getText();

        if (nombre == null || nombre.isEmpty()) {
            labelResultado.setText("Introduce un nombre para la cuenta.");
            labelResultado.setStyle("-fx-text-fill: red;");
            return;
        }

        Controlador.getInstance().crearCuenta(nombre);
        labelResultado.setText("Cuenta '" + nombre + "' creada correctamente.");
        labelResultado.setStyle("-fx-text-fill: green;");
        campoNombre.clear();
    }
}