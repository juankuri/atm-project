package Logic;

import Domain.Banco;
import Domain.Cajero;
import Domain.Cuenta;
import Domain.Operacion;
import Domain.TipoOperacion;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author josue
 */
public class LogicCajero implements Runnable {

    private Cajero cajero;
    private BlockingQueue<Operacion> colaOperaciones;
    private Banco bancoDomain;
    private volatile boolean activo = true;

    public LogicCajero(Cajero cajero, BlockingQueue<Operacion> colaOperaciones, Banco bancoDomain) {
        this.cajero = cajero;
        this.colaOperaciones = colaOperaciones;
        this.bancoDomain = bancoDomain;
    }

    @Override
    public void run() {
        while (activo && !Thread.currentThread().isInterrupted()) {
            try {
                Operacion operacion = colaOperaciones.take();

                cajero.ocupar();
                System.out.println("Cajero " + cajero.getId() + " atiende "
                        + operacion.getTipo() + " a la cuenta " + operacion.getCuenta().getNumCuenta());

                double monto = operacion.getMonto();

                switch (operacion.getTipo()) {
                    case DEPOSITO:
                        operacion.getCuenta().depositar(monto);
                        bancoDomain.aumentarPresupuesto(monto);
                        System.out.println("Depósito realizado de $" + monto
                                + ". Presupuesto banco: $" + bancoDomain.getPresupuesto());
                        break;
                    case RETIRO:
                        if (operacion.getCuenta().retirar(monto) && bancoDomain.disminuirPresupuesto(monto)) {
                            System.out.println("Retiro realizado de $" + monto
                                    + ". Presupuesto banco: $" + bancoDomain.getPresupuesto());
                        } else {
                            System.out.println("Retiro fallido. Fondos insuficientes en cuenta o banco.");
                        }
                        break;
                    case TRANSFERENCIA:
                        Cuenta origen = operacion.getCuenta();
                        Cuenta destino = operacion.getCuentaDestino();
                        if (origen.getSaldo() >= monto) {
                            origen.retirar(monto);
                            destino.depositar(monto);
                            
                            System.out.println("Transferencia de $" + monto
                                    + " de cuenta " + origen.getNumCuenta()
                                    + " a cuenta " + destino.getNumCuenta() + " realizada.");
                        } else {
                            System.out.println("Transferencia fallida. Fondos insuficientes.");
                        }
                        break;
                    case CONSULTA:
                        System.out.println("Consulta de saldo: $" + operacion.getCuenta().getSaldo());
                        break;
                }

                Thread.sleep(2000);

                cajero.liberar();
                System.out.println("Cajero " + cajero.getId() + " terminó con la cuenta " + operacion.getCuenta().getNumCuenta());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("Cajero " + cajero.getId() + " fue detenido.");
    }

    public void detener() {
        activo = false;
    }
}
