package com.tds.gestiongastos.vista;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import com.tds.gestiongastos.controlador.Controlador;
import com.tds.gestiongastos.modelo.Cuenta;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class ImportarViewController {

	@FXML
	private ComboBox<String> comboCuentas;
	@FXML
	private TextField campoRuta;
	@FXML
	private Label labelResultado;

	private File archivoSeleccionado;

	@FXML
	public void initialize() {
		List<String> nombresCuentas = Controlador.getInstance().obtenerCuentas().stream().map(Cuenta::getNombreCuenta)
				.collect(Collectors.toList());
		comboCuentas.setItems(FXCollections.observableArrayList(nombresCuentas));
	}

	@FXML
	private void handleExaminar() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Seleccionar archivo CSV");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos CSV", "*.csv"));

		archivoSeleccionado = fileChooser.showOpenDialog(campoRuta.getScene().getWindow());
		if (archivoSeleccionado != null) {
			campoRuta.setText(archivoSeleccionado.getAbsolutePath());
		}
	}

	@FXML
	private void handleImportar() {
		String nombreCuenta = comboCuentas.getValue();

		if (nombreCuenta == null || nombreCuenta.isEmpty()) {
			labelResultado.setText("Selecciona una cuenta.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}

		if (archivoSeleccionado == null) {
			labelResultado.setText("Selecciona un archivo.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}

		Cuenta cuenta = Controlador.getInstance().obtenerCuentas().stream()
				.filter(c -> c.getNombreCuenta().equals(nombreCuenta)).findFirst().orElse(null);

		if (cuenta == null) {
			labelResultado.setText("Cuenta no encontrada.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}

		try {
			int importados = Controlador.getInstance().importarGastos(cuenta, "bancario",
					archivoSeleccionado.getAbsolutePath());
			labelResultado.setText("Importados " + importados + " gastos correctamente.");
			labelResultado.setStyle("-fx-text-fill: green;");
		} catch (Exception e) {
			labelResultado.setText("Error al importar: " + e.getMessage());
			labelResultado.setStyle("-fx-text-fill: red;");
		}
	}
}