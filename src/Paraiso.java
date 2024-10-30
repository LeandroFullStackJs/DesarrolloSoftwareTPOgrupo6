public class Paraiso extends AsientoDecorator {
    private double porcentajeExtra = 0.02; // 2% extra

    public Paraiso(Asiento asiento) {
        super(asiento);
    }

    @Override
    public double obtenerCosto() {
        return super.obtenerCosto() * (1 + porcentajeExtra);
    }
}