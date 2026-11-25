package org.example.Models;

/**
 * Datos de un usuario para el formulario de registro.
 */
public class Usuario {

    private final String nombre;
    private final String apellido;
    private final String fechaNacimiento;
    private final String pais;
    private final String codigoPostal;
    private final String numero;
    private final String calle;
    private final String ciudad;
    private final String estado;
    private final String telefono;
    private final String email;
    private final String password;

    public Usuario(String nombre, String apellido, String fechaNacimiento, String pais,
                   String codigoPostal, String numero, String calle, String ciudad,
                   String estado, String telefono, String email, String password) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.pais = pais;
        this.codigoPostal = codigoPostal;
        this.numero = numero;
        this.calle = calle;
        this.ciudad = ciudad;
        this.estado = estado;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public String getPais() { return pais; }
    public String getCodigoPostal() { return codigoPostal; }
    public String getNumero() { return numero; }
    public String getCalle() { return calle; }
    public String getCiudad() { return ciudad; }
    public String getEstado() { return estado; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
