package Interfaz;

import Domain.Banco;
import Domain.Cajero;
import Domain.Cuenta;
import Domain.Operacion;
import Domain.TipoOperacion;
import Logic.LogicBanco;
import Logic.LogicPersona;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author josue
 */
public class SimuladorBanco {

    public static void main(String[] args) throws Exception {
//        // Inicialización igual que antes...
//        List<Cajero> cajeros = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            cajeros.add(new Cajero());
//        }
//
//        Banco bancoDomain = new Banco(UUID.randomUUID(), 100000.0, cajeros, new ArrayList<>());
//        LogicBanco banco = new LogicBanco(bancoDomain, cajeros);
//        LogicPersona logicPersona = new LogicPersona();
//        Map<Long, Cuenta> cuentasRegistradas = new HashMap<>();
//
//        // Cuentas simuladas y clientes simulados igual que antes...
//        int cuentasSimuladas = 1000;
//        for (int i = 1; i <= cuentasSimuladas; i++) {
//            String nombre = "Simulado" + i;
//            String apellidoPa = "Cliente" + i;
//            String apellidoMa = "Test";
//            String telefono = "555555555" + i;
//            String correo = "sim" + i + "@correo.com";
//            String sexo = "M";
//            String rfc = "SIM" + i + "RFC";
//            Date fechaNacimiento = new Date();
//            int nip = 1111 + i;
//            Cuenta cuentaSim = logicPersona.crearPersonaConCuentaAuto(
//                    nombre, apellidoPa, apellidoMa, telefono, correo,
//                    fechaNacimiento, sexo, rfc, 0.0, nip
//            );
//            cuentasRegistradas.put(cuentaSim.getNumCuenta(), cuentaSim);
//            double saldoInicial = 200 * i;
//            banco.agregarOperacionACola(new Operacion(TipoOperacion.DEPOSITO, cuentaSim, saldoInicial));
//        }
//        List<Thread> simulados = new ArrayList<>();
//        List<Cuenta> cuentasList = new ArrayList<>(cuentasRegistradas.values());
//        for (Cuenta cuentaSim : cuentasList) {
//            Thread sim = new Thread(new ClienteSimulado(banco, cuentaSim, cuentasRegistradas));
//            simulados.add(sim);
//            sim.start();
//        }
//        JOptionPane.showMessageDialog(null, "Clientes simulados lanzados automáticamente (solo depósito y retiro).");
//
//        boolean salir = false;
//        while (!salir) {
//            String[] opciones = {"Crear cuenta nueva", "Ingresar a mi cuenta", "Ver presupuesto del banco", "Salir"};
//            int opcion = JOptionPane.showOptionDialog(null, "ATM Multihilo", "Menú Principal",
//                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
//            switch (opcion) {
//                case 0: // Crear cuenta
//                    String nombre = JOptionPane.showInputDialog("Nombre:");
//                    String apellidoPa = JOptionPane.showInputDialog("Apellido paterno:");
//                    String apellidoMa = JOptionPane.showInputDialog("Apellido materno:");
//                    String telefono = JOptionPane.showInputDialog("Teléfono (10 dígitos):");
//                    String correo = JOptionPane.showInputDialog("Correo:");
//                    String sexo = JOptionPane.showInputDialog("Sexo (M/F):");
//                    String rfc = JOptionPane.showInputDialog("RFC:");
//                    Date fechaNacimiento = new Date(); // Puedes usar un input más complejo si gustas
//                    double saldoInicial = Double.parseDouble(JOptionPane.showInputDialog("Saldo inicial:"));
//                    int nip = Integer.parseInt(JOptionPane.showInputDialog("NIP (4 dígitos):"));
//                    Cuenta nuevaCuenta = logicPersona.crearPersonaConCuentaAuto(
//                            nombre, apellidoPa, apellidoMa, telefono, correo,
//                            fechaNacimiento, sexo, rfc, 0.0, nip
//                    );
//                    cuentasRegistradas.put(nuevaCuenta.getNumCuenta(), nuevaCuenta);
//                    if (saldoInicial > 0) {
//                        banco.agregarOperacionACola(new Operacion(TipoOperacion.DEPOSITO, nuevaCuenta, saldoInicial));
//                        JOptionPane.showMessageDialog(null, "Depósito inicial en proceso, espera confirmación...");
//                    }
//                    JOptionPane.showMessageDialog(null, "¡Cuenta creada! Tu número de cuenta es: " + nuevaCuenta.getNumCuenta());
//                    loginYOperaciones(nuevaCuenta, banco, cuentasRegistradas);
//                    break;
//                case 1: // Login
//                    long numCuenta = Long.parseLong(JOptionPane.showInputDialog("Número de cuenta:"));
//                    int nipAcceso = Integer.parseInt(JOptionPane.showInputDialog("NIP:"));
//                    Cuenta cuentaLogin = cuentasRegistradas.get(numCuenta);
//                    if (cuentaLogin != null && cuentaLogin.getNip() == nipAcceso) {
//                        loginYOperaciones(cuentaLogin, banco, cuentasRegistradas);
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Cuenta o NIP incorrectos.");
//                    }
//                    break;
//                case 2:
//                    JOptionPane.showMessageDialog(null, "Presupuesto actual del banco: $" + banco.getBancoDomain().getPresupuesto());
//                    break;
//                case 3:
//                default:
//                    salir = true;
//                    banco.cerrarBanco();
//                    for (Thread sim : simulados) {
//                        sim.join();
//                    }
//                    JOptionPane.showMessageDialog(null, "¡Hasta luego!");
//                    break;
//            }
//        }
    }

