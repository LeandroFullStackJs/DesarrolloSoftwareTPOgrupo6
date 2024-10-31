import java.util.List;

public class Ticket {
    private Cliente cliente;
    private List<Entrada> entradas;
    private double total;
    private String medioDePago;

    public Ticket(Cliente cliente, List<Entrada> entradas, double total, String medioDePago) {
        this.cliente = cliente;
        this.entradas = entradas;
        this.total = total;
        this.medioDePago = medioDePago;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket de Compra\n");
        sb.append("Cliente: ").append(cliente.getEmail()).append("\n");
        sb.append("Entradas:\n");
        for (Entrada entrada : entradas) {
            sb.append(entrada).append("\n");
        }
        sb.append("Total: $").append(total).append("\n");
        sb.append("Medio de Pago: ").append(medioDePago).append("\n");
        return sb.toString();
    }
}