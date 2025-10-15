package com.tds.gestiongastos;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Controlador {

    private Stage stage;
    private static ControladorPrincipal instance;

    public ControladorPrincipal(Stage stage) {
        this.stage = stage;
        instance = this;
        mostrarLogin();
    }

    public static ControladorPrincipal getInstance() {
        return instance;
    }

    public void mostrarLogin() {
        cargarVista("/vista/ventana_login.fxml", "Iniciar sesión");
    }

    public void mostrarMenu() {
        cargarVista("/vista/ventana_menu.fxml", "Menú principal");
    }

    private void cargarVista(String fxml, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            stage.setScene(new Scene(root));
            stage.setTitle(titulo);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
