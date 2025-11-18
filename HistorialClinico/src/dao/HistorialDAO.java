package dao;
import config.DatabaseConnection;
import config.TransaccionesUtils;
import java.sql.Connection;
import java.util.List;
import model.HistorialClinica;
import java.sql.*;
import java.util.ArrayList;
import model.GrupoSanguineo;

/**
 -Grupo: 50
 -Alumnos: 
 * Alex Nahuel Austin-Comision 17
 * Cristian Gabriel Aguirre-Comision 6
 * Cain Cabrera Bertilazzi-Comision 11
 * Leonel Jesus Aballay-Comision 17
 */
public class HistorialDAO implements GenericDAO<HistorialClinica> {
    
    private static final String INSERT_SQL = "INSERT INTO historia_clinica (nro_historia,grupo_sanguineo,antecedentes,medicacion_actual,observaciones) VALUES (?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE historia_clinica SET nro_historia = ?, grupo_sanguineo = ?, antecedentes = ?, medicacion_actual = ?, observaciones = ? WHERE id = ?";
    private static final String DELETE_SQL = "UPDATE historia_clinica SET eliminado = TRUE WHERE id = ?";
    private static final String SELECT_GET_BY_ID_SQL = "SELECT id, nro_historia, grupo_sanguineo, antecedentes, medicacion_actual, observaciones FROM historia_clinica WHERE id = ?";
    private static final String SELECT_ALL_PACIENTE = "SELECT * FROM historia_clinica WHERE eliminado = FALSE";
    

    @Override
   
    public void insertar(HistorialClinica entidad) throws SQLException { 
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {   

            conn.setAutoCommit(false);

            ps.setString(1, entidad.getNumeroHistoria());
            ps.setString(2, entidad.getGrupoSanguineo().toString());
            ps.setString(3, entidad.getAntecedentes());
            ps.setString(4, entidad.getMedicacionActual());
            ps.setString(5, entidad.getObservaciones());

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
                throw new SQLException("No se insertó ningún registro en HistorialClinica");
            }

        } catch (SQLException e) {
            throw new SQLException("Error al insertar HistorialClinica", e);
        }
}


    @Override
    public void actualizar(HistorialClinica entidad) throws SQLException {
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) { 
            ps.setString(1, entidad.getNumeroHistoria());
            ps.setString(2, entidad.getGrupoSanguineo().toString());
            ps.setString(3, entidad.getAntecedentes());
            ps.setString(4, entidad.getMedicacionActual());
            ps.setString(5, entidad.getObservaciones());
            ps.setInt(6, entidad.getId());
            
            TransaccionesUtils.validarTransaccion(conn, ps,"Error al actualizar Historial..");
            
        } catch(Exception e) {   
            System.out.println("Error al conectar a la BD" + e.getMessage());    
        }     
    }

    @Override
    public void eliminar(int id) throws Exception {
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            ps.setInt(1, id);
            
            TransaccionesUtils.validarTransaccion(conn, ps,"Error al eliminar Historial...");
            
        } catch (SQLException e) {
            System.out.println("Error de conexion...");   
        }
    }

    @Override
    public HistorialClinica getById(int id) throws Exception {
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_GET_BY_ID_SQL)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new HistorialClinica(rs.getString("nro_historia"),
                        GrupoSanguineo.fromString(rs.getString("grupo_sanguineo")),
                        rs.getString("antecedentes"),
                        rs.getString("medicacion_actual"),
                        rs.getString("observaciones"),
                        rs.getInt("id"));
                
            } else {
              throw new SQLException("Error, no se encontro algun dato");
            } 
        }
    }

    @Override
    public List<HistorialClinica> getAll() throws Exception {
        ArrayList<HistorialClinica> listaHistorial = new ArrayList<>(); 
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PACIENTE)) {
            ResultSet rs = ps.executeQuery();
              
            while (rs.next()) {                 
                listaHistorial.add(new HistorialClinica(rs.getString("nro_historia"),
                         GrupoSanguineo.fromString(rs.getString("grupo_sanguineo")),
                        rs.getString("antecedentes"),
                        rs.getString("medicacion_actual"),
                        rs.getString("observaciones"),
                        rs.getInt("id")));
            }
        }
        return listaHistorial;
    }
}