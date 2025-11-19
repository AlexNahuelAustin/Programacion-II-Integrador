package service;

import dao.GenericDAO;
import java.util.List;
import model.HistorialClinica;

/**
 -Grupo: 50
 -Alumnos: 
 * Alex Nahuel Austin-Comision 17
 * Cristian Gabriel Aguirre-Comision 6
 * Cain Cabrera Bertilazzi-Comision 11
 * Leonel Jesus Aballay-Comision 17
 */

public class HistorialServiceImpl implements GenericService<HistorialClinica> {

    public HistorialServiceImpl(GenericDAO<HistorialClinica> historialClinicaDAO) {
        this.historialClinicaDAO = historialClinicaDAO;
    }

    private final GenericDAO<HistorialClinica> historialClinicaDAO;


    @Override
    public void insertar(HistorialClinica entidad) throws Exception {  
        if (entidad.getNumeroHistoria() == null || entidad.getNumeroHistoria().trim().isEmpty()) {
            throw new IllegalArgumentException("El numero del historial no puede ser vacio");
        }
        
        historialClinicaDAO.insertar(entidad);
    }
     
    @Override
    public void actualizar(HistorialClinica entidad) throws Exception {
        historialClinicaDAO.actualizar(entidad);
    }

    @Override
    public void eliminar(int id) throws Exception {
        if (id <= 0) {
            System.out.println("ID incorrecto");
        }
        
        historialClinicaDAO.eliminar(id);
    }

    @Override
    public HistorialClinica getById(int id) throws Exception {
        if (id <= 0) {
            System.out.println("ID incorrecto");
        }
        return historialClinicaDAO.getById(id);
    }

    @Override
    public List<HistorialClinica> getAll() throws Exception {
        return historialClinicaDAO.getAll(); 
    }   
}
