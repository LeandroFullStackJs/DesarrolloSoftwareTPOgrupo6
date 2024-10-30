public class Debito implements Pago {
    @Override
    public double calcularMonto(double monto) {
        return monto; // No recargo ni descuento
    }
}