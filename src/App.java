import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static GestionClientes gestionClientes = new GestionClientes();
    private static Administrador administrador = new Administrador("admin", "123123");

    public static void main(String[] args) {
        CatalogoFunciones catalogoFunciones = CatalogoFunciones.getInstance();
        CatalogoGrupos catalogoGrupos = CatalogoGrupos.getInstance();
        Sala sala = new Sala(1, 100); // Ejemplo de sala

        cargarEjemplos(catalogoFunciones, catalogoGrupos, sala);
        cargarMenu(catalogoFunciones, catalogoGrupos, sala);
    }

    public static void cargarEjemplos(CatalogoFunciones catalogoFunciones, CatalogoGrupos catalogoGrupos, Sala sala) {
        // Cargar asientos en la sala
        cargarAsientos(sala);

        // Crear y agregar grupos de ejemplo
        Grupo grupo1 = new Grupo(1, new ArrayList<>());
        grupo1.agregarActor(new Actor("Protagonista", "Juan", "Perez", "Grupo 1"));
        grupo1.agregarActor(new Actor("Antagonista", "Maria", "Gomez", "Grupo 1"));
        catalogoGrupos.agregarGrupo(grupo1);

        Grupo grupo2 = new Grupo(2, new ArrayList<>());
        grupo2.agregarActor(new Actor("Protagonista", "Carlos", "Lopez", "Grupo 2"));
        grupo2.agregarActor(new Actor("Antagonista", "Ana", "Martinez", "Grupo 2"));
        catalogoGrupos.agregarGrupo(grupo2);

        // Crear y agregar funciones de ejemplo
        Funcion funcion1 = new Funcion(1, "2023-12-01", 20, grupo1, 120);
        Funcion funcion2 = new Funcion(2, "2023-12-02", 18, grupo2, 90);
        catalogoFunciones.agregarFuncion(funcion1);
        catalogoFunciones.agregarFuncion(funcion2);

        // Registrar clientes de ejemplo
        gestionClientes.registrarCliente("cliente1@example.com", "password1");
        gestionClientes.registrarCliente("cliente2@example.com", "password2");
    }

    public static void cargarAsientos(Sala sala) {
        // Cargar asientos de diferentes tipos en la sala
        for (int i = 0; i < 20; i++) {
            sala.cargarAsiento(new Platea(new AsientoBasico(i, 50)));
            sala.cargarAsiento(new PalcoAlto(new AsientoBasico(i + 20, 40)));
            sala.cargarAsiento(new Cazuela(new AsientoBasico(i + 40, 30)));
            sala.cargarAsiento(new Paraiso(new AsientoBasico(i + 60, 20)));
        }
    }

    public static void cargarMenu(CatalogoFunciones catalogoFunciones, CatalogoGrupos catalogoGrupos, Sala sala) {
        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Ver Funciones");
            System.out.println("4. Comprar Entradas (Necesita iniciar sesión)");
            System.out.println("5. Cerrar Sesión");
            System.out.println("6. Administrador");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                scanner.next(); // Limpiar entrada inválida
                continue;
            }

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    verFunciones(catalogoFunciones);
                    break;
                case 4:
                    if (gestionClientes.getClienteActual() != null) {
                        gestionClientes.getClienteActual().getCarrito().comprarEntradas(catalogoFunciones, gestionClientes.getClienteActual());
                    } else {
                        System.out.println("Debe iniciar sesión para comprar entradas.");
                    }
                    break;
                case 5:
                    gestionClientes.cerrarSesion();
                    break;
                case 6:
                    menuAdministrador(catalogoFunciones, catalogoGrupos);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    public static void menuAdministrador(CatalogoFunciones catalogoFunciones, CatalogoGrupos catalogoGrupos) {
        System.out.print("Ingrese nombre de administrador: ");
        String nombre = scanner.next();
        System.out.print("Ingrese contraseña: ");
        String contraseña = scanner.next();

        if (administrador.autenticar(nombre, contraseña)) {
            while (true) {
                System.out.println("\n--- Menú Administrador ---");
                System.out.println("1. Agregar Función");
                System.out.println("2. Cargar Grupo");
                System.out.println("3. Volver al Menú Principal");
                System.out.print("Seleccione una opción: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                    scanner.next(); // Limpiar entrada inválida
                    continue;
                }

                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (opcion) {
                    case 1:
                        agregarFuncion(catalogoFunciones);
                        break;
                    case 2:
                        cargarGrupo(catalogoGrupos);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } else {
            System.out.println("Credenciales de administrador incorrectas.");
        }
    }

    public static void agregarFuncion(CatalogoFunciones catalogoFunciones) {
        System.out.print("Ingrese el id de la función : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la fecha de la función (YYYY-MM-DD): ");
        String fecha = scanner.next();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la hora de la función (HH:MM): ");
        int hora = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese la duración de la función (minutos): ");
        int duracion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Aquí deberías seleccionar un grupo de actores ya cargado
        Grupo grupo = seleccionarGrupo();

        // Aquí deberías seleccionar una sala ya cargada
      // VER  Sala sala = seleccionarSala();

        Funcion funcion = new Funcion(id, fecha, hora, grupo, duracion);
        administrador.agregarFuncion(catalogoFunciones, funcion);
        System.out.println("Función agregada exitosamente.");
    }

    public static Sala seleccionarSala() {
        // Implementar lógica para seleccionar una sala ya cargada
        // Por ahora, devolveremos una sala de ejemplo
        return new Sala(1, 100);
    }

    public static void cargarGrupo(CatalogoGrupos catalogoGrupos) {
        System.out.print("Ingrese el ID del grupo: ");
        int idGrupo = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Ingrese el nombre del grupo: ");
        String nombreGrupo = scanner.next();
        scanner.nextLine(); // Limpiar buffer

        List<Actor> actores = new ArrayList<>();
        Grupo grupo = new Grupo(idGrupo, actores); // Define grupo before using it

        while (true) {
            System.out.print("Ingrese el nombre del actor (o 'fin' para terminar): ");
            String nombreActor = scanner.next();
            if (nombreActor.equalsIgnoreCase("fin")) {
                break;
            }
            scanner.nextLine(); // Limpiar buffer
            System.out.print("Ingrese el apellido del actor: ");
            String apellidoActor = scanner.next();
            scanner.nextLine(); // Limpiar buffer
            System.out.print("Ingrese el rol del actor en la obra: ");
            String rolObra = scanner.next();
            scanner.nextLine(); // Limpiar buffer
            Actor actor = new Actor(rolObra, nombreActor, apellidoActor, nombreGrupo);
            actor.setNombreObra(nombreGrupo);
            grupo.agregarActor(actor);
            actores.add(actor);
        }

        administrador.cargarGrupo(catalogoGrupos, grupo);
        System.out.println("Grupo cargado exitosamente.");
    }

    public static Grupo seleccionarGrupo() {
        // Implementar lógica para seleccionar un grupo de actores ya cargado
        // Por ahora, devolveremos un grupo vacío como ejemplo
        return new Grupo(0, new ArrayList<>()); // Example with id and empty list of actors
    }

    public static void registrarCliente() {
        if (gestionClientes.getClienteActual() != null) {
            System.out.println("No puede registrar un nuevo cliente mientras hay una sesión iniciada. Por favor, cierre la sesión actual.");
            return;
        }

        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scanner.nextLine();
        gestionClientes.registrarCliente(email, contraseña);
    }

    public static void iniciarSesion() {
        if (gestionClientes.getClienteActual() != null) {
            System.out.println("Ya hay una sesión iniciada. Por favor, cierre la sesión actual antes de iniciar una nueva.");
            return;
        }

        System.out.print("Ingrese su email: ");
        String email = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contraseña = scanner.nextLine();
        gestionClientes.iniciarSesion(email, contraseña);
    }

    public static void verFunciones(CatalogoFunciones catalogo) {
        for (Funcion funcion : catalogo.getFunciones()) {
            System.out.println(funcion);
        }
    }
}