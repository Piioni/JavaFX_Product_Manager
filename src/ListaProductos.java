import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListaProductos {
    private final List<Producto> listaProductos;

    public ListaProductos() {
        listaProductos = new ArrayList<>();
    }

    public void guardarProductos(File file) throws IOException {
        // Try with resources to save the list of products to a file
        try( ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(listaProductos);
        }
    }

    public void cargarProductos(File file) throws IOException {
        // Try with resources to read the list of products from a file
        try( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            listaProductos.clear();
            List<Producto> productos = (List<Producto>) ois.readObject();
            listaProductos.addAll(productos);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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


    public Producto[] getListaProductos() {
        return listaProductos.toArray(new Producto[0]);
    }
}
