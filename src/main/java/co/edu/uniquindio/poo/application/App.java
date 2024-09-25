package co.edu.uniquindio.poo.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import co.edu.uniquindio.poo.model.Biblioteca;
import co.edu.uniquindio.poo.model.DetallePrestamo;
import co.edu.uniquindio.poo.model.Estudiante;
import co.edu.uniquindio.poo.model.Libro;

/**
 * Hello world!
 *
 */
public class App {

     public static void main(String[] args) {
        
       // Crear biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca UQ");

        // Crear bibliotecarios
        biblioteca.crearBibliotecario("Cami Mejía", "1094883586", "3005612665", "cami@uqvirtual.edu.co", 3000.0);
        biblioteca.crearBibliotecario("Juan Vanegas", "1094883587", "300561266", "juan@uqvirtual.edu.co", 3200.0);

        // Crear estudiantes
        biblioteca.crearEstudiante("Cami Mejía", "1094883586", "3005612665", "cami@uqvirtual.edu.co");
        biblioteca.crearEstudiante("Juan Vanegas", "1094883587", "300561266", "juan@uqvirtual.edu.co");

        // Crear libros
        biblioteca.crearLibro("Libro1", "978-3-16-148410-2", "Gabriel Garcia Marquez", "Cien años de soledad", "Editorial De Medellin", "2023-01-01", 5);
        biblioteca.crearLibro("Libro2", "978-1-23-456789-3", "Carolina Andújar", "El despertar de las sirena", "Editorial De Bogota", "2023-02-01", 3);

        //Crear detalles de préstamo
        List<DetallePrestamo> detallesPrestamo1 = new ArrayList<>();
        detallesPrestamo1.add(new DetallePrestamo(biblioteca.consultarLibroPorCodigo("Libro1"), 2));
        detallesPrestamo1.add(new DetallePrestamo(biblioteca.consultarLibroPorCodigo("Libro2"), 1));

        //Crear préstamo
        biblioteca.crearPrestamo("Pr01", biblioteca.consultarEstudiantePorCedula("111222333"), detallesPrestamo1, new Date(), new Date());

        //Adicionar libro al préstamo
        DetallePrestamo detalleAdicional = new DetallePrestamo(biblioteca.consultarLibroPorCodigo("L001"), 1);
        biblioteca.adicionarLibroAlPrestamo("Pr01", detalleAdicional);

        //Entregar préstamo
        double costoPrestamo = biblioteca.entregarPrestamo("Pr01", new Date());
        System.out.println("Costo del préstamo: " + costoPrestamo);

        //Consultar los datos del libro
        Libro libroConsultado = biblioteca.consultarLibroPorCodigo("Libro1");
        System.out.println("Datos del libro consultado: " + libroConsultado.getTitulo() + ", Autor: " + libroConsultado.getAutor());

        //Consultar cantidad de préstamos por nombre del libro
        int cantidadPrestamos = biblioteca.consultarCantidadPrestamosPorNombre("Cien años de soledad");
        System.out.println("Cantidad de préstamos del libro "+ libroConsultado.getTitulo() + ": "   + cantidadPrestamos);

        //Reemplazar un libro
        Libro nuevoLibro = new Libro("Libro1", "978-3-16-148410-2", "Autor NN", "Libro NN", "Editorial NN", "2024-01-01", 10);
        biblioteca.reemplazarLibro("Libro1", nuevoLibro);

        //Mostrar reportes
        Estudiante estudianteConMasPrestamos = biblioteca.estudianteConMasPrestamos();
        System.out.println("Estudiante con más préstamos: " + estudianteConMasPrestamos.getNombre());


        double totalDineroRecaudado = biblioteca.totalDineroRecaudado();
        System.out.println("Total de dinero recaudado: " + totalDineroRecaudado);


        double totalDineroAPagarBibliotecarios = biblioteca.totalDineroAPagarBibliotecarios();
        System.out.println("Total de dinero a pagar a los bibliotecarios: " + totalDineroAPagarBibliotecarios);
    }
}
