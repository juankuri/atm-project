package Domain;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 *
 * @author josue
 */
public class Persona {
    
    private UUID id; 
    private String nombre;
    private String apellidoPa;
    private String apellidoMa;
    private String telefono;
    private String correo;
    private Date fechaNacimiento;
    private String sexo;
    private String RFC;
    
    public Persona() {
        this.id = UUID.randomUUID();
    }

    public Persona(UUID id, String nombre, String apellidoPa, String apellidoMa, String telefono, String correo, Date fechaNacimiento, String sexo, String RFC) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPa = apellidoPa;
        this.apellidoMa = apellidoMa;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.RFC = RFC;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPa() {
        return apellidoPa;
    }

    public void setApellidoPa(String apellidoPa) {
        this.apellidoPa = apellidoPa;
    }

    public String getApellidoMa() {
        return apellidoMa;
    }

    public void setApellidoMa(String apellidoMa) {
        this.apellidoMa = apellidoMa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && telefono.matches("\\d{10,}")) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("Teléfono inválido");
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo != null && Pattern.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$", correo)) {
            this.correo = correo;
        } else {
            throw new IllegalArgumentException("Correo electrónico inválido");
        }
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
//        if (RFC != null && RFC.matches("[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}")) {
//            this.RFC = RFC;
//        } else {
//            throw new IllegalArgumentException("RFC inválido");
//        }
this.RFC = RFC;
    }
    
}
