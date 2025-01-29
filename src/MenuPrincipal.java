import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class MenuPrincipal {
    private ListaProductos listaProductos;
    private Stage stage;

    public MenuPrincipal(Stage stage, ListaProductos listaProductos) {
        this.stage = stage;
        this.listaProductos = listaProductos;
    }

    public Scene getScene() {
        // Creacion de la estructura de la escena
        VBox menuLayout = new VBox(20);
        menuLayout.setPadding(new Insets(20));
        menuLayout.setAlignment(Pos.CENTER);

        // Creation del título
        Label titulo = new Label("Menú Principal");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.getStyleClass().add("label-title");

        VBox tituloBox = new VBox(titulo);
        tituloBox.setPadding(new Insets(0, 0, 25, 0));
        tituloBox.setAlignment(Pos.CENTER);

        // Creacion de los botones
        Button btnProductos = new Button("Ir a Ventana de Productos");
        btnProductos.setOnAction(e -> MainApp.mostrarVentanaProductos());
        btnProductos.setPrefWidth(200);

        Button btnGuardar = new Button("Guardar como");
        btnGuardar.setOnAction(e -> guardar());
        btnGuardar.setPrefWidth(200);

        Button btnCargar = new Button("Cargar");
        btnCargar.setOnAction(e -> cargar());
        btnCargar.setPrefWidth(200);

        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> System.exit(0));
        btnSalir.setPrefWidth(200);

        menuLayout.getChildren().addAll(tituloBox, btnProductos, btnGuardar, btnCargar, btnSalir);

        Scene scene = new Scene(menuLayout, 450, 400);
        scene.getStylesheets().add("stylesMenu.css");

        return scene;
    }

    // metodos de los botones

    private void guardar() {
        // Permite al usuario seleccionar la ubicación para guardar el archivo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Archivo");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try {
                // Guardar la lista de productos en un archivo JSON
                listaProductos.guardarProductos(file.toPath());
                System.out.println("Guardado en: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Error al guardar el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Por favor, seleccione una ubicación para guardar el archivo.");
        }
    }

    private void cargar() {
        // Permite al usuario seleccionar un archivo para cargar
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar Archivo");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                // Limpiar la lista de productos y carga los productos desde el archivo
                listaProductos.clear();
                listaProductos.cargarProductos(file.toPath());
                MainApp.setPath(file.toPath()); // Guardar la ubicación del archivo para futuras referencias
                System.out.println("Cargado desde: " + file.getAbsolutePath());
                MainApp.mostrarVentanaProductos();
            } catch (IOException e) {
                System.out.println("Error al cargar el archivo: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error al cargar el archivo 2: " + e.getMessage());
            }
        }
    }

}