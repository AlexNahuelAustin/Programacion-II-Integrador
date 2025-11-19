package service;

import java.util.List;

/**
 -Grupo: 50
 -Alumnos: 
 * Alex Nahuel Austin-Comision 17
 * Cristian Gabriel Aguirre-Comision 6
 * Cain Cabrera Bertilazzi-Comision 11
 * Leonel Jesus Aballay-Comision 17
 */
public interface GenericService<T> {
    
    void actualizar (T entidad) throws Exception;
    void insertar (T entidad) throws Exception;
    void eliminar (int id) throws Exception;
    T getById(int id)throws Exception;
    List<T> getAll()throws Exception;
    
}
