package service;

import java.util.List;

/**
 * -Grupo: 50 -Alumnos:
 *
 * @author Alex Nahuel Austin-Comision 17
 * @author Cristian Gabriel Aguirre-Comision 6
 * @author Cain Cabrera Bertilazzi-Comision 11
 * @author Leonel Jesus Aballay-Comision 17
 */
public interface GenericService<T> {

    // Definición de una interfaz genérica.
    // Actualiza una entidad existente.
    void actualizar(T entidad) throws Exception;

    // Inserta una nueva entidad en la fuente de datos.
    void insertar(T entidad) throws Exception;

    // Elimina una entidad por su identificador entero.
    void eliminar(int id) throws Exception;

    // Recupera una entidad por su identificador.
    T getById(int id) throws Exception;

    // Recupera una lista de todas las entidades.
    List<T> getAll() throws Exception;

}
