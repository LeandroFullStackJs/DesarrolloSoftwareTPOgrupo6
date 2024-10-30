public class AsientoDecorator extends Asiento {
    protected Asiento asientoDecorado;

    public AsientoDecorator(Asiento asiento) {
        super(asiento.costoBase);
        this.asientoDecorado = asiento;
    }

    @Override
    public double obtenerCosto() {
        return asientoDecorado.obtenerCosto();
    }
}