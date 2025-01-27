import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuPrincipal {
    public Scene getScene(MainApp mainApp) {
        VBox menuLayout = new VBox(20);
        menuLayout.setPadding(new Insets(20));
        menuLayout.setAlignment(Pos.CENTER);

        // Título del menú principal
        Label titulo = new Label("Menú Principal");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titulo.getStyleClass().add("label-titulo");

        VBox tituloBox = new VBox(titulo);
        tituloBox.setPadding(new Insets(0, 0, 25, 0));
        tituloBox.setAlignment(Pos.CENTER);

        // Botón para ir a la ventana de productos
        Button btnProductos = new Button("Ir a Ventana de Productos");
        btnProductos.setOnAction(e -> mainApp.mostrarVentanaProductos());
        btnProductos.setPrefWidth(200);
        btnProductos.getStyleClass().addAll("button", "button-icon");

        //Botón para ir a la ventana de guardado
        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> mainApp.mostrarVentanaGuardar());
        btnGuardar.setPrefWidth(200);
        btnGuardar.getStyleClass().addAll("button", "button-icon");

        // Botón adicional para salir de la aplicación
        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> System.exit(0));
        btnSalir.setPrefWidth(200);
        btnSalir.getStyleClass().addAll("button", "button-icon");

        // Agregar elementos al diseño
        menuLayout.getChildren().addAll(tituloBox, btnProductos, btnGuardar, btnSalir);

        Scene scene = new Scene(menuLayout, 450, 400);
        scene.getStylesheets().add("stylesMenuPrincipal.css");

        return scene;
    }
}