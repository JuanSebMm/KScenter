import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar al usuario el número de filas y columnas
        System.out.print("Ingrese el número de filas: ");
        int filas = scanner.nextInt();
        System.out.print("Ingrese el número de columnas: ");
        int columnas = scanner.nextInt();

        // Declarar la matriz según los valores ingresados por el usuario
        String[][] matriz = new String[filas][columnas];

        // Llenar la matriz inicialmente con letras y números
        char letra = 'A';
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = letra + "            " + (j + 1); // Letra A, B, C, ... y número de 1 a columnas
            }
            letra++; // Avanzar a la siguiente letra para la siguiente fila
        }

        // Mostrar la matriz inicial en la consola
        System.out.println("Asientos disponibles:");
        System.out.println("================================");
        mostrarMatriz(matriz);
        System.out.println("================================");

        // Conjunto para almacenar las posiciones ya modificadas
        Set<Integer> posicionesModificadas = new HashSet<>();

        // Ciclo para repetir el proceso de selección
        boolean continuar = true;
        while (continuar) {
            // Solicitar la cantidad de veces que se repetirá el proceso
            System.out.print("\nIngrese la cantidad de veces que desea seleccionar un asiento: ");
            int veces = scanner.nextInt();

            // Ciclo para repetir el proceso la cantidad de veces indicada
            for (int k = 1; k <= veces; k++) {
                System.out.println("\nSelección de asiento " + k + ":");

                int filaSeleccionada, columnaSeleccionada;
                boolean esValido;

                do {
                    // Modificar un elemento de la matriz
                    System.out.print("Ingrese la fila que desea seleccionar (1-" + filas + "): ");
                    filaSeleccionada = scanner.nextInt() - 1; // Ajustar índice de base 0
                    System.out.print("Ingrese la columna que desea seleccionar (1-" + columnas + "): ");
                    columnaSeleccionada = scanner.nextInt() - 1; // Ajustar índice de base 0

                    // Verificar si la posición ingresada está dentro de los límites de la matriz
                    esValido = (filaSeleccionada >= 0 && filaSeleccionada < filas && columnaSeleccionada >= 0 && columnaSeleccionada < columnas);

                    // Verificar si la posición ya ha sido modificada
                    int posicion = filaSeleccionada * columnas + columnaSeleccionada;
                    if (posicionesModificadas.contains(posicion)) {
                        System.out.println("¡Esa posición ya ha sido seleccionada! Inténtelo nuevamente.");
                        esValido = false; // Forzar a repetir la entrada
                    }

                    if (!esValido) {
                        System.out.println("La posición ingresada está fuera de los límites de la matriz o es inválida. Inténtelo nuevamente.");
                    }
                } while (!esValido);

                // Agregar la posición seleccionada al conjunto de posiciones modificadas
                posicionesModificadas.add(filaSeleccionada * columnas + columnaSeleccionada);

                // Realizar la modificación en la matriz
                matriz[filaSeleccionada][columnaSeleccionada] = "no disponible";
                System.out.println("Se ha seleccionado el asiento (" + (filaSeleccionada + 1) + ", " + (columnaSeleccionada + 1) + ").");

                // Mostrar la matriz actualizada en la consola
                System.out.println("\nMatriz actualizada de asientos:");
                mostrarMatriz(matriz);
            }

            // Preguntar al usuario si desea eliminar alguna selección
            System.out.print("\n¿Desea eliminar alguna selección realizada? (S/N): ");
            scanner.nextLine(); // Consumir el salto de línea pendiente
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                eliminarSeleccion(matriz, posicionesModificadas);
                // Mostrar la matriz actualizada después de eliminar
                System.out.println("\nMatriz actualizada de asientos después de eliminar:");
                mostrarMatriz(matriz);
            }

            // Preguntar al usuario si desea continuar con más selecciones
            System.out.print("\n¿Desea realizar más selecciones? (S/N): ");
            respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("S")) {
                continuar = false;
            }
        }

        // Cerrar el scanner
        scanner.close();
    }

    // Método para mostrar la matriz en la consola
    public static void mostrarMatriz(String[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length; // Se asume que todas las filas tienen la misma cantidad de columnas

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Método para eliminar una selección realizada
    public static void eliminarSeleccion(String[][] matriz, Set<Integer> posicionesModificadas) {
        Scanner scanner = new Scanner(System.in);

        // Mostrar las posiciones seleccionadas
        System.out.println("\nPosiciones seleccionadas:");
        for (Integer posicion : posicionesModificadas) {
            int fila = posicion / matriz[0].length;
            int columna = posicion % matriz[0].length;
            System.out.println("(" + (fila + 1) + ", " + (columna + 1) + ")");
        }

        // Solicitar la posición que desea eliminar
        int filaEliminar, columnaEliminar;
        boolean esValido;

        do {
            // Modificar un elemento de la matriz
            System.out.print("Ingrese la fila que desea eliminar (1-" + matriz.length + "): ");
            filaEliminar = scanner.nextInt() - 1; // Ajustar índice de base 0
            System.out.print("Ingrese la columna que desea eliminar (1-" + matriz[0].length + "): ");
            columnaEliminar = scanner.nextInt() - 1; // Ajustar índice de base 0

            // Verificar si la posición ingresada está dentro de los límites de la matriz
            esValido = (filaEliminar >= 0 && filaEliminar < matriz.length && columnaEliminar >= 0 && columnaEliminar < matriz[0].length);

            // Verificar si la posición está en las selecciones realizadas
            int posicion = filaEliminar * matriz[0].length + columnaEliminar;
            if (!posicionesModificadas.contains(posicion)) {
                System.out.println("¡Esa posición no ha sido seleccionada! Inténtelo nuevamente.");
                esValido = false; // Forzar a repetir la entrada
            }

            if (!esValido) {
                System.out.println("La posición ingresada está fuera de los límites de la matriz o no ha sido seleccionada. Inténtelo nuevamente.");
            }
        } while (!esValido);

        // Eliminar la posición seleccionada
        matriz[filaEliminar][columnaEliminar] = (filaEliminar + 1) + "" + (columnaEliminar + 1);
        posicionesModificadas.remove(filaEliminar * matriz[0].length + columnaEliminar);

        // Cerrar el scanner
        scanner.close();
    }
}
