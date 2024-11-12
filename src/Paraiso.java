public class Paraiso extends Asiento {
    private double porcentajeExtra = 0.02; // 2% extra

    public Paraiso(int id, double costoBase) {
        super(id, costoBase);
    }

    @Override
    public double obtenerCosto() {
        return costoBase * (1 + porcentajeExtra);
    }
}