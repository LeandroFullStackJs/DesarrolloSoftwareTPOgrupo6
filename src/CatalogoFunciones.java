import java.util.ArrayList;
import java.util.List;

public class CatalogoFunciones {
    private static CatalogoFunciones instance;
    private List<Funcion> funciones;

    private CatalogoFunciones() {
        funciones = new ArrayList<>();
    }

    public static CatalogoFunciones getInstance() {
        if (instance == null) {
            instance = new CatalogoFunciones();
        }
        return instance;
    }

    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

    public void eliminarFuncion(Funcion funcion) {
        funciones.remove(funcion);
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }
}