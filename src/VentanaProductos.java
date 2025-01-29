import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Path;

public class VentanaProductos {
    private TextField txtCodigo, txtNombre, txtCantidad, txtPrecio, txtDescripcion;
    private ListView<String> lista;
    private final ListaProductos listaProductos;
    private final Path PATH;

    public VentanaProductos(ListaProductos listaProductos, Path PATH) {
        this.listaProductos = listaProductos;
        this.PATH = PATH;
    }

    public Scene getScene() {

        // panel izquierdo (Labels, textfields y botones)
        VBox panelIzquierdo = new VBox(20);
        panelIzquierdo.setPadding(new Insets(20));
        panelIzquierdo.getStyleClass().add("root");

        // Panel para los labels y textfields
        VBox panelCampos = new VBox(10);

        // Creación y estilización de los labels
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

        // Creation y estilización de los textfields
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

        // Añadir labels y textfields al panel
        panelCampos.getChildren().addAll(
                lblCodigo, txtCodigo,
                lblNombre, txtNombre,
                lblCantidad, txtCantidad,
                lblPrecio, txtPrecio,
                lblDescripcion, txtDescripcion
        );

        // Panel (izquierdo) de botones
        HBox panelBotones = new HBox(25);
        panelBotones.setAlignment(Pos.CENTER);
        panelBotones.setPadding(new Insets(20, 0, 0, 0));

        // Creación y estilización de los botones
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

        // Añadir ambas secciones al panel izquierdo y centrarlos
        panelIzquierdo.getChildren().addAll(panelCampos, panelBotones);
        panelIzquierdo.setAlignment(Pos.CENTER);

        // panel derecho (Listado de productos)
        VBox panelDerecho = new VBox(20);
        panelDerecho.setPadding(new Insets(20));

        Label listadoProductos = new Label("Listado de productos");
        listadoProductos.getStyleClass().add("label-title");

        lista = new ListView<>();
        lista.setPrefHeight(225);
        lista.getStyleClass().add("list-view");

        // Panel (derecho) de botones
        HBox panelBotonesDerecha = new HBox(25);
        panelBotonesDerecha.setAlignment(Pos.CENTER);
        panelBotonesDerecha.setPadding(new Insets(20, 0, 0, 0));

        // Creación y estilización de los botones
        Button btnMostrar = new Button("Mostrar");
        btnMostrar.getStyleClass().add("button");
        btnMostrar.setOnAction(e -> mostrarProductos());
        Button btnGuardar = new Button("Guardar cambios");
        btnGuardar.getStyleClass().add("button");
        btnGuardar.setOnAction(e -> guardarCambios());
        Button btnSalir = new Button("Salir");
        btnSalir.getStyleClass().add("button");
        btnSalir.setOnAction(e -> MainApp.mostrarMenuPrincipal());

        panelBotonesDerecha.getChildren().addAll(btnMostrar, btnGuardar, btnSalir);

        panelDerecho.getChildren().addAll(listadoProductos, lista, panelBotonesDerecha);
        panelDerecho.setAlignment(Pos.CENTER);

        // Creation del SplitPane (panel dividido) e inicialización de la escena
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(panelIzquierdo, panelDerecho);
        splitPane.setDividerPositions(0.5);

        Scene scene = new Scene(splitPane, 750, 500);
        scene.getStylesheets().add("stylesProductos.css");

        return scene;
    }

    // metodos de los botones

    private void agregarProducto() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String cantidadStr = txtCantidad.getText();
        String precioStr = txtPrecio.getText();
        String descripcion = txtDescripcion.getText();

        // Verificar que los campos no estén vacíos
        if (codigo.isEmpty() || nombre.isEmpty() || cantidadStr.isEmpty() || precioStr.isEmpty() || descripcion.isEmpty()) {
            mostrarAlerta("Todos los campos son obligatorios.");
            return;
        }

        // Verificar que los valores numéricos sean válidos
        int cantidad;
        double precio;
        try {
            cantidad = Integer.parseInt(cantidadStr);
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            mostrarAlerta("Cantidad y precio deben ser valores numéricos.");
            return;
        }

        Producto p = new Producto(codigo, nombre, precio, cantidad, descripcion);
        listaProductos.anadirProducto(p);

        // Limpiar los textfields
        txtCodigo.clear();
        txtNombre.clear();
        txtCantidad.clear();
        txtPrecio.clear();
        txtDescripcion.clear();

        // Añadir el producto a la lista
        lista.getItems().add(p.toString() + "\n");

    }

    // metodo para buscar uno o varios producto
    private void buscarProducto() {
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String cantidadStr = txtCantidad.getText();
        String precioStr = txtPrecio.getText();
        String descripcion = txtDescripcion.getText();

        lista.getItems().clear();

        // Recorrer la lista de productos
        for (Producto p : listaProductos.getListaProductos()) {
            // Inicializa el match a true
            boolean match = true;

            // Verificar si los campos de búsqueda no están vacíos y si coinciden con los valores del producto
            if (!codigo.isEmpty() && !p.getCodigo().equals(codigo)) {
                match = false;
            }
            if (!nombre.isEmpty() && !p.getNombre().equals(nombre)) {
                match = false;
            }
            if (!cantidadStr.isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(cantidadStr);
                    if (p.getCantidad() != cantidad) {
                        match = false;
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Cantidad debe ser un valor numérico.");
                    return;
                }
            }
            if (!precioStr.isEmpty()) {
                try {
                    double precio = Double.parseDouble(precioStr);
                    if (p.getPrecio() != precio) {
                        match = false;
                    }
                } catch (NumberFormatException e) {
                    mostrarAlerta("Precio debe ser un valor numérico.");
                    return;
                }
            }
            if (!descripcion.isEmpty() && !p.getDescripcion().equals(descripcion)) {
                match = false;
            }

            if (match) {
                lista.getItems().add(p.toString());
            }
        }

        if (lista.getItems().isEmpty()) {
            mostrarAlerta("No se encontraron productos que coincidan con los criterios de búsqueda.");
        }

    }

    private void eliminarProducto() {
        // Obtener el producto seleccionado en la lista
        String selectedItem = lista.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String codigo = selectedItem.split(",")[0].trim();
            listaProductos.eliminarProducto(codigo);
            lista.getItems().remove(selectedItem);
        } else {
            mostrarAlerta("Seleccione un producto para eliminar.");
        }

    }

    public void guardarCambios() {
        if (PATH == null) {
            mostrarAlerta("Por favor, seleccione una ubicación para guardar el archivo.");
            return;
        }
        try{
            listaProductos.guardarProductos(PATH);
            System.out.println("Cambios guardados en: " + PATH.toAbsolutePath());
        } catch (IOException e) {
            mostrarAlerta("Error al guardar el archivo: " + e.getMessage());
        }
    }

    private void mostrarProductos() {
        lista.getItems().clear();
        for (Producto p : listaProductos.getListaProductos()) {
            lista.getItems().add(p.toString() + "\n");;
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}