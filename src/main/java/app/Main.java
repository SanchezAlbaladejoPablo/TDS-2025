package app;

import javafx.application.Application;
import javafx.stage.Stage;
import com.tds.gestiongastos.vista.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        new VentanaLogin(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
