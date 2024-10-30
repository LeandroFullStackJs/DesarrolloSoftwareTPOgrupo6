public class Platea extends AsientoDecorator {
    private double porcentajeExtra = 0.2; // 20% extra

    public Platea(Asiento asiento) {
        super(asiento);
    }

    @Override
    public double obtenerCosto() {
        return super.obtenerCosto() * (1 + porcentajeExtra);
    }
}