public class Platea extends Asiento {
    private double porcentajeExtra = 0.2; // 20% extra

    public Platea(int id, double costoBase) {
        super(id, costoBase);
    }

    @Override
    public double obtenerCosto() {
        return costoBase * (1 + porcentajeExtra);
    }
}