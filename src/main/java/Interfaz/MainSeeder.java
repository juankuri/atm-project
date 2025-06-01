package Interfaz;

import DAO.BancoDAO;
import DAO.CajeroDAO;
import DAO.CuentaDAO;
import DAO.PersonaDAO;
import DAO.Conexion;
import Domain.Banco;
import Domain.Cajero;
import Domain.Cuenta;
import Domain.Persona;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.UUID;

public class MainSeeder {

    public static void main(String[] args) {
        try {
            Connection con = Conexion.getConnection();

            BancoDAO bancoDAO = new BancoDAO(con);
            CajeroDAO cajeroDAO = new CajeroDAO(con);
            PersonaDAO personaDAO = new PersonaDAO(con);
            CuentaDAO cuentaDAO = new CuentaDAO(con);

            UUID bancoId = UUID.randomUUID();
            Banco banco = new Banco(bancoId, 1_000_000.0, new ArrayList<>(), new ArrayList<>());
            bancoDAO.insertar(banco);

            Cajero cajero1 = new Cajero(UUID.randomUUID(), 0.0, false);
            Cajero cajero2 = new Cajero(UUID.randomUUID(), 0.0, false);
            cajeroDAO.insertar(cajero1, bancoId);
            cajeroDAO.insertar(cajero2, bancoId);

            UUID persona1Id = UUID.randomUUID();
            UUID persona2Id = UUID.randomUUID();

            Persona p1 = new Persona(
                persona1Id, "Josue", "Gonzalez", "Luna", "555-1234", "josue@example.com",
                Date.valueOf("2000-01-01"), "M", "JGL000101A00"
            );

            Persona p2 = new Persona(
                persona2Id, "Luis", "Martinez", "Reyes", "555-5678", "luis@example.com",
                Date.valueOf("1999-12-12"), "M", "LMR991212A01"
            );

            personaDAO.insertar(p1);
            personaDAO.insertar(p2);

            Cuenta c1 = new Cuenta(UUID.randomUUID(), 100001, 5000.0, 1234, p1);
            Cuenta c2 = new Cuenta(UUID.randomUUID(), 100002, 3000.0, 5678, p2);
            cuentaDAO.insertar(c1);
            cuentaDAO.insertar(c2);

            System.out.println("✅ Base de datos poblada exitosamente.");

        } catch (Exception e) {
            System.err.println("❌ Error al poblar la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
