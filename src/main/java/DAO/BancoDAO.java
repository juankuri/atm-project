package DAO;

import Domain.Banco;
import java.sql.*;
import java.util.UUID;

public class BancoDAO {

    private Connection connection;

    public BancoDAO(Connection connection) {
        this.connection = Conexion.conectar();
    }

    public Banco buscarPorId(UUID id) throws SQLException {
        String query = "SELECT * FROM Banco WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double presupuesto = rs.getDouble("presupuesto");
                return new Banco(id, presupuesto, null, null);
            }
        }
        return null;
    }

    public void insertar(Banco banco) throws SQLException {
        String query = "INSERT INTO Banco (id, presupuesto) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, banco.getId().toString());
            stmt.setDouble(2, banco.getPresupuesto());
            stmt.executeUpdate();
        }
    }

    public void actualizarPresupuesto(Banco banco) throws SQLException {
        String query = "UPDATE Banco SET presupuesto = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, banco.getPresupuesto());
            stmt.setString(2, banco.getId().toString());
            stmt.executeUpdate();
        }
    }

    public void disminuirPresupuesto(double monto) throws SQLException {
        String query = "UPDATE Banco SET presupuesto = presupuesto - ? WHERE presupuesto >= ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, monto);
            stmt.setDouble(2, monto);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No se pudo disminuir el presupuesto, monto insuficiente.");
            }
        }
    }
}
