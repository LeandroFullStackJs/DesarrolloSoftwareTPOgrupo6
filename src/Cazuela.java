public class Cazuela extends AsientoDecorator {
    private double porcentajeExtra = 0.05; // 5% extra

    public Cazuela(Asiento asiento) {
        super(asiento);
    }

    @Override
    public double obtenerCosto() {
        return super.obtenerCosto() * (1 + porcentajeExtra);
    }
}