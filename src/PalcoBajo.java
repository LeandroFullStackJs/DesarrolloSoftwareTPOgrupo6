public class PalcoBajo extends Asiento {
    private double porcentajeExtra = 0.10; // 10% extra

    public PalcoBajo(int id, double costoBase) {
        super(id, costoBase);
    }

    @Override
    public double obtenerCosto() {
        return costoBase * (1 + porcentajeExtra);
    }
}