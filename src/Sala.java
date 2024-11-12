/*import java.util.ArrayList;
import java.util.List;

public class Sala {
    private int idSala;
    private int capacidadMaxima;
    private List<Asiento> asientosDisponibles;

    public Sala(int idSala, int capacidadMaxima) {
        this.idSala = idSala;
        this.capacidadMaxima = capacidadMaxima;
        this.asientosDisponibles = new ArrayList<>();
    }

    public void cargarAsiento(Asiento asiento) {
        if (asientosDisponibles.size() < capacidadMaxima) {
            asientosDisponibles.add(asiento);
        } else {
            System.out.println("Capacidad máxima alcanzada");
        }
    }

    public List<Asiento> getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public Asiento seleccionarAsiento(int numeroAsiento) {
        if (numeroAsiento >= 0 && numeroAsiento < asientosDisponibles.size()) {
            Asiento asiento = asientosDisponibles.get(numeroAsiento);
            if (asiento.isDisponible()) {
                asiento.setDisponible(false);
                return asiento;
            } else {
                System.out.println("El asiento no está disponible.");
            }
        } else {
            System.out.println("Número de asiento inválido.");
        }
        return null;
    }
}*/