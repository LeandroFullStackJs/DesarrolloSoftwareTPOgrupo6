import java.util.List;
import java.util.ArrayList;

public class Funcion {
    private int id;
    private String fecha;
    private int hora;
    private Grupo grupo;
    private int duracion; // en minutos
    private List<Asiento> asientosDisponibles;

    // Constructor
    public Funcion(int id, String fecha, int hora, Grupo grupo, int duracion) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.grupo = grupo;
        this.duracion = duracion;
        this.asientosDisponibles = new ArrayList<>();  // Inicializamos la lista
        cargarAsientos();  // Llenamos los asientos disponibles al crear la función
    }

    // Método para llenar los asientos disponibles
    private void cargarAsientos() {
        for (int i = 1; i <= 10; i++) {  // Ejemplo: 10 asientos por función
            asientosDisponibles.add(new AsientoBasico(i, 100.0)); // Example cost
        }
    }

    public double calcularCostoTotal() {
        return duracion * 10; // Simple fórmula, ajustar a conveniencia
    }

    public List<Asiento> getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void reservarAsiento(Asiento asiento) {
        asientosDisponibles.remove(asiento);
    }

    @Override
    public String toString() {
        return "Funcion [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", duracion=" + duracion + "]";
    }
}
