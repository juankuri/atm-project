/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author juankuri
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mariadb://localhost:3306/atm";
    private static final String USER = "root";
    private static final String PASSWORD = "warrior24";

    public static Connection getConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); // <- Esto es CRUCIAL
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("⚠️ Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("⚠️ Error al conectar: " + e.getMessage());
        }
        return null;
    }

 public static Connection conectar() {
        try {
            Class.forName("org.mariadb.jdbc.Driver"); // <- Esto es CRUCIAL
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("⚠️ Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("⚠️ Error al conectar: " + e.getMessage());
        }
        return null;
    }
}
