package DAO;

import Domain.Cajero;

import java.sql.*;
import java.util.*;
import java.util.UUID;

public class CajeroDAO {

    private Connection connection;

    public CajeroDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Cajero> listarPorBancoId(UUID bancoId) throws SQLException {
        List<Cajero> cajeros = new ArrayList<>();
        String query = "SELECT * FROM Cajero WHERE banco_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, bancoId.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                UUID id = UUID.fromString(rs.getString("id"));
                boolean ocupado = rs.getBoolean("ocupado");
                double monto = 0.0;
                try {
                    monto = rs.getDouble("monto"); // solo si la columna existe
                } catch (SQLException e) {
                    // si la columna no existe, lo ignoramos
                }
                Cajero cajero = new Cajero(id, monto, ocupado);
                cajeros.add(cajero);
            }
        }
        return cajeros;
    }

    public void insertar(Cajero cajero, UUID bancoId) throws SQLException {
        String query = "INSERT INTO Cajero (id, banco_id, ocupado, monto) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cajero.getId().toString());
            stmt.setString(2, bancoId.toString());
            stmt.setBoolean(3, cajero.isOcupado());
            stmt.setDouble(4, cajero.getMonto()); 
            stmt.executeUpdate();
        }
    }

    public void actualizarOcupado(Cajero cajero) throws SQLException {
        String query = "UPDATE Cajero SET ocupado = ?, monto = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, cajero.isOcupado());
            stmt.setDouble(2, cajero.getMonto());
            stmt.setString(3, cajero.getId().toString());
            stmt.executeUpdate();
        }
    }
}
