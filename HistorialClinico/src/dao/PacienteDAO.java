package dao;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import model.GrupoSanguineo;
import model.HistorialClinica;
import config.TransaccionesUtils;


/**
 -Grupo: 50
 -Alumnos: 
 * Alex Nahuel Austin-Comision 17
 * Cristian Gabriel Aguirre-Comision 6
 * Cain Cabrera Bertilazzi-Comision 11
 * Leonel Jesus Aballay-Comision 17
 */
public class PacienteDAO implements GenericDAO<Paciente> {
    
     
    private static final String INSERT_SQL = "INSERT INTO paciente (nombre,apellido,dni,fecha_nacimiento,historia_clinica_id) VALUES (?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE paciente SET nombre = ?, apellido = ?, dni = ?, fecha_nacimiento = ? WHERE id = ?";
    private final static String DELETE_SQL = "UPDATE paciente SET eliminado = TRUE WHERE id = ?"; 
    private static final String SELECT_BY_ID ="SELECT id, nombre, apellido, dni, fecha_nacimiento FROM paciente WHERE id = ? AND eliminado = FALSE";;
    private final static String SELECT_ALL_SQL = "SELECT * FROM paciente WHERE eliminado = FALSE";

    
    @Override
    public void insertar(Paciente entidad) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

        conn.setAutoCommit(false);

        ps.setString(1, entidad.getNombre());
        ps.setString(2, entidad.getApellido());
        ps.setString(3, entidad.getDni());
        ps.setDate(4, java.sql.Date.valueOf(entidad.getFechaNacimiento()));
        ps.setInt(5, entidad.getHistorial().getId());

        int rowsAffected = ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                entidad.setId(rs.getInt(1));
            }
        }

        if (rowsAffected > 0) {
            conn.commit();
        } else {
            conn.rollback();
            throw new SQLException("No se insertó ningún registro en Paciente");
        }

    } catch (SQLException e) {
        throw new SQLException("Error al insertar Paciente", e);
    }
}

    @Override
    public void actualizar(Paciente entidad) throws SQLException {
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            ps.setString(1,entidad.getNombre());
            ps.setString(2, entidad.getApellido());
            ps.setString(3, entidad.getDni());
            ps.setDate(4,java.sql.Date.valueOf(entidad.getFechaNacimiento()));
            ps.setInt(5,entidad.getId());
            
            TransaccionesUtils.validarTransaccion(conn, ps ,"Error al actualizar Paciente");
            
        } catch (SQLException e){
            System.out.println("Error de conexion a base de datos");
        }
           
    }

    @Override
    public void eliminar(int id) throws Exception {
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            TransaccionesUtils.validarTransaccion(conn, ps,"Error al eliminar Paciente");
            
        } catch (SQLException e) {
            System.out.println("Error de conexion o al ejecutar la transaccion: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Paciente getById(int id) throws Exception {
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Paciente(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("dni"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getInt("id"));
                    }
                
            } catch (SQLException e ) {
                System.out.println("Error al Traer al paciente... ");
            }

        }
        return null;
    }
    
 @Override
    public List<Paciente> getAll() throws SQLException {
        List<Paciente> pacientes = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                pacientes.add(new Paciente(
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("dni"),
                    rs.getDate("fecha_nacimiento").toLocalDate(),
                    rs.getInt("id")
                ));
            }
        }
        return pacientes;
    }
    
}