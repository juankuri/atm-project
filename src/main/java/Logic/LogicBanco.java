package Logic;

import Domain.Banco;
import Domain.Cajero;
import Domain.Cuenta;
import Domain.Operacion;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author josue
 */
public class LogicBanco {

    private Banco bancoDomain;
    private List<Cajero> cajeros;
    private BlockingQueue<Operacion> colaOperaciones;
    private List<Thread> hilosCajeros;

    public LogicBanco(Banco bancoDomain, List<Cajero> cajeros) {
        this.bancoDomain = bancoDomain;
        this.cajeros = cajeros;
        this.colaOperaciones = new LinkedBlockingQueue<>();
        this.hilosCajeros = new ArrayList<>();

        for (Cajero cajero : cajeros) {
            Thread t = new Thread(new LogicCajero(cajero, colaOperaciones, bancoDomain));
            t.start();
            hilosCajeros.add(t);
        }
    }

    public void agregarOperacionACola(Operacion operacion) {
        try {
            colaOperaciones.put(operacion);
            System.out.println("Operación " + operacion.getTipo() + " agregada a la cola para cuenta " + operacion.getCuenta().getNumCuenta());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void cerrarBanco() {
        for (Thread t : hilosCajeros) {
            t.interrupt();
        }
        System.out.println("Banco cerrado, cajeros detenidos.");
    }

    public Banco getBancoDomain() {
        return bancoDomain;
    }

}
