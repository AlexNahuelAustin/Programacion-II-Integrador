package service;

import dao.GenericDAO;
import java.util.List;
import model.HistorialClinica;

/**
 * -Grupo: 50 -Alumnos:
 *
 * @author Alex Nahuel Austin-Comision 17
 * @author Cristian Gabriel Aguirre-Comision 6
 * @author Cain Cabrera Bertilazzi-Comision 11
 * @author Leonel Jesus Aballay-Comision 17
 */
public class HistorialServiceImpl implements GenericService<HistorialClinica> {

    public HistorialServiceImpl(GenericDAO<HistorialClinica> historialClinicaDAO) {
        this.historialClinicaDAO = historialClinicaDAO;
    }

    // Implementación concreta de la interfaz de servicio genérica
    // Se especializa en la entidad HistorialClinica.
    private final GenericDAO<HistorialClinica> historialClinicaDAO;

    // Lógica de negocio y validación, Se valida que el número de historia no sea nulo, ni una cadena vacía o solo espacios en blanco.
    @Override
    public void insertar(HistorialClinica entidad) throws Exception {
        if (entidad.getNumeroHistoria() == null || entidad.getNumeroHistoria().trim().isEmpty()) {
            throw new IllegalArgumentException("El numero del historial no puede ser vacio");
        }
        historialClinicaDAO.insertar(entidad);
    }

// En este método no se ha añadido lógica de negocio adicional, solo delega.
    @Override
    public void actualizar(HistorialClinica entidad) throws Exception {
        historialClinicaDAO.actualizar(entidad);
    }

    // verifica que el ID sea positivo.
    @Override
    public void eliminar(int id) throws Exception {
        if (id <= 0) {
            System.out.println("ID incorrecto");
        }
        
        // Delega la eliminación del registro al DAO.
        historialClinicaDAO.eliminar(id);
    }

    // Se verifica que el ID sea positivo.
    @Override
    public HistorialClinica getById(int id) throws Exception {
        if (id <= 0) {
            System.out.println("ID incorrecto");
        }
        
        // Delega la búsqueda del registro al DAO y devuelve el resultado
        return historialClinicaDAO.getById(id);
    }

    // Delega la recuperación de todos los registros al DAO.
    @Override
    public List<HistorialClinica> getAll() throws Exception {
        return historialClinicaDAO.getAll();
    }
}
