import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaProductos {
    private TextField txtCodigo, txtNombre, txtCantidad, txtPrecio, txtDescripcion;
    private ListView<String> lista;
    private ListaProductos listaProductos;
    private MainApp mainApp;

    public VentanaProductos(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public Scene getScene() {
        listaProductos = new ListaProductos();

        // panel izquierdo
        VBox panelIzquierdo = new VBox();
        panelIzquierdo.setPadding(new Insets(20));

        txtCodigo = new TextField();
        txtNombre = new TextField();
        txtCantidad = new TextField();
        txtPrecio = new TextField();
        txtDescripcion = new TextField();

        panelIzquierdo.getChildren().addAll(
                new Label("Código"), txtCodigo,
                new Label("Nombre"), txtNombre,
                new Label("Cantidad"), txtCantidad,
                new Label("Precio"), txtPrecio,
                new Label("Descripción"), txtDescripcion
        );

        // Panel derecho
        VBox panelBotonesIzquierdo = new VBox();
        panelBotonesIzquierdo.setPadding(new Insets(10, 0, 0, 0));

        // Botones junto con sus eventos
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(e -> agreagarProudcto());
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setOnAction(e -> eliminarProducto());
        Button btnBuscar = new Button("Buscar");
        btnBuscar.setOnAction(e -> buscarProducto());

        // agregar botones al panel izquierdo principal
        panelBotonesIzquierdo.getChildren().addAll(btnAgregar, btnEliminar, btnBuscar);
        panelIzquierdo.getChildren().add(panelBotonesIzquierdo);

        // Panel derecho
        VBox panelDerecho = new VBox(10);
        panelDerecho.setPadding(new Insets(20));

        Label listadoProductos = new Label("Listado de productos");
        listadoProductos.setStyle("-fx-font-weight: bold; -fx-alignment: center");

        lista = new ListView<>();
        Button btnMostrar = new Button("Mostrar");
        btnMostrar.setOnAction(e -> mostrarProductos());
        Button btnSalir = new Button("Salir");
        btnSalir.setOnAction(e -> mainApp.mostrarMenuPrincipal());

        panelDerecho.getChildren().addAll(listadoProductos, lista, btnMostrar, btnSalir);

        // Split pane
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(panelIzquierdo, panelDerecho);
        splitPane.setDividerPositions(0.5);

        return new Scene(splitPane, 600, 400);
    }

    private void mostrarProductos() {
    }

    private void buscarProducto() {
    }

    private void eliminarProducto() {
    }

    private void agreagarProudcto() {
    }


}
