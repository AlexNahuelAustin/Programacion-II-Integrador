package service;

import java.util.List;
import model.Paciente;
import dao.GenericDAO;

/**
 * -Grupo: 50 -Alumnos:
 *
 * @author Alex Nahuel Austin-Comision 17
 * @author Cristian Gabriel Aguirre-Comision 6
 * @author Cain Cabrera Bertilazzi-Comision 11
 * @author Leonel Jesus Aballay-Comision 17
 */
public class PacienteServiceImpl implements GenericService<Paciente> {

    // Implementación de la interfaz de servicio genérica (GenericService)
    // especializada para la entidad Paciente.
    private final GenericDAO<Paciente> pacienteDAO;

    // En este método no se ha añadido lógica de negocio adicional, solo delega.
    public PacienteServiceImpl(GenericDAO<Paciente> pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

    // Validaciones de campos obligatorios antes de la inserción.
    @Override
    public void insertar(Paciente entidad) throws Exception {
        if (entidad.getNombre() == null || entidad.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException(" => El nombre no puede ser vacio");
        }
        if (entidad.getApellido() == null || entidad.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser vacio");
        }
        if (entidad.getDni() == null || entidad.getDni().trim().isEmpty()) {
            throw new IllegalArgumentException("El dni no puede ser vacio");
        }
        System.out.println("Insertando paciente: " + entidad.getNombre());
        // Lo delega al DAO.
        pacienteDAO.insertar(entidad);
    }

    // En este método no se ha añadido lógica de negocio adicional, solo delega.
    @Override
    public void actualizar(Paciente entidad) throws Exception {
        System.out.println("Paciente: " + entidad.getNombre() + " Actualizado Correctamente ");
        pacienteDAO.actualizar(entidad);
    }

    // Delega directamente la eliminación por ID al DAO.
    @Override
    public void eliminar(int id) throws Exception {
        pacienteDAO.eliminar(id);
    }

    // Delega la búsqueda por ID al DAO.
    @Override
    public Paciente getById(int id) throws Exception {
        return pacienteDAO.getById(id);
    }

    // Delega la recuperación de todos los registros al DAO.
    @Override
    public List<Paciente> getAll() throws Exception {
        return pacienteDAO.getAll();
    }

}
