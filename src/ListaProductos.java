import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private final List<Producto> listaProductos;

    public ListaProductos() {
        listaProductos = new ArrayList<>();
    }

    public void guardarProductos(Path path) throws IOException {
        // Guardar la lista de productos en un archivo JSON
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        for (Producto p : listaProductos) {
            json.append(p.toJson()).append(",\n");
        }
        json.deleteCharAt(json.length() - 2);
        json.append("]");
        Files.writeString(path, json.toString());

    }

    public void cargarProductos(Path path) throws IOException {
        // Cargar la lista de productos desde un archivo JSON
        if (!Files.exists(path)) {
            System.out.println("El archivo no existe.");
            return;
        }
        // Leer el archivo JSON
        String json = Files.readString(path);
        json = json.trim();
        if (json.startsWith("[")) {
            json = json.substring(1);
        }
        if (json.endsWith("]")) {
            json = json.substring(0, json.length() - 1);
        }
        json = json.trim();
        if (json.isEmpty()) {
            return;
        }
        String[] productos = json.split("},\\s*\\{");
        for (int i = 0; i < productos.length; i++) {
            if (!productos[i].startsWith("{")) {
                productos[i] = "{" + productos[i];
            }
            if (!productos[i].endsWith("}")) {
                productos[i] = productos[i] + "}";
            }
            listaProductos.add(Producto.fromJson(productos[i]));
        }
    }

    public void anadirProducto(Producto p) {
        listaProductos.add(p);
    }

    public void eliminarProducto(int i) {
        if (i >= 0 && i < listaProductos.size()) {
            listaProductos.remove(i);
        } else {
            System.out.println("Índice fuera de rango.");
        }
    }

    public Producto buscarProducto(String codigo) {
        for (Producto p : listaProductos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public void clear() {
        listaProductos.clear();
    }


    public Producto[] getListaProductos() {
        return listaProductos.toArray(new Producto[0]);
    }
}
