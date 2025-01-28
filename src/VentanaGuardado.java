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

public class VentanaGuardado {
    private Stage stage;

    public VentanaGuardado(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        VBox panel = new VBox(20);
        panel.setPadding(new Insets(20));
        panel.setAlignment(Pos.CENTER);

        Label titulo = new Label("Ventana de Guardado");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        VBox tituloBox = new VBox(titulo);
        tituloBox.setPadding(new Insets(0, 0, 25, 0));
        tituloBox.setAlignment(Pos.CENTER);

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> guardar());
        btnGuardar.setPrefWidth(200);

        Button btnCargar = new Button("Cargar");
        btnCargar.setOnAction(e -> cargar());
        btnCargar.setPrefWidth(200);

        Button btnVolver = new Button("Volver");
        btnVolver.setOnAction(e -> MainApp.mostrarMenuPrincipal());
        btnVolver.setPrefWidth(200);

        VBox botonesBox = new VBox(10, btnGuardar, btnCargar, btnVolver);
        botonesBox.setAlignment(Pos.CENTER);

        panel.getChildren().addAll(tituloBox, botonesBox);

        return new Scene(panel, 400, 300);
    }

    private void guardar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Archivo");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            System.out.println("Guardando en: " + file.getAbsolutePath());
            // Lógica para guardar el archivo
        } else {
            System.out.println("Por favor, seleccione una ubicación para guardar el archivo.");
        }
    }

    private void cargar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar Archivo");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            System.out.println("Cargando desde: " + file.getAbsolutePath());
            // Lógica para cargar el archivo
        }
    }
}