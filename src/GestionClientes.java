import java.util.ArrayList;
import java.util.List;

public class GestionClientes {
    private List<Cliente> clientes;
    private Cliente clienteActual;

    public GestionClientes() {
        this.clientes = new ArrayList<>();
        this.clienteActual = null; // Cuando ningún cliente ha iniciado sesión
    }

    public void registrarCliente(String email, String contraseña) {
        Cliente nuevoCliente = new Cliente(email, contraseña);
        clientes.add(nuevoCliente);
        System.out.println("Cliente registrado con éxito: " + email);
    }

    public boolean iniciarSesion(String email, String contraseña) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email) && cliente.verificarContraseña(contraseña)) {
                clienteActual = cliente;
                System.out.println("Inicio de sesión exitoso. Bienvenido, " + email);
                return true;
            }
        }
        System.out.println("Email o contraseña incorrectos.");
        return false;
    }

    public Cliente getClienteActual() {
        return clienteActual;
    }

    public void cerrarSesion() {
        clienteActual = null;
        System.out.println("Sesión cerrada.");
    }
}