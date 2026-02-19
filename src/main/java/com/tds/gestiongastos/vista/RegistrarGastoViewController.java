package com.tds.gestiongastos.vista;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.tds.gestiongastos.controlador.Controlador;
import com.tds.gestiongastos.modelo.Categoria;
import com.tds.gestiongastos.modelo.Cuenta;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrarGastoViewController {

	@FXML
	private ComboBox<String> comboCuentas;
	@FXML
	private TextField campoConcepto;
	@FXML
	private TextField campoValor;
	@FXML
	private ComboBox<String> comboCategorias;
	@FXML
	private Label labelResultado;

	@FXML
	public void initialize() {
		// Cargar cuentas
		List<String> nombresCuentas = Controlador.getInstance().obtenerCuentas().stream().map(Cuenta::getNombreCuenta)
				.collect(Collectors.toList());
		comboCuentas.setItems(FXCollections.observableArrayList(nombresCuentas));

		// Cargar categorías
		List<String> nombresCategorias = Controlador.getInstance().obtenerCategorias().stream()
				.map(Categoria::getNombre).collect(Collectors.toList());
		comboCategorias.setItems(FXCollections.observableArrayList(nombresCategorias));
	}

	@FXML
	private void handleRegistrar() {
		String nombreCuenta = comboCuentas.getValue();
		String concepto = campoConcepto.getText();
		String valorStr = campoValor.getText();
		String categoriaNombre = comboCategorias.getValue();

		// Validaciones
		if (nombreCuenta == null || nombreCuenta.isEmpty()) {
			labelResultado.setText("Selecciona una cuenta.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}
		if (concepto == null || concepto.isEmpty()) {
			labelResultado.setText("Introduce un concepto.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}
		if (categoriaNombre == null || categoriaNombre.isEmpty()) {
			labelResultado.setText("Selecciona o escribe una categoría.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}

		double valor;
		try {
			valor = Double.parseDouble(valorStr);
			if (valor <= 0)
				throw new NumberFormatException();
		} catch (NumberFormatException e) {
			labelResultado.setText("Introduce un valor numérico positivo.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}

		// Buscar la cuenta
		Cuenta cuenta = Controlador.getInstance().obtenerCuentas().stream()
				.filter(c -> c.getNombreCuenta().equals(nombreCuenta)).findFirst().orElse(null);

		if (cuenta == null) {
			labelResultado.setText("Cuenta no encontrada.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}

		// Registrar
		Controlador.getInstance().registrarGasto(cuenta, valor, concepto, categoriaNombre);

		labelResultado.setText("Gasto registrado: " + concepto + " - " + valor + "€");
		labelResultado.setStyle("-fx-text-fill: green;");

		// Limpiar campos
		campoConcepto.clear();
		campoValor.clear();
	}
}