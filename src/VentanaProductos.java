import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VentanaProductos {
    private TextField txtCodigo, txtNombre, txtCantidad, txtPrecio, txtDescripcion;
    private ListView<String> lista;
    private ListaProductos listaProductos;

    public VentanaProductos(ListaProductos listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Scene getScene() {
        VBox panelIzquierdo = new VBox(20);
        panelIzquierdo.setPadding(new Insets(20));
        panelIzquierdo.getStyleClass().add("root");

        // Sección de campos
        VBox panelCampos = new VBox(10);
        txtCodigo = new TextField();
        txtCodigo.getStyleClass().add("text-field");
        txtNombre = new TextField();
        txtNombre.getStyleClass().add("text-field");
        txtCantidad = new TextField();
        txtCantidad.getStyleClass().add("text-field");
        txtPrecio = new TextField();
        txtPrecio.getStyleClass().add("text-field");
        txtDescripcion = new TextField();
        txtDescripcion.getStyleClass().add("text-field");

        Label lblCodigo = new Label("Código");
        lblCodigo.getStyleClass().add("label");
        Label lblNombre = new Label("Nombre");
        lblNombre.getStyleClass().add("label");
        Label lblCantidad = new Label("Cantidad");
        lblCantidad.getStyleClass().add("label");
        Label lblPrecio = new Label("Precio");
        lblPrecio.getStyleClass().add("label");
        Label lblDescripcion = new Label("Descripción");
        lblDescripcion.getStyleClass().add("label");

        panelCampos.getChildren().addAll(
                lblCodigo, txtCodigo,
                lblNombre, txtNombre,
                lblCantidad, txtCantidad,
                lblPrecio, txtPrecio,
                lblDescripcion, txtDescripcion
        );

        // Sección de botones
        HBox panelBotones = new HBox(25);
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.setPadding(new Insets(20, 0, 0, 0));
        Button btnAgregar = new Button("Agregar");
        btnAgregar.getStyleClass().add("button");
        btnAgregar.setOnAction(e -> agregarProducto());
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.getStyleClass().add("button");
        btnEliminar.setOnAction(e -> eliminarProducto());
        Button btnBuscar = new Button("Buscar");
        btnBuscar.getStyleClass().add("button");
        btnBuscar.setOnAction(e -> buscarProducto());

        panelBotones.getChildren().addAll(btnAgregar, btnEliminar, btnBuscar);

        // Añadir ambas secciones al panel izquierdo
        panelIzquierdo.getChildren().addAll(panelCampos, panelBotones);
        panelIzquierdo.setAlignment(Pos.CENTER);

        VBox panelDerecho = new VBox(20);
        panelDerecho.setPadding(new Insets(20));

        Label listadoProductos = new Label("Listado de productos");
        listadoProductos.getStyleClass().add("label-title");

        lista = new ListView<>();
        lista.setPrefHeight(250);

        // Sección de botones
        HBox panelBotonesDerecha = new HBox(25);
        panelBotonesDerecha.setAlignment(Pos.CENTER);
        panelBotonesDerecha.setPadding(new Insets(20, 0, 0, 0));
        Button btnMostrar = new Button("Mostrar");
        btnMostrar.getStyleClass().add("button");
        btnMostrar.setOnAction(e -> mostrarProductos());
        Button btnSalir = new Button("Salir");
        btnSalir.getStyleClass().add("button");
        btnSalir.setOnAction(e -> MainApp.mostrarMenuPrincipal());

        panelBotonesDerecha.getChildren().addAll(btnMostrar, btnSalir);

        panelDerecho.getChildren().addAll(listadoProductos, lista, panelBotonesDerecha);
        panelDerecho.setAlignment(Pos.CENTER);

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(panelIzquierdo, panelDerecho);
        splitPane.setDividerPositions(0.5);

        Scene scene = new Scene(splitPane, 750, 500);
        scene.getStylesheets().add("stylesProductos.css");

        return scene;
    }

    private void mostrarProductos() {
    }

    private void buscarProducto() {
    }

    private void eliminarProducto() {
    }

    private void agregarProducto() {
    }
}