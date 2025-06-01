package Domain;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author josue
 */
public class Operacion {

    private UUID id;
    private TipoOperacion tipo;
    private Cuenta cuenta;
    private double monto;
    private Date fecha;
    private Cuenta cuentaDestino;

    public Operacion(TipoOperacion tipo, Cuenta cuenta, double monto) {
        this.id = UUID.randomUUID();
        this.tipo = tipo;
        this.cuenta = cuenta;
        this.monto = monto;
        this.fecha = new Date();
        this.cuentaDestino = null;
    }

    public Operacion(Cuenta origen, Cuenta destino, double monto) {
        this.id = UUID.randomUUID();
        this.tipo = TipoOperacion.TRANSFERENCIA;
        this.cuenta = origen;
        this.cuentaDestino = destino;
        this.monto = monto;
        this.fecha = new Date();
    }

    public UUID getId() { return id; }

    public TipoOperacion getTipo() { return tipo; }

    public Cuenta getCuenta() { return cuenta; }

    public double getMonto() { return monto; }

    public Date getFecha() { return fecha; }

    public Cuenta getCuentaDestino() { return cuentaDestino; }

    public void setCuentaDestino(Cuenta cuentaDestino) { this.cuentaDestino = cuentaDestino; }
}