    public static void loginYOperaciones(
            Cuenta cuenta,
            LogicBanco banco,
            Map<Long, Cuenta> cuentasRegistradas) {
        boolean sesion = true;
        while (sesion) {
            String[] opciones = {"Depósito", "Retiro", "Consulta de saldo", "Transferencia", "Cerrar sesión"};
            int op = JOptionPane.showOptionDialog(null, "Menú de Operaciones", "Operaciones",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
            switch (op) {
                case 0: // Depósito
                    double montoDep = Double.parseDouble(JOptionPane.showInputDialog("Monto a depositar:"));
                    if (montoDep > 0) {
                        cuenta.depositar(montoDep);
                        banco.getBancoDomain().aumentarPresupuesto(montoDep);
                        JOptionPane.showMessageDialog(null, "Depósito realizado, saldo actual: $" + cuenta.getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Monto inválido.");
                    }
                    break;
                case 1: // Retiro
                    double montoRet = Double.parseDouble(JOptionPane.showInputDialog("Monto a retirar:"));
                    if (montoRet > 0 && cuenta.getSaldo() >= montoRet && banco.getBancoDomain().disminuirPresupuesto(montoRet)) {
                        cuenta.retirar(montoRet);
                        JOptionPane.showMessageDialog(null, "Retiro realizado, saldo actual: $" + cuenta.getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Monto inválido o insuficiente.");
                    }
                    break;
                case 2: // Consulta
                    JOptionPane.showMessageDialog(null, "Saldo actual: $" + cuenta.getSaldo());
                    break;
                case 3: // Transferencia
                    long numCuentaDest = Long.parseLong(JOptionPane.showInputDialog("Número de cuenta destino:"));
                    double montoTrans = Double.parseDouble(JOptionPane.showInputDialog("Monto a transferir:"));
                    Cuenta cuentaDestino = cuentasRegistradas.get(numCuentaDest);
                    if (cuentaDestino != null && montoTrans > 0 && cuenta.getSaldo() >= montoTrans) {
                        cuenta.retirar(montoTrans);
                        cuentaDestino.depositar(montoTrans);
                        JOptionPane.showMessageDialog(null, "Transferencia realizada. Saldo actual: $" + cuenta.getSaldo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cuenta destino inválida, monto no permitido o fondos insuficientes.");
                    }
                    break;
                case 4:
                default:
                    sesion = false;
                    JOptionPane.showMessageDialog(null, "Sesión cerrada.");
                    break;
            }
        }
    }
}
