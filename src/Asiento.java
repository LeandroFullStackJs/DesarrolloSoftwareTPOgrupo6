public abstract class Asiento {
    private int id;
    private int numeroAsiento;
    protected double costoBase;
    private boolean disponible;

    public Asiento(int id, double costoBase) {
        this.id = id;
        this.costoBase = costoBase;
        this.disponible = true;
    }

    public int getId() {
        return id;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
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
        //return "Asiento [id=" + id + ", numeroAsiento=" + numeroAsiento + ", costoBase=" + costoBase + ", disponible=" + disponible + "]";
        return "Asiento [id=" + id + ", costoBase=" + costoBase + ", disponible=" + disponible + "]";
    }
}