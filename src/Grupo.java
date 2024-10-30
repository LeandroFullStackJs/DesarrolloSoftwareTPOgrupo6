import java.util.List;

public class Grupo {
    private int id;
    private List<Actor> actores;

    public Grupo(int id, List<Actor> actores) {
        this.id = id;
        this.actores = actores;
    }

    public List<Actor> getActores() {
        return actores;
    }
    
    public void agregarActor(Actor actor) {

        actores.add(actor);

    }
    @Override
    public String toString() {
        return "Grupo " + id + " con actores: " + actores;
    }
}