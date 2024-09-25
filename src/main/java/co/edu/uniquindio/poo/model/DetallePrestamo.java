package co.edu.uniquindio.poo.model;

public class DetallePrestamo {

    private Libro libro;
    private int cantidad;
    private double subtotal;

    public DetallePrestamo(Libro libro, int cantidad) {
        this.libro = libro;
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    private double calcularSubtotal() {
        return cantidad * 1.0; // Suponiendo que el costo es 1 unidad monetaria por libro
    }

    // Getters y Setters
    public Libro getLibro() {
        return libro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }
    
}
