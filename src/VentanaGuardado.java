import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class VentanaGuardado {
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

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> cancelar());

        panel.getChildren().addAll(tituloBox, btnGuardar, btnCancelar);

        return new Scene(panel, 300, 200);
    }

    private void guardar() {
        System.out.println("Guardando...");
    }

    private void cancelar() {
        System.out.println("Cancelando...");
    }
}
