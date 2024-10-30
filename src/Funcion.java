public class Funcion {
    private int id;
    private String fecha;
    private int hora;
    private Grupo grupo;
    private int duracion; // en minutos
    private Sala sala;

    public Funcion(int id, String fecha, int hora, Grupo grupo, int duracion, Sala sala) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.grupo = grupo;
        this.duracion = duracion;
        this.sala = sala;
    }

    public double calcularCostoTotal() {
        return duracion * 10; // Simple fórmula, ajustar a conveniencia
    }

    @Override
    public String toString() {
        return "Funcion [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", duracion=" + duracion + "]";
    }

    public Sala getSala() {
        return sala;
    }

    public Asiento seleccionarAsiento(int numeroAsiento) {
        return sala.seleccionarAsiento(numeroAsiento);
    }
}