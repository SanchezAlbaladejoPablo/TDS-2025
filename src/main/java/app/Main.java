package app;

import javafx.application.Application;
import javafx.stage.Stage;
import com.tds.gestiongastos.vista.SceneManager;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setWidth(900);
        stage.setHeight(600);
        SceneManager.getInstance().inicializar(stage);
        SceneManager.getInstance().mostrarLogin();
    }

    public static void main(String[] args) {
        launch(args);
    }
}