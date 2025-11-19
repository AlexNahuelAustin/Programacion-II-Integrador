package model;

/**
 -Grupo: 50
 -Alumnos: 
 * Alex Nahuel Austin-Comision 17
 * Cristian Gabriel Aguirre-Comision 6
 * Cain Cabrera Bertilazzi-Comision 11
 * Leonel Jesus Aballay-Comision 17
 */
public abstract class Base { 
    //Declaramos atributos de la clase abstracta.
    private boolean eliminado;
    private int id;
    
    //Se crea constructor con parametros y vacio.
    public Base(boolean eliminado, int id) { 
        this.eliminado = eliminado;
        this.id = id;
    }

    public Base() {
    }
    
    //Getters y Setters
    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
