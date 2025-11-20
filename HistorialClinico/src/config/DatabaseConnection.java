package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * -Grupo: 50 -Alumnos:
 *
 * @author Alex Nahuel Austin-Comision 17
 * @author Cristian Gabriel Aguirre-Comision 6
 * @author Cain Cabrera Bertilazzi-Comision 11
 * @author Leonel Jesus Aballay-Comision 17
 */
public class DatabaseConnection {

    // Constantes privadas y finales para la configuración de la conexión y poder conectar la base de datos.
    private static final String URL = "jdbc:mysql://localhost:3306/historial_clinico";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    //  Asegura que el Driver de MySQL se cargue y se registre en el sistema JDBC de Java Para que luego podamos pedir una conexión válida.
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar Driver en memoria", e);
        }
    }

    // Método para pedir y obtener la conexión activa a la base de datos.
    public static Connection getConnection() throws SQLException {
        if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty()) {
            throw new SQLException("Error de conexion, Datos erroneos");
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
