public class OfertaDescuento extends AsientoDecorator {
    private double porcentajeDescuento; // Descuento en porcentaje (por ejemplo, 0.20 para 20%)

    public OfertaDescuento(Asiento asiento, double porcentajeDescuento) {
        super(asiento);
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public double obtenerCosto() {
        double costoOriginal = super.obtenerCosto();
        return costoOriginal * (1 - porcentajeDescuento);
    }
}