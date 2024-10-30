public class AsientoBasico extends Asiento {

    public AsientoBasico(double costoBase) {
        super(costoBase);
    }

    @Override
    public double obtenerCosto() {
        return costoBase;
    }
}
