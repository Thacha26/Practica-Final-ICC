/**
 * Clase main del juego "Hanoi" EL jugador debe de mover los discos correctamente
 * @author Espinosa Romero Frida Thais
 * @author Carriche Arriaga Dante Raziel
 * 11 Diciembre 2024 @version 8
 */
import java.util.Scanner;

public class mainHanoi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String separador = "....................................................";

        System.out.println(separador);
        System.out.println("Bienvenido al juego de las Torres de Hanoi! Por favor, selecciona una opción");
        boolean seguirJugando = true;

        int numD = 6;  // Número de discos a jugar son los 6 requeridos
        Hanoi juego = null;  // Objeto del juego
        
        // Se muestra el menú al empezar el juego
        while (seguirJugando) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Jugar");
            System.out.println("2. Ver reglas");
            System.out.println("3. Ver solución");
            System.out.println("4. Salir");

            // Validación para la entrada del menú
            int opcion = 0;
            boolean opcionValida = false;
            while (!opcionValida) {
                try {
                    System.out.print("Selecciona una opción: ");
                    opcion = Integer.parseInt(sc.nextLine());
                    if (opcion < 1 || opcion > 4) {
                        System.out.println("Opción inválida, solo puedes ingresar números (1-4).");
                    } else { //Solo se puede seleccionar una opción dentro del rango
                        opcionValida = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("UY! Error! Solo se aceptan números intenta de nuez");
                }
            }

            switch (opcion) {
                
                case 1:
                    //Iniciar el juego
                    juego = new Hanoi(""); 
                    juego.jugar();  // Comienza el juego
                    juego = new Hanoi("");
                    
                    break;
                

                case 2:
                    // Se muestran las reglas
                    System.out.println(separador);
                    mostrarReglas();
                    break;

                case 3:
                    // Por si el usuario quiere ver la solución
                    if (juego != null) {
                        System.out.println(separador);
                        
                        // Muestra los movimientos mínimos 
                        int movimientosMinimos = juego.movimientosMin(numD, 1, 2, 3);  // Obtiene los movimientos mínimos
                        System.out.println("\nCantidad MÍNIMA de movimientos: " + movimientosMinimos);
                        
                        System.out.println("Solución para " + numD + " discos:");
                        juego.mostrarSolucion(numD, 1, 2, 3);  // Moestra la solución del juego
                    } else {
                        System.out.println("Primero debes iniciar un juego para ver la solución.");
                    }
                    break;

                case 4:
                    // Salir del juego
                    System.out.println("¡Gracias por jugar!, aaaaaaadiossssssss!!!!!!!!!!");
                    seguirJugando = false;

                    break;

                default:
                    System.out.println("Opción inválida, nananis, try again con una correcta o no va a jalar.");
            }
        }
    
    }

    /**
     * Muestra las reglas del juego al usuario que quiera verlas
     * 
     */
    public static void mostrarReglas() {
        System.out.println("\n--- Reglas del Juego ---");
        System.out.println("1. El objetivo del juego es mover todos los discos de un poste (torre) a otra.");
        System.out.println("2. Solo puedes mover un disco a la vez.");
        System.out.println("3. Un disco solo puede ser colocado sobre un disco de mayor tamaño o sobre un poste vacío.");
        System.out.println("4. El juego termina cuando todos los discos están en el poste de destino.");
    }
    
}
