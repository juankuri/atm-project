import java.sql.Connection;
// import java.sql.DriverManager;

import DAO.Conexion;

public class TestConexion {
    public static void main(String[] args) {
        Connection conn = Conexion.getConnection();
        if (conn != null) {
            System.out.println("✅ Conexión exitosa");
        } else {
            System.out.println("❌ No se pudo conectar");
        }
    }
}
