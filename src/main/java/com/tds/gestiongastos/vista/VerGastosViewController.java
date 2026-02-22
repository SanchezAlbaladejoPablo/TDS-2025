package com.tds.gestiongastos.vista;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.tds.gestiongastos.controlador.Controlador;
import com.tds.gestiongastos.modelo.Categoria;
import com.tds.gestiongastos.modelo.Gasto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



public class VerGastosViewController {
    // Tabla
    @FXML private TableView<Gasto> tablaGastos;
    @FXML private TableColumn<Gasto, String> columnaFecha;
    @FXML private TableColumn<Gasto, String> columnaConcepto;
    @FXML private TableColumn<Gasto, String> columnaCategoria;
    @FXML private TableColumn<Gasto, Number> columnaCantidad;
    @FXML private TableColumn<Gasto, String> columnaCuenta;

    // Filtros
    @FXML private ComboBox<String> comboCategoria;
    @FXML private DatePicker fechaDesde;
    @FXML private DatePicker fechaHasta;
    @FXML private TextField campoBusqueda;

    // Estadísticas
    @FXML private Label labelTotalGastos;
    @FXML private Label labelCantidadTotal;

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private ObservableList<Gasto> listaGastos;

    @FXML
    public void initialize() {
        listaGastos = FXCollections.observableArrayList();
        configurarTabla();
        cargarCategorias();
        cargarGastos();
        actualizarEstadisticas();
    }

    private void configurarTabla() {
        // Fecha
        columnaFecha.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getFecha().format(FORMATO))
        );

        // Concepto
        columnaConcepto.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getConcepto())
        );

        // Categoría
        columnaCategoria.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getCategoria().getNombre())
        );

        // Cantidad con formato €
        columnaCantidad.setCellValueFactory(cellData -> 
            new SimpleDoubleProperty(cellData.getValue().getValor())
        );
        columnaCantidad.setCellFactory(col -> new TableCell<Gasto, Number>() {
            @Override
            protected void updateItem(Number cantidad, boolean empty) {
                super.updateItem(cantidad, empty);
                setText(empty || cantidad == null ? null : String.format("%.2f €", cantidad.doubleValue()));
            }
        });

        // Cuenta
        columnaCuenta.setCellValueFactory(cellData -> {
            String cuenta = cellData.getValue().getCuenta() != null 
                ? cellData.getValue().getCuenta().getNombreCuenta() 
                : "Personal";
            return new SimpleStringProperty(cuenta);
        });

        tablaGastos.setItems(listaGastos);
    }

    private void cargarCategorias() {
        List<Categoria> categorias = Controlador.getInstance().obtenerCategorias();
        ObservableList<String> nombres = FXCollections.observableArrayList("Todas");
        nombres.addAll(categorias.stream().map(Categoria::getNombre).collect(Collectors.toList()));
        comboCategoria.setItems(nombres);
        comboCategoria.getSelectionModel().selectFirst();
    }

    private void cargarGastos() {
        List<Gasto> gastos = Controlador.getInstance().obtenerGastosUsuarioActual();
        listaGastos.clear();
        listaGastos.addAll(gastos);
    }

    @FXML
    private void handleAplicarFiltros() {
        List<Gasto> gastosFiltrados = Controlador.getInstance().obtenerGastosUsuarioActual();

        // Filtro categoría
        String categoria = comboCategoria.getValue();
        if (categoria != null && !categoria.equals("Todas")) {
            gastosFiltrados = gastosFiltrados.stream()
                .filter(g -> g.getCategoria().getNombre().equals(categoria))
                .collect(Collectors.toList());
        }

        // Filtro fecha desde
        LocalDate desde = fechaDesde.getValue();
        if (desde != null) {
            gastosFiltrados = gastosFiltrados.stream()
                .filter(g -> !g.getFecha().toLocalDate().isBefore(desde))
                .collect(Collectors.toList());
        }

        // Filtro fecha hasta
        LocalDate hasta = fechaHasta.getValue();
        if (hasta != null) {
            gastosFiltrados = gastosFiltrados.stream()
                .filter(g -> !g.getFecha().toLocalDate().isAfter(hasta))
                .collect(Collectors.toList());
        }

        // Filtro búsqueda
        String busqueda = campoBusqueda.getText();
        if (busqueda != null && !busqueda.trim().isEmpty()) {
            String lower = busqueda.toLowerCase();
            gastosFiltrados = gastosFiltrados.stream()
                .filter(g -> g.getConcepto().toLowerCase().contains(lower))
                .collect(Collectors.toList());
        }

        listaGastos.clear();
        listaGastos.addAll(gastosFiltrados);
        actualizarEstadisticas();
    }

    @FXML
    private void handleLimpiarFiltros() {
        comboCategoria.getSelectionModel().selectFirst();
        fechaDesde.setValue(null);
        fechaHasta.setValue(null);
        campoBusqueda.clear();
        cargarGastos();
        actualizarEstadisticas();
    }

    @FXML
    private void handleNuevoGasto() {
        mostrarInfo("Nuevo Gasto", "Usa el botón 'Registrar Gasto' del menú lateral");
    }

    @FXML
    private void handleEditar() {
        Gasto gasto = tablaGastos.getSelectionModel().getSelectedItem();
        if (gasto == null) {
            mostrarAdvertencia("Sin selección", "Selecciona un gasto para editar");
            return;
        }
        
        mostrarInfo("Editar", 
            "Funcionalidad en desarrollo\n\n" +
            "Gasto: " + gasto.getValor() + "€\n" +
            "Categoría: " + gasto.getCategoria().getNombre() + "\n" +
            "Concepto: " + gasto.getConcepto());
    }

    @FXML
    private void handleBorrar() {
        Gasto gasto = tablaGastos.getSelectionModel().getSelectedItem();
        if (gasto == null) {
            mostrarAdvertencia("Sin selección", "Selecciona un gasto para borrar");
            return;
        }

        Optional<ButtonType> resultado = mostrarConfirmacion(
            "Confirmar borrado",
            "¿Borrar este gasto?\n\n" +
            "Cantidad: " + gasto.getValor() + "€\n" +
            "Categoría: " + gasto.getCategoria().getNombre() + "\n" +
            "Concepto: " + gasto.getConcepto()
        );

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            boolean borrado = Controlador.getInstance().borrarGasto(gasto);
            if (borrado) {
                listaGastos.remove(gasto);
                actualizarEstadisticas();
                mostrarExito("Borrado", "Gasto eliminado correctamente");
            } else {
                mostrarError("Error", "No se pudo borrar el gasto");
            }
        }
    }

    private void actualizarEstadisticas() {
        int total = listaGastos.size();
        double suma = listaGastos.stream().mapToDouble(Gasto::getValor).sum();
        labelTotalGastos.setText(String.valueOf(total));
        labelCantidadTotal.setText(String.format("%.2f €", suma));
    }

    // Diálogos
    private void mostrarInfo(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAdvertencia(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarExito(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private Optional<ButtonType> mostrarConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        return alert.showAndWait();
    }
}