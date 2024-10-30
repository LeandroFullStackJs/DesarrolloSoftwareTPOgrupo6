public class PalcoBajo extends AsientoDecorator {
    private double porcentajeExtra = 0.1; // 10% extra

    public PalcoBajo(Asiento asiento) {
        super(asiento);
    }

    @Override
    public double obtenerCosto() {
        return super.obtenerCosto() * (1 + porcentajeExtra);
    }
}