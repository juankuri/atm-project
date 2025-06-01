package Domain;

import java.util.UUID;

/**
 *
 * @author josue
 */
public class Cajero {
    
    private UUID id;
    private double monto;
    //private TipoOperacion operacionActual;
    private boolean ocupado;

    public Cajero() {
        this.id = UUID.randomUUID();
        this.ocupado = false;
    }
    
    public Cajero(UUID id, double monto, /*TipoOperacion operacionActual,*/ boolean ocupado) {
        this.id = id;
        this.monto = monto;
        //this.operacionActual = operacionActual;
        this.ocupado = ocupado;
    }

    public UUID getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

//    public TipoOperacion getOperacionActual() {
//        return operacionActual;
//    }
//
//    public void setOperacionActual(TipoOperacion operacionActual) {
//        this.operacionActual = operacionActual;
//    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
    
    public void ocupar() {
        this.ocupado = true;
    }

    public void liberar() {
        this.ocupado = false;
    }
}

