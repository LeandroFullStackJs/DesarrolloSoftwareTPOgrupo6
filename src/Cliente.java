public class Cliente {
    
    private String email;
    private String contraseña;
    private Carrito carrito;

    public Cliente(String email, String contraseña) {
        this.email = email;
        this.contraseña = contraseña;
        this.carrito = new Carrito(); // Cada cliente tiene su propio carrito de compras
    }

    public String getEmail() {
        return email;
    }

    public boolean verificarContraseña(String contraseña) {
        return this.contraseña.equals(contraseña);
    }

    public Carrito getCarrito() {
        return carrito;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "email='" + email + '\'' +
                '}';
    }
}