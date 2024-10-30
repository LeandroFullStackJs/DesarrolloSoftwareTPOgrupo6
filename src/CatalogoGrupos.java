import java.util.ArrayList;
import java.util.List;

public class CatalogoGrupos {
    private static CatalogoGrupos instance;
    private List<Grupo> grupos;

    private CatalogoGrupos() {
        grupos = new ArrayList<>();
    }

    public static CatalogoGrupos getInstance() {
        if (instance == null) {
            instance = new CatalogoGrupos();
        }
        return instance;
    }

    public void agregarGrupo(Grupo grupo) {
        grupos.add(grupo);
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }
}