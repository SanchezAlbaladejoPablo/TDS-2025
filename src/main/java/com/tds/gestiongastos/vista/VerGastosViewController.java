package com.tds.gestiongastos.vista;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.tds.gestiongastos.controlador.Controlador;
import com.tds.gestiongastos.modelo.Gasto;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class VerGastosViewController {

	@FXML
	private TableView<Gasto> tablaGastos;
	@FXML
	private TableColumn<Gasto, String> colFecha;
	@FXML
	private TableColumn<Gasto, String> colConcepto;
	@FXML
	private TableColumn<Gasto, String> colCategoria;
	@FXML
	private TableColumn<Gasto, Number> colValor;
	@FXML
	private Label labelTotal;

	private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	@FXML
	public void initialize() {
		// Configurar columnas
		colFecha.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getFecha().format(FORMATO)));

		colConcepto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getConcepto()));

		colCategoria.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getCategoria().getNombre()));

		colValor.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getValor()));

		// Cargar datos
		List<Gasto> gastos = Controlador.getInstance().obtenerGastosUsuarioActual();
		tablaGastos.setItems(FXCollections.observableArrayList(gastos));

		// Total
		double total = Controlador.getInstance().obtenerTotalUsuarioActual();
		labelTotal.setText(String.format("%.2f €", total));
	}
}