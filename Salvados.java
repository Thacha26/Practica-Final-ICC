/**
 * Clase del juego "Salvados", donde el jugador tiene que adivinar a quien se salvará
 * @author Espinosa Romero Frida Thais
 * @author Carriche Arriaga Dante Raziel
 * 27 noviembre 2024 @version 5
 */
import java.util.Scanner;

public class Salvados {
    protected String nombredelJugador;
    protected int puntos;
    String separador = "..............................................";

    /**
     * Constructor para inicializar nombre y puntos, puntos se inicializa en 0
     *
     * @param nombredelJugador Nombre del jugador
     * @param this.puntos = 0 
     */
    public Salvados(String nombredelJugador) {
        this.nombredelJugador = nombredelJugador;
        this.puntos = 0;
    }

    // Se eliminan a las personas
    public void jugar() {
        Scanner scanner = new Scanner(System.in);

        // El arreglo contendrá el número de personas que siempre estarán en las sillas, o sea 100 personas
        int[] personas = new int[100];
        for (int p = 0; p < 100; p++) {
            personas[p] = p + 1;  // Se asignan los números del 1 al 100
        }


        /**Aquí no sé si sea problema mío o del random, pero noté un pattrón, 
         * pues cada cierto tiempo se repiten los números, en este caso los que 
         * siempre fueron más constantes eran el 3 y el 100
         **/
        // Obtener el número aleatorio de quien será salvado
        int i = (int) (Math.random() * 100) + 1;  // Aleatorio de entre el 1 y 100

        System.out.println(separador);
        System.out.println("Hola! y welcome a este juego!");
        System.out.print("Tienes que adivinar el número de la silla que salvará a la persona que quieres que sobreviva!");
        System.out.println("el número tiene que estar entre 1 y 100): ");
        System.out.println("\n" + separador);
        System.out.println("Por favor, introduce un número");

        int adivina = scanner.nextInt();
        System.out.println("\n" + separador);

        // Se valida que el número que ingresa el usuario sí esté entre el 1 y el 100
        if (adivina < 1 || adivina > 100) {
            System.out.println("Ingresa un número de silla válido entre 1 y 100.");
            return;
        }

        // Se inicializan las variables en 0, y se detiene cuando se eliminen a 99
        int numerosEliminados = 0;
        int posicionActual = 0;

        // Se juega hasta que quede solo una persona
        while (numerosEliminados < 99) { 
            // Buscar la siguiente persona para eliminar
            int eliminarSiguiente = (posicionActual + i - 1) % 100; // Se obtiene la posición con el operador módulo

            // Si ya se había eliminado a una persona, se avanza al siguiente
            while (personas[eliminarSiguiente] == 0) {
                eliminarSiguiente = (eliminarSiguiente + 1) % 100;
            }

            personas[eliminarSiguiente] = 0; // Se marca como eliminada
            numerosEliminados++;
            posicionActual = (eliminarSiguiente + 1) % 100;
        }

        // Se identifica cual es la persona que sobrevive
        int ultimaSilla = -1;
        for (int s = 0; s < 100; s++) {
            if (personas[s] != 0) {
                ultimaSilla = personas[s];
                break;
            }
        }

        System.out.println(separador);
        if (adivina == ultimaSilla) {
            System.out.println("¡SIIIIIIII. Correcto! La persona que se sobrevive es la de la silla número: " + ultimaSilla);
            puntos = 12;  // Si adivina se le dan 12 pts
        } else {
            System.out.println("¡NOOOOOOOOOOO. Incorrecto! La persona que se sobrevive es la de la silla número: " + ultimaSilla);
            puntos = 2; // Sino adivina se le dan 2 pts
        }

        // Se muestran los puntos y su posición
        consultarPosicion();
    }

        /**
         * Muestra la posición del jugador
         */
        public void consultarPosicion() {
            System.out.println("El jugador " + nombredelJugador + " tiene " + puntos + " puntotes.");
            System.out.println(separador);
        }

        /**
         * Obtiene los puntos del jugador
         * 
         * @return Los puntos
         */
        public int obtenerPuntos() {
            return puntos;
        }

        /**
         * Obtiene el nombre del jugador
         * 
         * @return El nombre
         */
        public String obtenernombredelJugador() {
            return nombredelJugador;
        }
}
