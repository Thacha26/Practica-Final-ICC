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
        String jugador = "";  // Variable que almacena el nombre
        Jugador[] clasificacion = new Jugador[0]; // Arreglo vacío, pero se irá incrementando el valor
        int numJugadores = 0; // Se inicializa con 0 el número de jugadores

        // Se muestra el menú al empezar el juego
        while (seguirJugando) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Jugar");
            System.out.println("2. Ver reglas");
            System.out.println("3. Ver solución");
            System.out.println("4. Consultar mi posición");
            System.out.println("5. Salir");

            // Validación para la entrada del menú
            int opcion = 0;
            boolean opcionValida = false;
            while (!opcionValida) {
                try {
                    System.out.print("Selecciona una opción: ");
                    opcion = Integer.parseInt(sc.nextLine());
                    if (opcion < 1 || opcion > 5) {
                        System.out.println("Opción inválida, solo puedes ingresar números (1-5).");
                    } else { //Solo se puede seleccionar una opción dentro del rango
                        opcionValida = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("UY! Error! Solo se aceptan números intenta de nuez");
                }
            }

            switch (opcion) {
                case 1:
                    // Si se selecciona 1 se empezará a jugar
                    if (juego == null) {
                        System.out.println(separador);
                        System.out.println("Introduce tu nombre: "); // Se pide el nombre la primera vez que se inicializa el juego
                        jugador = sc.nextLine();
                        juego = new Hanoi(jugador); // Aquí se inicializa el juego por primera vez
                    } else {
                        // Si ya se había inicializado el juego se pregunta si se quiere continuar con el mismo nombre o si quiere otro para ir haciendo el ranking
                        System.out.println(separador);
                        System.out.println("¿Quieres seguir con el mismo nombre (" + jugador + ") o introducir otro?");
                        System.out.print("1. Seguir con el mismo nombre\n2. Introducir un nuevo nombre\nSelecciona una opción: ");
                        int opcionDelName = 0;
                        boolean opcionDelNameValida = false;
                        while (!opcionDelNameValida) {
                            try {
                                opcionDelName = Integer.parseInt(sc.nextLine());
                                if (opcionDelName == 1 || opcionDelName == 2) {
                                    opcionDelNameValida = true;
                                } else {
                                    System.out.println("Recuerda, solo tienes que seleccionar 1 o 2");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("UY! Error! Solo se aceptan números vas de nuez");
                            }
                        }

                        if (opcionDelName == 2) {
                            // Si se selecciona cambiar el nombre
                            System.out.println("Introduce tu nuevo nombre: ");
                            jugador = sc.nextLine();
                            juego = new Hanoi(jugador); // Reinicia el juego con un nuevo nombre
                        }
                    }

                    juego.jugar();
                    
                    // Añadir el jugador a la clasificación
                    if (numJugadores >= clasificacion.length) {
                        // Se aumenta el tamaño del arreglo
                        clasificacion = expandirArreglo(clasificacion);
                    }
                    clasificacion[numJugadores] = new Jugador(jugador, juego.obtenerPuntos());
                    numJugadores++;
                    mostrarClasificacion(clasificacion, numJugadores);

                    // Preguntar si continuar o salir después de jugar
                    System.out.println("¿Quieres seguir jugando este maravilloso juego? (S/N)");
                    String respuesta = sc.nextLine();
                    if (respuesta.equalsIgnoreCase("N")) {
                        seguirJugando = false;
                    } else {
                        juego = new Hanoi(jugador); // Reinicia el juego para la siguiente ronda, manteniendo el nombre
                    }
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
                    // Consultar posición
                    if (juego != null) { // Si ya se inicializó el juego y se jugó una vez muestra la posición
                        juego.consultarPosicion();
                    } else {
                        System.out.println("Nel pastel. Primero debes iniciar un juego."); // Si no, se pide que se juegue al menos una vez
                    }
                    break;

                case 5:
                    // Salir del juego
                    System.out.println("¡Gracias por jugar!, aaaaaaadiossssssss!!!!!!!!!!");
                    seguirJugando = false;
                    break;

                default:
                    System.out.println("Opción inválida, nananis, try again con una correcta o no va a jalar.");
            }
        }
        
        sc.close();
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
    
    /**
     * Muestra la clasificación de los jugadores ordenada por los puntos obtenidos.
     * Los jugadores son ordenados de mayor a menor según sus puntos 
     * 
     * @param clasificacion con la información de los jugadores.
     * @param numJugadores Número total de jugadores
     */    
    public static void mostrarClasificacion(Jugador[] clasificacion, int numJugadores) {
        System.out.println("\n--- Clasificación ---");
        // Ordenar jugadores por puntos que han obtenido, el que esté en las primeras posiciones es el que tiene más  puntos
        for (int i = 0; i < numJugadores - 1; i++) {
            for (int j = i + 1; j < numJugadores; j++) {
                if (clasificacion[i].getPuntos() < clasificacion[j].getPuntos()) {
                    Jugador temp = clasificacion[i];
                    clasificacion[i] = clasificacion[j];
                    clasificacion[j] = temp;
                }
            }
        }

        //Se muestra la clasificación
        for (int i = 0; i < numJugadores; i++) {
            Jugador jugador = clasificacion[i];
            System.out.println((i + 1) + ". " + jugador.getNombre() + " - " + jugador.getPuntos() + " puntos");
        }
    }

    /**
    *  Método para expandir el arreglo cuando esté lleno
    *
    * @param clasificacion Arreglo de objetos
    * @return arreglo de jugadores 
    *
    **/
    public static Jugador[] expandirArreglo(Jugador[] clasificacion) {
        // Crear un nuevo arreglo de tamaño mayor (duplicado)
        Jugador[] nuevoArreglo = new Jugador[clasificacion.length == 0 ? 1 : clasificacion.length * 2]; // Empieza con tamaño 1 si está vacío
        System.arraycopy(clasificacion, 0, nuevoArreglo, 0, clasificacion.length);
        return nuevoArreglo;
    }
}

/**
 *  Representa a un jugador en el juego
 * Contiene el nombre y los puntos del jugador
 */
class Jugador {
    private String nombre;
    private int puntos;

    /**
     * Constructor de la clase
     * 
     * @param nombre El nombre del jugador
     * @param puntos Los puntos del jugador
     */

    public Jugador(String nombre, int puntos) {
        this.nombre = nombre;
        this.puntos = puntos;
    }

    /**
     * Obtiene el nombre del jugador
     * 
     * @return El nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los puntos del jugador
     * 
     * @return Los puntos del jugador
     */
    public int getPuntos() {
        return puntos;
    }
}
