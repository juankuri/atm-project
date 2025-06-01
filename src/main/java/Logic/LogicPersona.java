package Logic;

import Domain.Cuenta;
import Domain.Persona;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author josue
 */
public class LogicPersona {

    private static final Set<String> cuentasExistentes = new HashSet<>();
    private static final Random random = new Random();

    private String generarNumeroCuentaUnico() {
        String numCuenta;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 16; i++) {
                sb.append(random.nextInt(10));
            }
            numCuenta = sb.toString();
        } while (cuentasExistentes.contains(numCuenta));
        cuentasExistentes.add(numCuenta);
        return numCuenta;
    }

    public Cuenta crearPersonaConCuentaAuto(
        String nombre,
        String apellidoPa,
        String apellidoMa,
        String telefono,
        String correo,
        Date fechaNacimiento,
        String sexo,
        String RFC,
        double saldoInicial,
        int nip 
    ) {
        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setApellidoPa(apellidoPa);
        persona.setApellidoMa(apellidoMa);
        persona.setTelefono(telefono);
        persona.setCorreo(correo);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setSexo(sexo);
        persona.setRFC(RFC);

        String numCuenta = generarNumeroCuentaUnico();

        Cuenta cuenta = new Cuenta(UUID.randomUUID(), Long.parseLong(numCuenta), saldoInicial, nip, persona);

        System.out.println("Cuenta creada. Número de cuenta: " + numCuenta + ", NIP: " + nip);

        return cuenta;
    }
}
