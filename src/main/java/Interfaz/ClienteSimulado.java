package Interfaz;

import Domain.Cuenta;
import Domain.Operacion;
import Domain.TipoOperacion;
import Logic.LogicBanco;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author josue
 */
public class ClienteSimulado implements Runnable {
    
    private LogicBanco banco;
    private Cuenta cuenta;
    private Map<Long, Cuenta> cuentasRegistradas;
    private Random random = new Random();

    public ClienteSimulado(LogicBanco banco, Cuenta cuenta, Map<Long, Cuenta> cuentasRegistradas) {
        this.banco = banco;
        this.cuenta = cuenta;
        this.cuentasRegistradas = cuentasRegistradas;
    }

    @Override
    public void run() {
        try {
            
            for (int i = 0; i < 5; i++) {
                int tipo = random.nextInt(2); 
                double monto = 100 + random.nextInt(900); 

                switch (tipo) {
                    case 0: 
                        banco.agregarOperacionACola(new Operacion(TipoOperacion.DEPOSITO, cuenta, monto));
                        System.out.println("[SIMULADO-" + cuenta.getNumCuenta() + "] DEPÓSITO de $" + monto);
                        break;
                    case 1: 
                        banco.agregarOperacionACola(new Operacion(TipoOperacion.RETIRO, cuenta, monto));
                        System.out.println("[SIMULADO-" + cuenta.getNumCuenta() + "] RETIRO de $" + monto);
                        break;
                }

                Thread.sleep(100 + random.nextInt(500));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
