public class Credito implements Pago {
    private int cantCuotas;

    public Credito(int cantCuotas) {
        this.cantCuotas = cantCuotas;
    }

    @Override
    public double calcularMonto(double monto) {
        switch (cantCuotas) {
            case 2: return monto * 1.06;
            case 3: return monto * 1.12;
            case 6: return monto * 1.20;
            default: return monto;
        }
    }
}