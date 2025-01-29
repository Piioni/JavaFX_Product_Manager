import javafx.application.Application;
import javafx.stage.Stage;
import java.nio.file.Path;

public class MainApp extends Application {
    private static Stage primaryStage;
    private static ListaProductos listaProductos;
    private static Path PATH ;

    @Override
    public void start(Stage primaryStage) {
        MainApp.primaryStage = primaryStage;
        MainApp.listaProductos = new ListaProductos();
        primaryStage.setTitle("Main Menu");

        mostrarMenuPrincipal();
    }

    public static void mostrarMenuPrincipal() {
        MenuPrincipal menuPrincipal = new MenuPrincipal(primaryStage, listaProductos);
        primaryStage.setScene(menuPrincipal.getScene());
        primaryStage.show();
    }

    public static void mostrarVentanaProductos() {
        VentanaProductos ventanaProductos = new VentanaProductos(listaProductos, PATH);
        primaryStage.setScene(ventanaProductos.getScene());
        primaryStage.show();
    }

    public static void setPath(Path path) {
        PATH = path;
    }

    public static void main(String[] args) {
        launch(args);
    }
}