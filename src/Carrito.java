import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Carrito {
    private List<Entrada> entradas;

    public Carrito() {
        this.entradas = new ArrayList<>();
    }

    public void agregarEntrada(Funcion funcion, Asiento asiento) {
        entradas.add(new Entrada(funcion, asiento));
    }

    public double calcularTotal() {
        double total = 0;
        for (Entrada entrada : entradas) {
            total += entrada.getCosto();
        }
        return total;
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    public void vaciarCarrito() {
        entradas.clear();
    }

    public void comprarEntradas(CatalogoFunciones catalogo, Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
        boolean agregarMasFunciones = true;

        while (agregarMasFunciones) {
            try {
                System.out.println("Seleccione una función:");
                for (int i = 0; i < catalogo.getFunciones().size(); i++) {
                    System.out.println(i + ". " + catalogo.getFunciones().get(i));
                }

                if (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                    scanner.next(); // Limpiar entrada inválida
                    continue;
                }

                int indiceFuncion = scanner.nextInt();
                scanner.nextLine();  // Limpiar buffer
                Funcion funcionSeleccionada = catalogo.getFunciones().get(indiceFuncion);

                boolean agregarMasAsientos = true;
                while (agregarMasAsientos) {
                    System.out.println("Seleccione un tipo de asiento:");
                    System.out.println("1. Platea\n2. Palco Alto\n3. Cazuela\n4. Paraiso");

                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                        scanner.next(); // Limpiar entrada inválida
                        continue;
                    }

                    int tipoAsiento = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    Asiento asientoDecorado = null;

                    switch (tipoAsiento) {
                        case 1:
                            asientoDecorado = new Platea(new AsientoBasico(50));
                            break;
                        case 2:
                            asientoDecorado = new PalcoAlto(new AsientoBasico(40));
                            break;
                        case 3:
                            asientoDecorado = new Cazuela(new AsientoBasico(30));
                            break;
                        case 4:
                            asientoDecorado = new Paraiso(new AsientoBasico(20));
                            break;
                        default:
                            System.out.println("Opción inválida.");
                            return;
                    }

                        System.out.println("Seleccione un asiento disponible:");
                       // List<Asiento> asientos = funcionSeleccionada.getAsientosDisponibles();
                       List<Asiento> asientos = funcionSeleccionada.getAsientosDisponibles();

                       if (funcionSeleccionada.getAsientosDisponibles().isEmpty()) {
                        System.out.println("No hay asientos disponibles para esta función.");
                        } else {
                        System.out.println("Asientos disponibles:");
                        for (Asiento asiento : funcionSeleccionada.getAsientosDisponibles()) {
                            System.out.println(asiento);
                        }
                    }
                        
   
                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                        scanner.next(); // Limpiar entrada inválida
                        continue;
                    }

                    int numeroAsiento = scanner.nextInt();
                    scanner.nextLine();  // Limpiar buffer
                    Asiento asientoSeleccionado = asientos.get(numeroAsiento);

                    if (asientoSeleccionado != null) {
                        funcionSeleccionada.reservarAsiento(asientoSeleccionado);
                        agregarEntrada(funcionSeleccionada, asientoSeleccionado);
                        System.out.println("Entrada agregada al carrito.");
                    } else {
                        System.out.println("No se pudo comprar la entrada. Intente nuevamente.");
                    }

                    System.out.println("¿Desea agregar más asientos para esta función? (1. Sí / 2. No)");
                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                        scanner.next(); // Limpiar entrada inválida
                        continue;
                    }

                    int respuestaAsientos = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                    agregarMasAsientos = (respuestaAsientos == 1);
                }

                System.out.println("¿Desea agregar otra función con otros asientos? (1. Sí / 2. No)");
                if (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                    scanner.next(); // Limpiar entrada inválida
                    continue;
                }

                int respuestaFunciones = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                agregarMasFunciones = (respuestaFunciones == 1);
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                scanner.nextLine();  // Limpiar buffer
            }
        }

        // Preguntar si desea proceder con el pago
        System.out.println("¿Desea proceder con el pago? (1. Sí / 2. No)");
        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, intente de nuevo.");
            scanner.next(); // Limpiar entrada inválida
            return;
        }

        int respuestaPago = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        if (respuestaPago == 1) {
            realizarPago(cliente);
        } else {
            System.out.println("Compra no completada. Las entradas siguen en su carrito.");
        }
    }

    public void realizarPago(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione método de pago:");
        System.out.println("1. Efectivo\n2. Débito\n3. Crédito");

        if (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, intente de nuevo.");
            scanner.next(); // Limpiar entrada inválida
            return;
        }

        int metodoPago = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
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
                if (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                    scanner.next(); // Limpiar entrada inválida
                    return;
                }
                int cuotas = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                estrategiaPago = new Credito(cuotas);
                break;
            default:
                System.out.println("Opción de pago inválida.");
                return;
        }

        double total = calcularTotal();
        double totalConDescuento = estrategiaPago.calcularMonto(total);

        System.out.println("Total a pagar: $" + totalConDescuento);
        System.out.println("Pago realizado con éxito. ¡Gracias por su compra!");

        vaciarCarrito();
    }
}