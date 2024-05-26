//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Definir las dimensiones del arreglo
        int filas = 5;
        int columnas = 5;
        String[][] matriz = new String[filas][columnas];

        // Llenar el arreglo dinámicamente
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = (char)('A' + j) + Integer.toString(i + 1);
            }
        }

        // Imprimir el arreglo en el formato solicitado
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(); // Nueva línea después de cada fila
        }
    }
}