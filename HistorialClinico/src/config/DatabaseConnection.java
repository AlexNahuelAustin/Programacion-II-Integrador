package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 -Grupo: 50
 -Alumnos: 
 * Alex Nahuel Austin-Comision 17
 * Cristian Gabriel Aguirre-Comision 6
 * Cain Cabrera Bertilazzi-Comision 11
 * Leonel Jesus Aballay-Comision 17
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/historial_clinico";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar Driver en memoria",e);
        }
    }
        
        public static Connection getConnection() throws SQLException {
            if (URL == null || URL.isEmpty() || USER == null || USER.isEmpty()) {
                throw new SQLException("Error de conexion, Datos erroneos");
            }
            return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}