package Domain;

import java.util.UUID;

/**
 *
 * @author josue
 */
public class Cuenta {

    private UUID id;
    private long numCuenta;
    private double saldo;
    private int nip;
    private Persona personas;

    public Cuenta() {
        this.id = UUID.randomUUID();
    }

    public Cuenta(UUID id, long numCuenta, double saldo, int nip, Persona personas) {
        this.id = id;
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.nip = nip;
        this.personas = personas;
    }

    public UUID getId() { return id; }

    public long getNumCuenta() { return numCuenta; }

    public void setNumCuenta(long numCuenta) { this.numCuenta = numCuenta; }

    public double getSaldo() { return saldo; }

    public void setSaldo(double saldo) { this.saldo = saldo; }

    public int getNip() { return nip; }

    public void setNip(int nip) { this.nip = nip; }

    public Persona getPersonas() { return personas; }

    public void setPersonas(Persona personas) { this.personas = personas; }

    public void depositar(double monto) {
        if (monto > 0) {
            this.saldo += monto;
        }
    }

    public boolean retirar(double monto) {
        if (monto > 0 && this.saldo >= monto) {
            this.saldo -= monto;
            return true;
        }
        return false;
    }

    public boolean transferir(Cuenta destino, double monto) {
        if (monto > 0 && this.saldo >= monto && destino != null) {
            this.saldo -= monto;
            destino.depositar(monto);
            return true;
        }
        return false;
    }
}