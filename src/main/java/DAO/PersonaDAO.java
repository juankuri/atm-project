package DAO;

import Domain.Persona;
import java.sql.*;
import java.util.*;

public class PersonaDAO {

    private final Connection con;

    public PersonaDAO(Connection con) {
        this.con = con;
    }

    public void insertar(Persona persona) throws SQLException {
        String sql = "INSERT INTO Persona (id, nombre, apellido_pa, apellido_ma, telefono, correo, fecha_nacimiento, sexo, rfc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, persona.getId().toString());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellidoPa());
            ps.setString(4, persona.getApellidoMa());
            ps.setString(5, persona.getTelefono());
            ps.setString(6, persona.getCorreo());

            // Convertir java.util.Date a java.sql.Date
            java.sql.Date fechaSql = persona.getFechaNacimiento() != null ? new java.sql.Date(persona.getFechaNacimiento().getTime()) : null;
            ps.setDate(7, fechaSql);

            ps.setString(8, persona.getSexo());
            ps.setString(9, persona.getRFC());
            ps.executeUpdate();
        }
    }

    public Persona buscarPorId(UUID id) throws SQLException {
        String sql = "SELECT * FROM Persona WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Persona(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("nombre"),
                    rs.getString("apellido_pa"),
                    rs.getString("apellido_ma"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getString("sexo"),
                    rs.getString("rfc")
                );
            }
        }
        return null;
    }

    public List<Persona> listarTodas() throws SQLException {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM Persona";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                personas.add(new Persona(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("nombre"),
                    rs.getString("apellido_pa"),
                    rs.getString("apellido_ma"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getString("sexo"),
                    rs.getString("rfc")
                ));
            }
        }
        return personas;
    }

    public void eliminarPorId(UUID id) throws SQLException {
        String sql = "DELETE FROM Persona WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id.toString());
            ps.executeUpdate();
        }
    }
}
