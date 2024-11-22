package modelo;

public class Clientes {
    // Declaramos las variables del cliente
    private Long idClientes;
    private String nombre;
    private String apellido;
    private String genero;
    private String telefono; // Cambiado a String

    // Constructor por defecto
    public Clientes() {
    }

    // Constructor con parámetros
    public Clientes(Long idClientes, String nombre, String apellido, String genero, String telefono) {
        this.idClientes = idClientes;
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.telefono = telefono;
    }

    // Métodos get y set
    public Long getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Long idClientes) {
        this.idClientes = idClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() { // Cambiado a String
        return telefono;
    }

    public void setTelefono(String telefono) { // Cambiado a String
        this.telefono = telefono;
    }
}