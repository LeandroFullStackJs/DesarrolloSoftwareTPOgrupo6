public class Tertulia extends AsientoDecorator {
    private double porcentajeExtra = 0.03; // 3% extra

    public Tertulia(Asiento asiento) {
        super(asiento);
    }

    @Override
    public double obtenerCosto() {
        return super.obtenerCosto() * (1 + porcentajeExtra);
    }
}