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

        // Botón para ir a la ventana de productos
        Button btnProductos = new Button("Ir a Ventana de Productos");
        btnProductos.setOnAction(e -> mainApp.mostrarVentanaProductos());

        // Botón adicional para salir de la aplicación
        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> System.exit(0));

        // Agregar elementos al diseño
        menuLayout.getChildren().addAll(titulo, btnProductos, btnSalir);

        return new Scene(menuLayout, 300, 200);
    }
}