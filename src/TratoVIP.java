public class TratoVIP extends AsientoDecorator {
    private static final double PORCENTAJE_EXTRA = 0.15; // 15% extra

    public TratoVIP(Asiento asiento) {
        super(asiento);
    }

    @Override
    public double obtenerCosto() {
        return super.obtenerCosto() * (1 + PORCENTAJE_EXTRA);
    }
}