package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * -Grupo: 50 -Alumnos:
 *
 * @author Alex Nahuel Austin-Comision 17
 * @author Cristian Gabriel Aguirre-Comision 6
 * @author Cain Cabrera Bertilazzi-Comision 11
 * @author Leonel Jesus Aballay-Comision 17
 */
public class TransaccionesUtils {

    // Clase de utilidad para manejar la lógica de COMMIT y ROLLBACK en transacciones JDBC.
    public static void validarTransaccion(Connection conn, PreparedStatement ps, String mensajeError) throws SQLException {
        try {
            conn.setAutoCommit(false);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected >= 1) {
                conn.commit();
            } else {
                conn.rollback();
                throw new SQLException(mensajeError + "  No se afectó ninguna fila");
            }
        } catch (SQLException e) {
            conn.rollback();
            throw new SQLException(mensajeError + " " + e.getMessage(), e);
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
