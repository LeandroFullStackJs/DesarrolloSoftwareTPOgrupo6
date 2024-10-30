import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Entrada> entradas;

    public Carrito() {
        this.entradas = new ArrayList<>();
    }

    public void agregarEntrada(Funcion funcion, Asiento asiento) {
        entradas.add(new Entrada(funcion, asiento));
    }

    public double calcularTotal() {
        double total = 0;
        for (Entrada entrada : entradas) {
            total += entrada.getCosto();
        }
        return total;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void vaciarCarrito() {
        entradas.clear();
    }
}