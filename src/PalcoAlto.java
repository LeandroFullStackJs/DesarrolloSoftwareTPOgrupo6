public class PalcoAlto extends AsientoDecorator {
    private double porcentajeExtra = 0.15; // 15% extra

    public PalcoAlto(Asiento asiento) {
        super(asiento);
    }

    @Override
    public double obtenerCosto() {
        return super.obtenerCosto() * (1 + porcentajeExtra);
    }
}