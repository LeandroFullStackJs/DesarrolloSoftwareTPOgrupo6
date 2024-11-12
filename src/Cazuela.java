public class Cazuela extends Asiento {
    private double porcentajeExtra = 0.05; // 5% extra

    public Cazuela(int id, double costoBase) {
        super(id, costoBase);
    }

    @Override
    public double obtenerCosto() {
        return costoBase * (1 + porcentajeExtra);
    }
}