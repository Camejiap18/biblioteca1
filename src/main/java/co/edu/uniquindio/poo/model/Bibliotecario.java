package co.edu.uniquindio.poo.model;

public class Bibliotecario {

    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private double salario;
    
    public Bibliotecario(String nombre, String cedula, String telefono, String correo, double salario) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correo = correo;
        this.salario = salario;
    }

    //Métodos Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public double getSalario() {
        return salario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double calcularSalarioTotal(int añosAntiguedad, double totalPrestamos) {
        double comision = totalPrestamos * 0.2;
        double bonificacion = comision * 0.02 * añosAntiguedad;
        return salario + comision + bonificacion;
    }
    
}
