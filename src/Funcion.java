import java.util.List;
import java.util.ArrayList;

public class Funcion {
    private int id;
    private String fecha;
    private int hora;
    private Grupo grupo;
    private int duracion; // en minutos
    private List<Asiento> asientosDisponibles;
    private List<Integer> capacidadAsientos;

    // Constructor
    public Funcion(int id, String fecha, int hora, Grupo grupo, int duracion, List<Integer> capacidadAsientos) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.grupo = grupo;
        this.duracion = duracion;
        this.capacidadAsientos = capacidadAsientos;
        this.asientosDisponibles = new ArrayList<>();  // Inicializamos la lista
        cargarAsientos();  // Llenamos los asientos disponibles al crear la función
    }

    // Método para llenar los asientos disponibles
    private void cargarAsientos() {
        for (int i = 1; i <= capacidadAsientos.get(0); i++) {
            asientosDisponibles.add(new Platea(i, 100.0)); // Ejemplo de costo base
        }
        for (int i = 1; i <= capacidadAsientos.get(1); i++) {
            asientosDisponibles.add(new PalcoAlto(i, 80.0));
        }
        for (int i = 1; i <= capacidadAsientos.get(2); i++) {
            asientosDisponibles.add(new PalcoBajo(i, 70.0));
        }
        for (int i = 1; i <= capacidadAsientos.get(3); i++) {
            asientosDisponibles.add(new Cazuela(i, 60.0));
        }
        for (int i = 1; i <= capacidadAsientos.get(4); i++) {
            asientosDisponibles.add(new Tertulia(i, 50.0));
        }
        for (int i = 1; i <= capacidadAsientos.get(5); i++) {
            asientosDisponibles.add(new Paraiso(i, 40.0));
        }
    }

    public double calcularCostoTotal() {
        return duracion * 2; // Ejemplo: 2 unidades monetarias por minuto de duración
    }

    public List<Asiento> getAsientosDisponibles(String tipo) {
        List<Asiento> disponibles = new ArrayList<>();
        for (Asiento asiento : asientosDisponibles) {
            if (asiento.isDisponible() && asiento.getClass().getSimpleName().equals(tipo)) {
                disponibles.add(asiento);
            }
        }
        return disponibles;
    }

    public void reservarAsiento(Asiento asiento) {
        asiento.setDisponible(false);
    }

    public void liberarAsiento(Asiento asiento) {
        asiento.setDisponible(true);
    }

    @Override
    public String toString() {
        return "Funcion [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", duracion=" + duracion + "]";
    }
}
