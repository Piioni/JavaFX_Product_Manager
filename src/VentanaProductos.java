import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class VentanaProductos {
    private TextField txtCodigo, txtNombre, txtCantidad, txtPrecio, txtDescripcion;
    private ListView<String> lista;
    private final ListaProductos listaProductos;
    private Path PATH;

    public VentanaProductos(ListaProductos listaProductos, Path PATH) {
        this.listaProductos = listaProductos;
        this.PATH = PATH;
    }

    public Scene getScene() {

        // panel izquierdo (Labels, textfields y botones)
        VBox panelIzquierdo = new VBox(5);
        panelIzquierdo.setPadding(new Insets(35));
        panelIzquierdo.getStyleClass().add("root");

        // Panel para los labels y textfields
        VBox panelCampos = new VBox(10);

        // Creación y estilización de los labels
        Label lblCodigo = new Label("Code");
        lblCodigo.getStyleClass().add("label");
        Label lblNombre = new Label("Name");
        lblNombre.getStyleClass().add("label");
        Label lblCantidad = new Label("Quantity");
        lblCantidad.getStyleClass().add("label");
        Label lblPrecio = new Label("Price");
        lblPrecio.getStyleClass().add("label");
        Label lblDescripcion = new Label("Description");
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

        // Contenedor para los botones izquierdos
        VBox panelBotonesIzquierdo = new VBox(20);
        panelBotonesIzquierdo.setAlignment(Pos.CENTER);
        panelBotonesIzquierdo.setPadding(new Insets(20, 0, 0, 0));

        // Panel (izquierdo) de botones superior
        HBox panelBotonesSuperior = new HBox(25);
        panelBotonesSuperior.setAlignment(Pos.CENTER);
        panelBotonesSuperior.setPadding(new Insets(20, 0, 0, 0));

        // Creación y estilización de los botones
        Button btnAgregar = new Button("Add product");
        btnAgregar.getStyleClass().add("button");
        btnAgregar.setOnAction(e -> agregarProducto());
        Button btnEliminar = new Button("Delete product");
        btnEliminar.getStyleClass().add("button");
        btnEliminar.setOnAction(e -> eliminarProducto());
        Button btnBuscar = new Button("Search product");
        btnBuscar.getStyleClass().add("button");
        btnBuscar.setOnAction(e -> buscarProducto());

        panelBotonesSuperior.getChildren().addAll(btnAgregar, btnEliminar, btnBuscar);

        // Panel (izquierdo) de botones inferior
        HBox panelBotonesInferior = new HBox(25);
        panelBotonesInferior.setAlignment(Pos.CENTER);

        // Creación y estilización de los botones
        Button btnModificar = new Button("Update product");
        btnModificar.getStyleClass().add("button");
        btnModificar.setOnAction(e -> modificarProducto());
        Button btnLimpiar = new Button("Clean fields");
        btnLimpiar.getStyleClass().add("button");
        btnLimpiar.setOnAction(e -> {
            txtCodigo.clear();
            txtNombre.clear();
            txtCantidad.clear();
            txtPrecio.clear();
            txtDescripcion.clear();
        });

        panelBotonesInferior.getChildren().addAll(btnModificar, btnLimpiar);

        // Añadir ambos paneles de botones al panel de botones izquierdo
        panelBotonesIzquierdo.getChildren().addAll(panelBotonesSuperior, panelBotonesInferior);

        // Añadir ambas secciones al panel izquierdo y centrarlos
        panelIzquierdo.getChildren().addAll(panelCampos, panelBotonesIzquierdo);
        panelIzquierdo.setAlignment(Pos.CENTER);

        // panel derecho (Listado de productos)
        VBox panelDerecho = new VBox(20);
        panelDerecho.setPadding(new Insets(20));

        Label listadoProductos = new Label("Products list");
        listadoProductos.getStyleClass().add("label-title");

        lista = new ListView<>();
        lista.setPrefHeight(300);
        lista.getStyleClass().add("list-view");

        // Panel (derecho) de botones
        HBox panelBotonesDerecha = new HBox(25);
        panelBotonesDerecha.setAlignment(Pos.CENTER);
        panelBotonesDerecha.setPadding(new Insets(20, 0, 0, 0));

        // Creación y estilización de los botones
        Button btnMostrar = new Button("Display all");
        btnMostrar.getStyleClass().add("button");
        btnMostrar.setOnAction(e -> mostrarProductos());
        Button btnGuardar = new Button("Save");
        btnGuardar.getStyleClass().add("button");
        btnGuardar.setOnAction(e -> guardarCambios());
        Button btnSalir = new Button("Exit");
        btnSalir.getStyleClass().add("button");
        btnSalir.setOnAction(e -> MainApp.mostrarMenuPrincipal());

        panelBotonesDerecha.getChildren().addAll(btnMostrar, btnGuardar, btnSalir);

        panelDerecho.getChildren().addAll(listadoProductos, lista, panelBotonesDerecha);
        panelDerecho.setAlignment(Pos.CENTER);

        // Creation del SplitPane (panel dividido) e inicialización de la escena
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(panelIzquierdo, panelDerecho);
        splitPane.setDividerPositions(0.5);

        Scene scene = new Scene(splitPane, 1000, 560);
        scene.getStylesheets().add("stylesProductos.css");

        return scene;
    }


    // metodos de los botones
    private void agregarProducto() {
        // Obtener los valores de los textfields
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
        // Verificar que el código no esté duplicado
        if (listaProductos.buscarProducto(codigo) != null) {
            mostrarAlerta("El código ya existe.");
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

        // Print and add the product to the list
        imprimirProducto(p);

    }

    // metodo para buscar uno o varios productos
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
                txtCodigo.setText(p.getCodigo());
                txtNombre.setText(p.getNombre());
                txtCantidad.setText(String.valueOf(p.getCantidad()));
                txtPrecio.setText(String.valueOf(p.getPrecio()));
                txtDescripcion.setText(p.getDescripcion());
                return;
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
            // Obtener el código del producto seleccionado
            String codigo = selectedItem.split("\n")[0].split(":")[1].trim();
            listaProductos.eliminarProducto(codigo);
            lista.getItems().remove(selectedItem);
        } else {
            mostrarAlerta("Seleccione un producto para eliminar.");
        }

    }

    // Metodo para modificar un producto existente
    private void modificarProducto() {
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

        // Buscar el producto en la lista
        Producto p = listaProductos.buscarProducto(codigo);

        if (p != null) {
            // Modificar los valores del producto
            p.setNombre(nombre);
            p.setCantidad(cantidad);
            p.setPrecio(precio);
            p.setDescripcion(descripcion);

            // Actualizar la lista
            lista.getItems().clear();
            mostrarProductos();
        } else {
            mostrarAlerta("Producto no encontrado.");
        }
    }

    // Metodo para guardar los cambios en el archivo sin necesidad de seleccionar la ubicación
    public void guardarCambios() {
        // Verificar si se ha seleccionado una ubicación para guardar el archivo anteriormente
        if (PATH == null) {
            mostrarAlerta("Por favor, seleccione una ubicación para guardar el archivo.");
            guardar();
        } else {
            try {
                listaProductos.guardarProductos(PATH);
                System.out.println("Cambios guardados en: " + PATH.toAbsolutePath());
            } catch (IOException e) {
                mostrarAlerta("Error al guardar el archivo: " + e.getMessage());
            }
        }
    }

    private void guardar() {
        // Permite al usuario seleccionar la ubicación para guardar el archivo
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Archivo");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos JSON (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                // Asegurarse de que el archivo tenga la extensión .json
                if (!file.getPath().endsWith(".json")) {
                    file = new File(file.getPath() + ".json");
                }

                // Guardar la lista de productos en un archivo JSON
                listaProductos.guardarProductos(file.toPath());
                PATH = file.toPath(); // Guardar la ubicación del archivo para futuras referencias
                System.out.println("Guardado en: " + file.getAbsolutePath());
            } catch (IOException e) {
                mostrarAlerta("Error al guardar el archivo: " + e.getMessage());
            } catch (SecurityException e) {
                mostrarAlerta("Permiso denegado: " + e.getMessage());
            } catch (Exception e) {
                mostrarAlerta("Error inesperado: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Por favor, seleccione una ubicación para guardar el archivo.");
        }
    }


    private void mostrarProductos() {
        lista.getItems().clear();
        for (Producto p : listaProductos.getListaProductos()) {
            // Print and add each product to the list
            imprimirProducto(p);
        }
    }

    private void imprimirProducto(Producto producto) {
        // Add the product to the list
        lista.getItems().add(producto.toString());
        // Print the product
        System.out.println(producto.toString());
    }

    private void mostrarAlerta(String mensaje) {
        // Crear una alerta para mostrar un mensaje de error
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}