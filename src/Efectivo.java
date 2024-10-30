public class Efectivo implements Pago {
    @Override
    public double calcularMonto(double monto) {
        return monto * 0.9; // 10% de descuento
    }
}