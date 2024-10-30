public class Actor {
    private String rolObra;
    private String nombreActor; 
    private String apellidoActor;
    private String nombreObra; // Este campo estaba faltando

    public Actor(String rolObra, String nombreActor, String apellidoActor, String nombreObra) {
        this.rolObra = rolObra;
        this.nombreActor = nombreActor;
        this.apellidoActor = apellidoActor;
        this.nombreObra = nombreObra;
    }
    public void setRolObra(String rolObra) {
        this.rolObra = rolObra;
    }

    public void setNombreActor(String nombreActor) {
        this.nombreActor = nombreActor;
    }

    public void setApellidoActor(String apellidoActor) {
        this.apellidoActor = apellidoActor;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }
    public String getRolObra() {
        return rolObra;
    }

    public String getNombreActor() {
        return nombreActor;
    }

    public String getApellidoActor() {
        return apellidoActor;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "rolObra='" + rolObra + '\'' +
                ", nombreActor='" + nombreActor + '\'' +
                ", apellidoActor='" + apellidoActor + '\'' +
                ", nombreObra='" + nombreObra + '\'' +
                '}';
    }
}