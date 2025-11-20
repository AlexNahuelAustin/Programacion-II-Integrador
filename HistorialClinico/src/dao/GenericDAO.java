package dao;

import java.util.List;

/**
 * -Grupo: 50 -Alumnos:
 *
 * @author Alex Nahuel Austin-Comision 17
 * @author Cristian Gabriel Aguirre-Comision 6
 * @author Cain Cabrera Bertilazzi-Comision 11
 * @author Leonel Jesus Aballay-Comision 17
 */
public interface GenericDAO<T> {
    // Interfaz genérica que define el contrato de acceso a datos.

    // Es una operación para crear y guardar una nueva entidad en la Base de datos.
    void insertar(T entidad) throws Exception;

    // Es una operación para modificar y guardar los cambios de una entidad existente.
    void actualizar(T entidad) throws Exception;

    // Es una operación para eliminar una entidad por su id.
    void eliminar(int id) throws Exception;

    // Es una operación para buscar y obtener una entidad por su id.
    T getById(int id) throws Exception;

    // Es una operación para obtener una lista  de todas las entidades.
    List<T> getAll() throws Exception;

}
