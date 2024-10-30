public class Administrador {
    private String nombre;
    private String contraseña;

    public Administrador(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public void agregarFuncion(CatalogoFunciones catalogo, Funcion funcion) {
        catalogo.agregarFuncion(funcion);
    }

    public void cargarGrupo(CatalogoGrupos catalogo, Grupo grupo) {
        catalogo.agregarGrupo(grupo);
    }

    public boolean autenticar(String nombre, String contraseña) {
        return this.nombre.equals(nombre) && this.contraseña.equals(contraseña);
    }
}
