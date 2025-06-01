package DAO;

import Domain.Cuenta;
import Domain.Persona;

import java.sql.*;
import java.util.UUID;

public class CuentaDAO {

    private final Connection con;

    public CuentaDAO(Connection con) {
        this.con = con;
    }

    public void insertar(Cuenta cuenta) throws SQLException {
        String sql = "INSERT INTO Cuenta (id, banco_id, persona_id, num_cuenta, saldo, nip) VALUES (?, NULL, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cuenta.getId().toString());
            ps.setString(2, cuenta.getPersonas().getId().toString());
            ps.setLong(3, cuenta.getNumCuenta());
            ps.setDouble(4, cuenta.getSaldo());
            ps.setInt(5, cuenta.getNip());
            ps.executeUpdate();
        }
    }

    public Cuenta buscarPorNumeroCuenta(String numCuentaStr) throws SQLException {
        String sql = "SELECT * FROM Cuenta WHERE num_cuenta = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numCuentaStr);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UUID id = UUID.fromString(rs.getString("id"));
                    long numCuenta = rs.getLong("num_cuenta");
                    double saldo = rs.getDouble("saldo");
                    int nip = rs.getInt("nip");
                    // String personaId = rs.getString("persona_id");

                    // Buscar persona para asociar a la cuenta
                    Persona persona = null; // Aquí idealmente llamarías PersonaDAO para obtener el objeto Persona con personaId

                    // Para simplicidad, se puede cargar solo el UUID y dejar persona null o buscar persona en otra llamada DAO.

                    Cuenta cuenta = new Cuenta(id, numCuenta, saldo, nip, persona);
                    return cuenta;
                }
                return null;
            }
        }
    }

    public boolean existeNumeroCuenta(String numCuentaStr) throws SQLException {
        String sql = "SELECT 1 FROM Cuenta WHERE num_cuenta = ? LIMIT 1";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, numCuentaStr);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void actualizar(Cuenta cuenta) throws SQLException {
        String sql = "UPDATE Cuenta SET saldo = ?, nip = ? WHERE num_cuenta = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, cuenta.getSaldo());
            ps.setInt(2, cuenta.getNip());
            ps.setLong(3, cuenta.getNumCuenta());
            ps.executeUpdate();
        }
    }
}
