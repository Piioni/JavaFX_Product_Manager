import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    private static Stage primaryStage;
    private static ListaProductos listaProductos;

    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.listaProductos = new ListaProductos();
        primaryStage.setTitle("Main Menu");

        mostrarMenuPrincipal();
    }

    public static void mostrarMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal();
        primaryStage.setScene(menuPrincipal.getScene());
        primaryStage.show();
    }

    public static void mostrarVentanaProductos() {
        VentanaProductos ventanaProductos = new VentanaProductos(listaProductos);
        primaryStage.setScene(ventanaProductos.getScene());
        primaryStage.show();
    }

    public static void mostrarVentanaGuardar() {
        VentanaGuardado ventanaGuardado = new VentanaGuardado(primaryStage, listaProductos);
        primaryStage.setScene(ventanaGuardado.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}