package com.tds.gestiongastos.vista;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {

    private static SceneManager instance;
    private Stage stage;
    private Scene escenaActual;

    private SceneManager() { }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void inicializar(Stage stage) {
        this.stage = stage;
    }

    public void mostrarLogin() {
        cargarVista("/vista/ventana_login.fxml", "Iniciar sesión");
    }

    public void mostrarRegistro() {
        cargarVista("/vista/ventana_registro.fxml", "Registro de usuario");
    }

    public void mostrarMenu() {
        cargarVista("/vista/ventana_menu.fxml", "Gestión de Gastos");
    }

    private void cargarVista(String fxml, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            if (escenaActual == null) {
                escenaActual = new Scene(root);
                escenaActual.getStylesheets().add(
                    getClass().getResource("/estilos/estilos.css").toExternalForm());
                stage.setScene(escenaActual);
            } else {
                escenaActual.setRoot(root);
            }

            stage.setTitle(titulo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}