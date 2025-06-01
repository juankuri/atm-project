package Logic;

/**
 *
 * @author josue
 */
import DAO.CuentaDAO;
import Domain.Cuenta;

import java.sql.Connection;

public class LogicCuenta {

    private final CuentaDAO cuentaDAO;

    public LogicCuenta(Connection con) {
        this.cuentaDAO = new CuentaDAO(con);
    }

    public boolean depositar(String numeroCuenta, double monto) throws Exception {
        if (monto <= 0) return false;

        Cuenta cuenta = cuentaDAO.buscarPorNumeroCuenta(numeroCuenta);
        if (cuenta == null) return false;

        cuenta.depositar(monto);
        cuentaDAO.actualizar(cuenta);
        return true;
    }

    public boolean retirar(String numeroCuenta, double monto) throws Exception {
        if (monto <= 0) return false;

        Cuenta cuenta = cuentaDAO.buscarPorNumeroCuenta(numeroCuenta);
        if (cuenta == null) return false;

        boolean exito = cuenta.retirar(monto);
        if (exito) {
            cuentaDAO.actualizar(cuenta);
        }
        return exito;
    }

    public boolean transferir(String origenCuenta, String destinoCuenta, double monto) throws Exception {
        if (monto <= 0) return false;

        Cuenta origen = cuentaDAO.buscarPorNumeroCuenta(origenCuenta);
        Cuenta destino = cuentaDAO.buscarPorNumeroCuenta(destinoCuenta);

        if (origen == null || destino == null) return false;

        if (!origen.retirar(monto)) return false;

        destino.depositar(monto);
        cuentaDAO.actualizar(origen);
        cuentaDAO.actualizar(destino);
        return true;
    }
}