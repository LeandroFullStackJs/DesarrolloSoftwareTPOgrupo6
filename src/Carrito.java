import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Carrito {
    private List<Entrada> entradas;
    private Pago estrategiaPago;

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

    public void cancelarCarrito() {
        for (Entrada entrada : entradas) {
            entrada.getAsiento().setDisponible(true);
            entrada.getFuncion().liberarAsiento(entrada.getAsiento());
        }
        entradas.clear();
        System.out.println("Carrito cancelado y asientos liberados.");
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

                int indiceFuncion = -1;
                while (indiceFuncion < 0 || indiceFuncion >= catalogo.getFunciones().size()) {
                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                        scanner.next(); // Limpiar entrada inválida
                        continue;
                    }
                    indiceFuncion = scanner.nextInt();
                    scanner.nextLine();  // Limpiar buffer
                }

                Funcion funcionSeleccionada = catalogo.getFunciones().get(indiceFuncion);

                boolean agregarMasAsientos = true;
                while (agregarMasAsientos) {
                    System.out.println("Seleccione un tipo de asiento:");
                    System.out.println("1. Platea\n2. Palco Alto\n3. Palco Bajo\n4. Cazuela\n5. Tertulia\n6. Paraiso");

                    int tipoAsiento = -1;
                    while (tipoAsiento < 1 || tipoAsiento > 6) {
                        if (!scanner.hasNextInt()) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número entre 1 y 6.");
                            scanner.next(); // Limpiar entrada inválida
                            continue;
                        }
                        tipoAsiento = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer
                    }

                    String tipoAsientoStr = "";
                    switch (tipoAsiento) {
                        case 1:
                            tipoAsientoStr = "Platea";
                            break;
                        case 2:
                            tipoAsientoStr = "PalcoAlto";
                            break;
                        case 3:
                            tipoAsientoStr = "PalcoBajo";
                            break;
                        case 4:
                            tipoAsientoStr = "Cazuela";
                            break;
                        case 5:
                            tipoAsientoStr = "Tertulia";
                            break;
                        case 6:
                            tipoAsientoStr = "Paraiso";
                            break;
                        default:
                            System.out.println("Opción inválida.");
                            return;
                    }

                    System.out.println("Seleccione un asiento disponible:");
                    List<Asiento> asientosDisponibles = funcionSeleccionada.getAsientosDisponibles(tipoAsientoStr);
                    if (asientosDisponibles.isEmpty()) {
                        System.out.println("No hay asientos disponibles para este tipo.");
                        continue;
                    } else {
                        System.out.println("Asientos disponibles:");
                        for (int i = 0; i < asientosDisponibles.size(); i++) {
                            System.out.println(i + ". " + asientosDisponibles.get(i));
                        }
                    }

                    int numeroAsiento = -1;
                    while (numeroAsiento < 0 || numeroAsiento >= asientosDisponibles.size()) {
                        if (!scanner.hasNextInt()) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
                            scanner.next(); // Limpiar entrada inválida
                            continue;
                        }
                        numeroAsiento = scanner.nextInt();
                        scanner.nextLine();  // Limpiar buffer
                    }

                    Asiento asientoSeleccionado = asientosDisponibles.get(numeroAsiento);

                    if (asientoSeleccionado != null) {
                        // Marcar el asiento como no disponible inmediatamente
                        funcionSeleccionada.reservarAsiento(asientoSeleccionado);

                        System.out.println("¿Desea aplicar un decorador? (1. Sí / 2. No)");
                        int aplicarDecorador = -1;
                        while (aplicarDecorador != 1 && aplicarDecorador != 2) {
                            if (!scanner.hasNextInt()) {
                                System.out.println("Entrada inválida. Por favor, ingrese 1 para Sí o 2 para No.");
                                scanner.next(); // Limpiar entrada inválida
                                continue;
                            }
                            aplicarDecorador = scanner.nextInt();
                            scanner.nextLine(); // Limpiar buffer
                        }

                        if (aplicarDecorador == 1) {
                            System.out.println("Seleccione un decorador:");
                            System.out.println("1. Oferta\n2. VIP");

                            int tipoDecorador = -1;
                            while (tipoDecorador < 1 || tipoDecorador > 2) {
                                if (!scanner.hasNextInt()) {
                                    System.out.println("Entrada inválida. Por favor, ingrese 1 para Oferta o 2 para VIP.");
                                    scanner.next(); // Limpiar entrada inválida
                                    continue;
                                }
                                tipoDecorador = scanner.nextInt();
                                scanner.nextLine(); // Limpiar buffer
                            }

                            switch (tipoDecorador) {
                                case 1:
                                    asientoSeleccionado = new AsientoDescuento(asientoSeleccionado);
                                    break;
                                case 2:
                                    asientoSeleccionado = new AsientoVIP(asientoSeleccionado);
                                    break;
                                default:
                                    System.out.println("Opción inválida.");
                                    return;
                            }
                        }

                        agregarEntrada(funcionSeleccionada, asientoSeleccionado);
                        System.out.println("Entrada agregada al carrito.");
                    } else {
                        System.out.println("No se pudo comprar la entrada. Intente nuevamente.");
                    }

                    System.out.println("¿Desea agregar más asientos para esta función? (1. Sí / 2. No)");
                    int respuestaAsientos = -1;
                    while (respuestaAsientos != 1 && respuestaAsientos != 2) {
                        if (!scanner.hasNextInt()) {
                            System.out.println("Entrada inválida. Por favor, ingrese 1 para Sí o 2 para No.");
                            scanner.next(); // Limpiar entrada inválida
                            continue;
                        }
                        respuestaAsientos = scanner.nextInt();
                        scanner.nextLine(); // Limpiar buffer
                    }
                    agregarMasAsientos = (respuestaAsientos == 1);
                }

                System.out.println("¿Desea agregar otra función con otros asientos? (1. Sí / 2. No)");
                int respuestaFunciones = -1;
                while (respuestaFunciones != 1 && respuestaFunciones != 2) {
                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida. Por favor, ingrese 1 para Sí o 2 para No.");
                        scanner.next(); // Limpiar entrada inválida
                        continue;
                    }
                    respuestaFunciones = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                }
                agregarMasFunciones = (respuestaFunciones == 1);
            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Entrada inválida. Por favor, intente de nuevo.");
                scanner.nextLine();  // Limpiar buffer
            }
        }

        // Preguntar si desea proceder con el pago
        System.out.println("¿Desea proceder con el pago? (1. Sí / 2. No)");
        int respuestaPago = -1;
        while (respuestaPago != 1 && respuestaPago != 2) {
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese 1 para Sí o 2 para No.");
                scanner.next(); // Limpiar entrada inválida
                continue;
            }
            respuestaPago = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
        }

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

        int metodoPago = -1;
        while (metodoPago < 1 || metodoPago > 3) {
            if (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entre 1 y 3.");
                scanner.next(); // Limpiar entrada inválida
                continue;
            }
            metodoPago = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
        }

        String medioDePago = "";
        switch (metodoPago) {
            case 1:
                estrategiaPago = new Efectivo();
                medioDePago = "Efectivo";
                break;
            case 2:
                estrategiaPago = new Debito();
                medioDePago = "Débito";
                break;
            case 3:
                System.out.println("Ingrese cantidad de cuotas (2, 3, 6): ");
                int cuotas = -1;
                while (cuotas != 2 && cuotas != 3 && cuotas != 6) {
                    if (!scanner.hasNextInt()) {
                        System.out.println("Entrada inválida. Por favor, ingrese 2, 3 o 6.");
                        scanner.next(); // Limpiar entrada inválida
                        continue;
                    }
                    cuotas = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer
                }
                estrategiaPago = new Credito(cuotas);
                medioDePago = "Crédito en " + cuotas + " cuotas";
                break;
            default:
                System.out.println("Opción de pago inválida.");
                return;
        }

        double total = calcularTotal();
        double totalConDescuento = estrategiaPago.calcularMonto(total);

        System.out.println("Total a pagar: $" + totalConDescuento);
        System.out.println("Pago realizado con éxito. ¡Gracias por su compra!");

        // Generar el ticket
        Ticket ticket = new Ticket(cliente, entradas, totalConDescuento, medioDePago);
        System.out.println("Ticket generado con éxito. Aquí están los detalles de su compra:");
        System.out.println(ticket);

        vaciarCarrito();
    }
}
