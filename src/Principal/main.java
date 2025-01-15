package Principal;

import Calculo.calculoConversion;
import Modelo.Moneda;
import java.util.Scanner;

public class main {
    static Scanner consola = new Scanner(System.in);
    static calculoConversion conversor = new calculoConversion();

    public void menuOpciones() {
        int opcion = 0;
        while (opcion != 10) {
            System.out.println("********************************************");
            System.out.println("Sea bienvenido/a al Conversor de Moneda =]");
            System.out.println("1) Dólar => Peso Argentino");
            System.out.println("2) Peso Argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Sol peruano => Mexicano");
            System.out.println("8) Peso chileno => Sol peruano");
            System.out.println("9) Dram armenio => Sol peruano");
            System.out.println("10) Salir");
            System.out.print("Ingrese su opción: ");
            
            try {
                opcion = consola.nextInt();
                if (opcion >= 1 && opcion <= 9) {
                    System.out.print("Ingrese el valor que desea convertir: ");
                    double cantidad = consola.nextDouble();
                    realizarConversion(opcion, cantidad);
                } else if (opcion == 10) {
                    System.out.println("Saliendo del programa. ¡Gracias por usar el conversor!");
                } else {
                    System.out.println("Por favor, elija una opción válida.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, intente nuevamente.");
                consola.nextLine(); // Limpiar el buffer
            }
        }
    }

    private void realizarConversion(int opcion, double cantidad) {
        String monedaOrigen = "", monedaDestino = "";
        switch (opcion) {
            case 1 -> {
                monedaOrigen = "USD";
                monedaDestino = "ARS";
            }
            case 2 -> {
                monedaOrigen = "ARS";
                monedaDestino = "USD";
            }
            case 3 -> {
                monedaOrigen = "USD";
                monedaDestino = "BRL";
            }
            case 4 -> {
                monedaOrigen = "BRL";
                monedaDestino = "USD";
            }
            case 5 -> {
                monedaOrigen = "USD";
                monedaDestino = "COP";
            }
            case 6 -> {
                monedaOrigen = "COP";
                monedaDestino = "USD";
            }
            case 7 -> {
                monedaOrigen = "PEN";
                monedaDestino = "MXN";
            }
            case 8 -> {
                monedaOrigen = "CLP";
                monedaDestino = "PEN";
            }
            case 9 -> {
                monedaOrigen = "AMD";
                monedaDestino = "PEN";
            }
        }

        try {
            Moneda conversion = conversor.buscarConversion(cantidad, monedaOrigen, monedaDestino);
            if (conversion != null) {
                System.out.printf("Resultado: %.2f %s => %.2f %s%n", 
                                  cantidad, monedaOrigen, 
                                  conversion.cantidad(), monedaDestino);
            } else {
                System.out.println("No se pudo realizar la conversión.");
            }
        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        main programa = new main();
        programa.menuOpciones();
    }
}
