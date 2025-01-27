import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Main Menu");

        mostrarMenuPrincipal();
    }

    public void mostrarMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        primaryStage.setScene(menuPrincipal.getScene(this));
        primaryStage.show();
    }

    public void mostrarVentanaProductos() {
        VentanaProductos ventanaProductos = new VentanaProductos(this);
        primaryStage.setScene(ventanaProductos.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
