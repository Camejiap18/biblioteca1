package co.edu.uniquindio.poo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class Biblioteca {

    private String nombre;
    private List<Bibliotecario> bibliotecarios;
    private List<Estudiante> estudiantes;
    private Map<String, Libro> libros;
    private Map<String, Prestamo> prestamos;

    public Biblioteca(String nombre) {
        this.bibliotecarios = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
        this.libros = new HashMap<>();
        this.prestamos = new HashMap<>();
    }

    // Métodos getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Bibliotecario> getBibliotecarios() {
        return bibliotecarios;
    }

    public void setBibliotecarios(List<Bibliotecario> bibliotecarios) {
        this.bibliotecarios = bibliotecarios;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public Map<String, Libro> getLibros() {
        return libros;
    }

    public void setLibros(Map<String, Libro> libros) {
        this.libros = libros;
    }

    public Map<String, Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Map<String, Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    // Métodos para crear bibliotecarios, estudiantes, libros y préstamos
    public void crearBibliotecario(String nombre, String cedula, String telefono, String correo, double salario) {
        Bibliotecario bibliotecario = new Bibliotecario(nombre, cedula, telefono, correo, salario);
        bibliotecarios.add(bibliotecario);
    }
    
    public void crearEstudiante(String nombre, String cedula, String telefono, String correo) {
        Estudiante estudiante = new Estudiante(nombre, cedula, telefono, correo);
        estudiantes.add(estudiante);
    }
    
    public void crearLibro(String codigo, String isbn, String autor, String titulo, String editorial, String fecha, int unidadesDisponibles) {
        Libro libro = new Libro(codigo, isbn, autor, titulo, editorial, fecha, unidadesDisponibles);
        libros.put(codigo, libro);
    }
    
    
    //Método para consultar un libro por el código
    public Libro consultarLibroPorCodigo(String codigo) {
        return libros.get(codigo);
    }

    
    // Método para consultar un estudiante por la cédula
    public Estudiante consultarEstudiantePorCedula(String cedula) {
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                return estudiante;
            }
        }
        return null;
    }
    

    // Método para consultar la cantidad de préstamos de un libro por el nombre
    public int consultarCantidadPrestamosPorNombre(String nombre) {
        int cantidad = 0;
        for (Prestamo prestamo : prestamos.values()) {
            for (DetallePrestamo detalle : prestamo.getDetalles()) {
                if (detalle.getLibro().getTitulo().equals(nombre)) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }
    
    // Método para consultar la cantidad de préstamos de un libro por el autor
    public void reemplazarLibro(String codigo, Libro nuevoLibro) {
        libros.put(codigo, nuevoLibro);
    }

    // Método para crear un préstamo
    public void crearPrestamo(String codigo, Estudiante estudiante, List<DetallePrestamo> detalles, Date fechaPrestamo, Date fechaEntrega) {
        Prestamo prestamo = new Prestamo(codigo, estudiante, detalles, fechaPrestamo, fechaEntrega);
        prestamos.put(codigo, prestamo);
        for (DetallePrestamo detalle : detalles) {
            detalle.getLibro().actualizarUnidadesDisponibles(-detalle.getCantidad());
        }
    }
    
    // Método para adicionar un libro al préstamo
    public void adicionarLibroAlPrestamo(String codigoPrestamo, DetallePrestamo detalle) {
        Prestamo prestamo = prestamos.get(codigoPrestamo);
        prestamo.getDetalles().add(detalle);
        detalle.getLibro().actualizarUnidadesDisponibles(-detalle.getCantidad());
    }
    
    // Método para entregar un préstamo
    public double entregarPrestamo(String codigoPrestamo, Date fechaEntrega) {
        Prestamo prestamo = prestamos.get(codigoPrestamo);
        prestamo.setFechaEntrega(fechaEntrega);
        double costo = prestamo.calcularCosto();
        for (DetallePrestamo detalle : prestamo.getDetalles()) {
            detalle.getLibro().actualizarUnidadesDisponibles(detalle.getCantidad());
        }
        return costo;
    }

    // Método para consultar un préstamo por el código
    public Prestamo consultarPrestamoPorCodigo(String codigo) {
        return prestamos.get(codigo);
    }
    
    // Método para consultar el estudiante con mas préstamos
    public Estudiante estudianteConMasPrestamos() {
        Map<Estudiante, Integer> prestamosPorEstudiante = new HashMap<>();
        for (Prestamo prestamo : prestamos.values()) {
            Estudiante estudiante = prestamo.getEstudiante();
            prestamosPorEstudiante.put(estudiante, prestamosPorEstudiante.getOrDefault(estudiante, 0) + 1);
        }
        Estudiante maxEstudiante = null;
        int maxPrestamos = 0;
        for (Map.Entry<Estudiante, Integer> entry : prestamosPorEstudiante.entrySet()) {
            if (entry.getValue() > maxPrestamos) {
                maxPrestamos = entry.getValue();
                maxEstudiante = entry.getKey();
            }
        }
        return maxEstudiante;
    }
    
    // Método para consultar el total de dinero recaudado por la biblioteca
    public double totalDineroRecaudado() {
        double total = 0;
        for (Prestamo prestamo : prestamos.values()) {
            total += prestamo.calcularCosto();
        }
        return total;
    }
    
    // Método para consultar el total de dinero a pagar a los bibliotecarios
    public double totalDineroAPagarBibliotecarios() {
        double total = 0;
        for (Bibliotecario bibliotecario : bibliotecarios) {
            double salarioBase = bibliotecario.getSalario();
            double comision = 0;
            for (Prestamo prestamo : prestamos.values()) {
                if (prestamo.getEstudiante().getCedula().equals(bibliotecario.getCedula())) {
                    comision += prestamo.calcularCosto() * 0.2; // 20% del valor del préstamo
                }
            }
            total += salarioBase + comision + (comision * 0.02 * 1); // Suponiendo 1 año de antigüedad
        }
        return total;
    }
    
}
