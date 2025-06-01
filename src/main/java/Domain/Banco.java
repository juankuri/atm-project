package Domain;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author josue
 */
public class Banco {
    
    private UUID id;
    private double presupuesto;
    private List<Cajero> cajeros;
    private List<Cuenta> cuentas;

    public Banco(UUID id, double presupuesto, List<Cajero> cajeros, List<Cuenta> cuentas) {
        this.id = id;
        this.presupuesto = presupuesto;
        this.cajeros = cajeros;
        this.cuentas = cuentas;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<Cajero> getCajeros() {
        return cajeros;
    }

    public void setCajeros(List<Cajero> cajeros) {
        this.cajeros = cajeros;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }
    
    public synchronized void aumentarPresupuesto(double monto) {
        presupuesto += monto;
    }

    public synchronized boolean disminuirPresupuesto(double monto) {
        if (presupuesto >= monto) {
            presupuesto -= monto;
            return true;
        }
        return false;
    }

//    public double getPresupuesto() {
//        return presupuesto;
//    }
}
