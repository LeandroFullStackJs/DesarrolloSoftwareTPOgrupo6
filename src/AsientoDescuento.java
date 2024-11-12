public class AsientoDescuento extends AsientoDecorator {
    private static final double PORCENTAJE_DESCUENTO = 0.20; // 20% de descuento

    public AsientoDescuento(Asiento asiento) {
        super(asiento);
    }

    @Override
    public double obtenerCosto() {
        return super.obtenerCosto() * (1 - PORCENTAJE_DESCUENTO);
    }
}