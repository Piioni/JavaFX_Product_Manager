public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    private String descripcion;

    public Producto(String codigo, String nombre, double precio, int cantidad, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public String toJson(){
        return "{\n" +
                "  \"codigo\": \"" + codigo + "\",\n" +
                "  \"nombre\": \"" + nombre + "\",\n" +
                "  \"precio\": " + precio + ",\n" +
                "  \"cantidad\": " + cantidad + ",\n" +
                "  \"descripcion\": \"" + descripcion + "\"\n" +
                "}";
    }

    public static Producto fromJson(String json) {
        json = json.trim();
        json = json.substring(1, json.length() - 1); // Remove the curly braces
        String[] fields = json.split(",\n");

        String codigo = fields[0].split(":")[1].trim().replace("\"", "");
        String nombre = fields[1].split(":")[1].trim().replace("\"", "");
        double precio = Double.parseDouble(fields[2].split(":")[1].trim());
        int cantidad = Integer.parseInt(fields[3].split(":")[1].trim());
        String descripcion = fields[4].split(":")[1].trim().replace("\"", "");

        return new Producto(codigo, nombre, precio, cantidad, descripcion);
    }

    public String toString() {
        return  "Código: " + codigo + "\n" +
                "Nombre: " + nombre + "\n" +
                "Precio: " + precio + "\n" +
                "Cantidad: " + cantidad + "\n" +
                "Descripción: " + descripcion + "\n";
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
