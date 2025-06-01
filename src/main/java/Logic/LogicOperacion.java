
package Logic;

import Domain.Cuenta;
import Domain.Operacion;

import java.sql.Connection;

import DAO.CuentaDAO;

public class LogicOperacion {

    private final CuentaDAO cuentaDAO;

    public LogicOperacion(Connection connection) {
        this.cuentaDAO = new CuentaDAO(connection);
    }

    public boolean registrarDeposito(String numeroCuenta, double monto) throws Exception {
        if (monto <= 0) return false;

        Cuenta cuenta = cuentaDAO.buscarPorNumeroCuenta(numeroCuenta);
        if (cuenta == null) return false;

        cuenta.depositar(monto);
        cuentaDAO.actualizar(cuenta);

        // Aquí podrías registrar una operación en la BD si lo deseas
        return true;
    }

    public boolean registrarRetiro(String numeroCuenta, double monto) throws Exception {
        if (monto <= 0) return false;

        Cuenta cuenta = cuentaDAO.buscarPorNumeroCuenta(numeroCuenta);
        if (cuenta == null) return false;

        boolean exito = cuenta.retirar(monto);
        if (exito) {
            cuentaDAO.actualizar(cuenta);
            // Registrar operación si aplica
        }
        return exito;
    }

    public boolean registrarTransferencia(String origenCuenta, String destinoCuenta, double monto) throws Exception {
        if (monto <= 0) return false;

        Cuenta origen = cuentaDAO.buscarPorNumeroCuenta(origenCuenta);
        Cuenta destino = cuentaDAO.buscarPorNumeroCuenta(destinoCuenta);
        if (origen == null || destino == null) return false;

        boolean exito = origen.transferir(destino, monto);
        if (exito) {
            cuentaDAO.actualizar(origen);
            cuentaDAO.actualizar(destino);
            // Registrar operación si aplica
        }
        return exito;
    }
}
