import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class App {
    


        private static Scanner scanner = new Scanner(System.in);
        private static GestionClientes gestionClientes = new GestionClientes();
        private static Administrador administrador = new Administrador("admin","123123"); // Add this line
    
        public static void main(String[] args) {
            CatalogoFunciones catalogoFunciones = CatalogoFunciones.getInstance();
            CatalogoGrupos catalogoGrupos = CatalogoGrupos.getInstance();
            Sala sala = new Sala(1, 100); // Ejemplo de sala
            cargarMenu(catalogoFunciones, catalogoGrupos, sala);
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
    
                int opcion = scanner.nextInt();
    
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
                            comprarEntradas(catalogoFunciones, gestionClientes.getClienteActual());
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
    
                    int opcion = scanner.nextInt();
    
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
            System.out.print("Ingrese la fecha de la función (YYYY-MM-DD): ");
            String fecha = scanner.next();
            System.out.print("Ingrese la hora de la función (HH:MM): ");
            int hora = scanner.nextInt();
            System.out.print("Ingrese la duración de la función (minutos): ");
            int duracion = scanner.nextInt();
            System.out.print("Ingrese el precio base de la función: ");
          //  double precioBase = scanner.nextDouble();
    
            // Aquí deberías seleccionar un grupo de actores ya cargado
            Grupo grupo = seleccionarGrupo();

             // Aquí deberías seleccionar una sala ya cargada
             Sala sala = seleccionarSala();
    
            Funcion funcion = new Funcion(id, fecha, hora, grupo, duracion, sala);
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
            System.out.print("Ingrese el nombre del grupo: ");
            String nombreGrupo = scanner.next();

            List<Actor> actores = new ArrayList<>();
            Grupo grupo = new Grupo(idGrupo, actores); // Define grupo before using it
    
            while (true) {
                System.out.print("Ingrese el nombre del actor (o 'fin' para terminar): ");
                String nombreActor = scanner.next();
                if (nombreActor.equalsIgnoreCase("fin")) {
                    break;
                }
                System.out.print("Ingrese el apellido del actor: ");
                String apellidoActor = scanner.next();
                System.out.print("Ingrese el rol del actor en la obra: ");
                String rolObra = scanner.next();
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
            scanner.nextLine(); // Limpiar buffer
            System.out.print("Ingrese su email: ");
            String email = scanner.nextLine();
            System.out.print("Ingrese su contraseña: ");
            String contraseña = scanner.nextLine();
            gestionClientes.registrarCliente(email, contraseña);
        }
    
        public static void iniciarSesion() {
            scanner.nextLine(); // Limpiar buffer
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
    
        public static void comprarEntradas(CatalogoFunciones catalogo, Cliente cliente) {
            System.out.println("Seleccione una función:");
            for (int i = 0; i < catalogo.getFunciones().size(); i++) {
                System.out.println(i + ". " + catalogo.getFunciones().get(i));
            }
    
            int indiceFuncion = scanner.nextInt();
            Funcion funcionSeleccionada = catalogo.getFunciones().get(indiceFuncion);
    
            System.out.println("Seleccione un tipo de asiento:");
            System.out.println("1. Platea\n2. Palco\n3. Cazuela");
            int tipoAsiento = scanner.nextInt();
            Asiento asientoSeleccionado = null;
    
            switch (tipoAsiento) {
                case 1:
                    asientoSeleccionado = new Platea(new AsientoBasico(50));
                    break;
                case 2:
                    asientoSeleccionado = new PalcoAlto(new AsientoBasico(40));
                    break;
                case 3:
                    asientoSeleccionado = new Cazuela(new AsientoBasico(30));
                    break;
                default:
                    System.out.println("Opción inválida.");
                    return;
            }
    
            cliente.getCarrito().agregarEntrada(funcionSeleccionada, asientoSeleccionado);
            System.out.println("Entrada agregada al carrito.");
    
            // Preguntar si desea proceder con el pago
            System.out.println("¿Desea proceder con el pago? (1. Sí / 2. No)");
            int respuestaPago = scanner.nextInt();
    
            if (respuestaPago == 1) {
                realizarPago(cliente);
            } else {
                System.out.println("Compra no completada. Las entradas siguen en su carrito.");
            }
        }
    
        public static void realizarPago(Cliente cliente) {
            System.out.println("Seleccione método de pago:");
            System.out.println("1. Efectivo\n2. Débito\n3. Crédito");
            int metodoPago = scanner.nextInt();
            Pago estrategiaPago = null;
    
            switch (metodoPago) {
                case 1:
                    estrategiaPago = new Efectivo();
                    break;
                case 2:
                    estrategiaPago = new Debito();
                    break;
                case 3:
                    System.out.println("Ingrese cantidad de cuotas (2, 3, 6): ");
                    int cuotas = scanner.nextInt();
                    estrategiaPago = new Credito(cuotas);
                    break;
                default:
                    System.out.println("Opción de pago inválida.");
                    return;
            }
    
            double total = cliente.getCarrito().calcularTotal();
            double totalConDescuento = estrategiaPago.calcularMonto(total);
    
            System.out.println("Total a pagar: $" + totalConDescuento);
            System.out.println("Pago realizado con éxito. ¡Gracias por su compra!");
    
            cliente.getCarrito().vaciarCarrito();
        }
    
}
