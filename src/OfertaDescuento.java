public class OfertaDescuento extends AsientoDecorator {
    private static final double PORCENTAJE_DESCUENTO = 0.20; // 20% descuento

    public OfertaDescuento(Asiento asiento, double porcentajeDescuento) {
        super(asiento);
    }

    @Override
    public double obtenerCosto() {
        double costoOriginal = super.obtenerCosto();
        return costoOriginal * (1 - PORCENTAJE_DESCUENTO);
    }
}