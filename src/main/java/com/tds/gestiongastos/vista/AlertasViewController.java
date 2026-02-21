package com.tds.gestiongastos.vista;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.tds.gestiongastos.controlador.Controlador;
import com.tds.gestiongastos.modelo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AlertasViewController {

	@FXML
	private TextField campoLimite;
	@FXML
	private ComboBox<String> comboPeriodo;
	@FXML
	private ComboBox<String> comboCategoria;
	@FXML
	private ListView<String> listaAlertas;
	@FXML
	private ListView<String> listaNotificaciones;
	@FXML
	private Label labelResultado;

	@FXML
	public void initialize() {
		// Periodos disponibles
		comboPeriodo.setItems(FXCollections.observableArrayList("Semanal", "Mensual"));

		// Categorías: primera opción "Todas (general)"
		List<String> categorias = new ArrayList<>();
		categorias.add("Todas (general)");
		categorias.addAll(Controlador.getInstance().obtenerCategorias().stream().map(Categoria::getNombre)
				.collect(Collectors.toList()));
		comboCategoria.setItems(FXCollections.observableArrayList(categorias));

		// Cargar alertas existentes
		actualizarListas();
	}

	@FXML
	private void crearAlerta() {
		String limiteStr = campoLimite.getText();
		String periodo = comboPeriodo.getValue();
		String categoriaNombre = comboCategoria.getValue();

		if (limiteStr == null || limiteStr.isEmpty()) {
			labelResultado.setText("Introduce un límite.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}

		if (periodo == null) {
			labelResultado.setText("Selecciona un periodo.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}

		double limite;
		try {
			limite = Double.parseDouble(limiteStr);
			if (limite <= 0)
				throw new NumberFormatException();
		} catch (NumberFormatException e) {
			labelResultado.setText("Introduce un límite numérico positivo.");
			labelResultado.setStyle("-fx-text-fill: red;");
			return;
		}

		// Crear estrategia
		EstrategiaAlerta estrategia;
		if (periodo.equals("Semanal")) {
			estrategia = new AlertaSemanal();
		} else {
			estrategia = new AlertaMensual();
		}

		// Crear alerta con o sin categoría
		if (categoriaNombre == null || categoriaNombre.equals("Todas (general)")) {
			Controlador.getInstance().crearAlerta(limite, estrategia);
		} else {
			Categoria categoria = GestorCategoria.getInstance().obtenerOCrearCategoria(categoriaNombre);
			Controlador.getInstance().crearAlerta(limite, estrategia, categoria);
		}

		labelResultado.setText("Alerta creada correctamente.");
		labelResultado.setStyle("-fx-text-fill: green;");
		campoLimite.clear();
		actualizarListas();
	}

	@FXML
	private void verificarAlertas() {
		Controlador.getInstance().comprobarAlertas();
		actualizarListas();
		labelResultado.setText("Alertas comprobadas.");
		labelResultado.setStyle("-fx-text-fill: blue;");
	}

	private void actualizarListas() {
		// Alertas activas
		List<String> alertasTexto = Controlador.getInstance().obtenerAlertas().stream()
				.map(a -> String.format("%s - Límite: %.2f€%s", a.getEstrategia().getDescripcion(), a.getLimite(),
						a.getCategoria() != null ? " (" + a.getCategoria().getNombre() + ")" : " (General)"))
				.collect(Collectors.toList());
		listaAlertas.setItems(FXCollections.observableArrayList(alertasTexto));

		// Notificaciones
		List<String> notificacionesTexto = new ArrayList<>();
		for (Alerta alerta : Controlador.getInstance().obtenerAlertas()) {
			for (Notificacion n : alerta.getNotificaciones()) {
				notificacionesTexto.add(n.toString());
			}
		}
		listaNotificaciones.setItems(FXCollections.observableArrayList(notificacionesTexto));
	}
}