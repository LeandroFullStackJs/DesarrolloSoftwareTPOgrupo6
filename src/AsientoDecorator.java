public abstract class AsientoDecorator extends Asiento {
    protected Asiento asientoDecorado;

    public AsientoDecorator(Asiento asiento) {
        super(asiento.getId(), asiento.obtenerCosto());
        this.asientoDecorado = asiento;
    }

    @Override
    public double obtenerCosto() {
        return asientoDecorado.obtenerCosto();
    }

    @Override
    public String toString() {
        return asientoDecorado.toString();
    }
}