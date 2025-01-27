import javafx.application.Application;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        VentanaProductos ventanaProductos = new VentanaProductos();
        ventanaProductos.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
