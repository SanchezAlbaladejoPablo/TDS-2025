package com.tds.gestiongastos.vista;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import com.tds.gestiongastos.controlador.Controlador;

import java.io.IOException;

public class MenuViewController {

	@FXML
	private StackPane contenidoCentral;
	@FXML
	private Button botonUsuario;

	@FXML
	public void initialize() {
		// Mostrar nombre del usuario en el botón
		String nombre = Controlador.getInstance().getUsuarioActual().getUsuario();
		botonUsuario.setText(nombre);
	}

	// --- Gastos ---
	@FXML
	private void handleRegistrarGasto() {
		cargarPanel("/vista/panel_registrar_gasto.fxml");
	}

	@FXML
	private void handleVerGastos() {
		cargarPanel("/vista/panel_ver_gastos.fxml");
	}

	// --- Alertas ---
	@FXML
	private void handleConfigurarAlertas() {
		cargarPanel("/vista/panel_alertas.fxml");
	}

	// --- Importar ---
	@FXML
	private void handleImportarBanco() {
		cargarPanel("/vista/panel_importar.fxml");
	}

	// --- Cerrar sesión ---
	@FXML
	private void handleCerrarSesion() {
		SceneManager.getInstance().mostrarLogin();
	}

	private void cargarPanel(String fxml) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
			Node panel = loader.load();
			contenidoCentral.getChildren().clear();
			contenidoCentral.getChildren().add(panel);
		} catch (IOException e) {
			contenidoCentral.getChildren().clear();
			contenidoCentral.getChildren().add(new Label("Error al cargar la vista: " + e.getMessage()));
		}
	}
}