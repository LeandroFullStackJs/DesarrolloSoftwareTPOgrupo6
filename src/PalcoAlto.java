public class PalcoAlto extends Asiento {
    private double porcentajeExtra = 0.15; // 15% extra

    public PalcoAlto(int id, double costoBase) {
        super(id, costoBase);
    }

    @Override
    public double obtenerCosto() {
        return costoBase * (1 + porcentajeExtra);
    }
}