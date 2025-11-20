package model;

import java.time.LocalDate;

/**
 * -Grupo: 50 -Alumnos:
 *
 * @author Alex Nahuel Austin-Comision 17
 * @author Cristian Gabriel Aguirre-Comision 6
 * @author Cain Cabrera Bertolazzi-Comision 11
 * @author Leonel Jesus Aballay-Comision 17
 */
public class Paciente extends Base {

    //Declaramos atributos de la clase.
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private HistorialClinica historial;

    //Declaramos Constructores, una para parametros y otro vacio.
    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento, int id) {
        super(false, id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;

    }

    public Paciente() {
        super();
    }

    //Metodos Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public HistorialClinica getHistorial() {
        return historial;
    }

    public void setHistorial(HistorialClinica historial) {
        this.historial = historial;
    }

    // Sobrescritura del método toString()
    @Override
    public String toString() {
        return "Paciente: \n"
                + "  id=" + getId() + "\n"
                + "  nombre= " + nombre + "\n"
                + "  apellido= " + apellido + "\n"
                + "  dni= " + dni + "\n"
                + "  fechaNacimiento= " + fechaNacimiento;
    }
}
