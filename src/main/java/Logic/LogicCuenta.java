package Logic;

import Domain.Cuenta;

/**
 *
 * @author josue
 */
public class LogicCuenta {
    
     public boolean depositar(Cuenta cuenta, double monto) {
        if (monto > 0) {
            cuenta.depositar(monto);
            System.out.println("Depósito realizado. Nuevo saldo: " + cuenta.getSaldo());
            return true;
        } else {
            System.out.println("Monto inválido para depósito.");
            return false;
        }
    }

    public boolean retirar(Cuenta cuenta, double monto) {
        if (monto > 0) {
            boolean exito = cuenta.retirar(monto);
            if (exito) {
                System.out.println("Retiro realizado. Nuevo saldo: " + cuenta.getSaldo());
            } else {
                System.out.println("Fondos insuficientes.");
            }
            return exito;
        } else {
            System.out.println("Monto inválido para retiro.");
            return false;
        }
    }
    
    public double consultarSaldo(Cuenta cuenta) {
        return cuenta.getSaldo();
    }

    public boolean verificarNip(Cuenta cuenta, int nipIngresado) {
        return cuenta.getNip() == nipIngresado;
    }
}
