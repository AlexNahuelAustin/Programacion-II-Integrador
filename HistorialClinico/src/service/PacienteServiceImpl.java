package service;

import java.util.List;
import model.Paciente;
import dao.GenericDAO;

/**
 -Grupo: 50
 -Alumnos: 
 * Alex Nahuel Austin-Comision 17
 * Cristian Gabriel Aguirre-Comision 6
 * Cain Cabrera Bertilazzi-Comision 11
 * Leonel Jesus Aballay-Comision 17
 */

public class PacienteServiceImpl implements GenericService<Paciente>{
    
    private final GenericDAO<Paciente> pacienteDAO;

    public PacienteServiceImpl(GenericDAO<Paciente> pacienteDAO) {
        this.pacienteDAO = pacienteDAO;
    }

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
        pacienteDAO.insertar(entidad);
    }

    @Override
    public void actualizar(Paciente entidad) throws Exception {
        System.out.println("Paciente: " + entidad.getNombre() + " Actualizado Correctamente ");
        pacienteDAO.actualizar(entidad);
    }

    @Override
    public void eliminar(int id) throws Exception {
        pacienteDAO.eliminar(id);
    }

    @Override
    public Paciente getById(int id) throws Exception {
        return pacienteDAO.getById(id);
    }

    @Override
    public List<Paciente> getAll() throws Exception {
        return pacienteDAO.getAll();
    }
    
}
   
