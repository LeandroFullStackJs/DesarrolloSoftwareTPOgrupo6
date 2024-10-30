public abstract class Asiento {
    protected double costoBase;
    private boolean disponible;

    public Asiento(double costoBase) {
        this.costoBase = costoBase;
        this.disponible = true;
    }

    public abstract double obtenerCosto();

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Asiento [costoBase=" + costoBase + ", disponible=" + disponible + "]";
    }
}