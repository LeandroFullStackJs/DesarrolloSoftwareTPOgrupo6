public class Tertulia extends Asiento {
    private double porcentajeExtra = 0.03; // 3% extra

    public Tertulia(int id, double costoBase) {
        super(id, costoBase);
    }

    @Override
    public double obtenerCosto() {
        return costoBase * (1 + porcentajeExtra);
    }
}