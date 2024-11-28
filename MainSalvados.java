import java.util.Scanner;
public class MainSalvados {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String separador = "....................................................";

        System.out.println(separador);
        System.out.println("Bienvenido al juego de SALVADOS! Por favor, selecciona una opción");
        boolean seguirJugando = true;

        String jugador = "";  // Variable que almacena el nombre
        Salvados juego = null; // Se inicializa el juego
        Jugador[] clasificacion = new Jugador[0]; // Arreglo vacío, pero se irá incrementando el valor
        int numJugadores = 0; // Se inicializa con 0 el número de jugadores

        // Se muestra el menú al empezar el juego
        while (seguirJugando) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Jugar");
            System.out.println("2. Ver reglas");
            System.out.println("3. Consultar mi posición");
            System.out.println("4. Salir");

            // Validación para la entrada del menú
            int opcion = 0;
            boolean opcionValida = false;
            while (!opcionValida) {
                try {
                    System.out.print("Selecciona una opción: ");
                    opcion = Integer.parseInt(sc.nextLine());
                    if (opcion < 1 || opcion > 4) {
                        System.out.println("Opción inválida, sólo puedes ingresar números (1-4).");
                    } else {
                        opcionValida = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("¡Error! Solo se aceptan númerosss. Intenta de nuevooo.");
                }
                
            }

            switch (opcion) {
                case 1:
                    // Si se selecciona 1 se empezará a jugar
                    if (juego == null) {
                        System.out.println(separador);
                        System.out.println("Introduce tu nombre: "); // Se pide el nombre la primera vez que se inicializa el juego
                        jugador = sc.nextLine();
                        juego = new Salvados(jugador);
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
                               System.out.println("Recuerda, sólo tienes que seleccionar 1 o 2");
                           }
                       } catch (NumberFormatException e) {
                           System.out.println("¡Error! Soolo se aceptan númerosss, vuele a escribir tu opción");
                       }
                   } 
                        if (opcionDelName == 2) {
                            // Si se selecciona cambiar el nombre
                            System.out.println("Introduce tu nuevo nombre: ");
                            jugador = sc.nextLine();
                            juego = new Salvados(jugador);
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
                    }
                    break;

                case 2:
                    // Se muestran las reglas
                    System.out.println(separador);
                    mostrarReglas();
                    break;

                case 3:
                    // Consultar posición
                    if (juego != null) { // Si ya se inicializó el juego y se jugó una vez muestra la posición
                        juego.consultarPosicion();
                    } else {
                        System.out.println("Nel pastel. Primero debes iniciar un juego."); // Si no se pide que se juegue al menos una vez
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
        
        sc.close();
    }

            // Método para imprimir las reglas para el jugador
            public static void mostrarReglas() {
                System.out.println("\n--- Reglas del Juego ---");
                System.out.println("1. Se colocarán 100 personas en un círculo, numeradas del 1 al 100.");
                System.out.println("2. Tienes que elegir un número del 1 al 100, la forma en la que se recorrerán a las personas para salvar al número que seleccionaste será siguiendo el mismo orden que las manecillas del reloj.");
                System.out.println("Si le atinas al mismo número que será elegido al azar para salvar a una persona, ganarás 12 PUNTOTES, si no, solo ganarás 2.");
            }

            // Método para mostrar la clasificación
            public static void mostrarClasificacion(Jugador[] clasificacion, int numJugadores) {
                System.out.println("\n--- Clasificación ---");
                // Ordenar jugadores por puntos que han obtenido, el que esté en las primeras posiciones es el que
                for (int i = 0; i < numJugadores - 1; i++) {
                    for (int j = i + 1; j < numJugadores; j++) {
                        if (clasificacion[i].getPuntos() < clasificacion[j].getPuntos()) {
                            Jugador temp = clasificacion[i];
                            clasificacion[i] = clasificacion[j];
                            clasificacion[j] = temp;
                        }
                    }
                }

                for (int i = 0; i < numJugadores; i++) {
                    Jugador jugador = clasificacion[i];
                    System.out.println((i + 1) + ". " + jugador.getNombre() + " - " + jugador.getPuntos() + " puntos");
                }
            }

            // Método para expandir el arreglo cuando esté lleno
            public static Jugador[] expandirArreglo(Jugador[] clasificacion) {
                // Crear un nuevo arreglo de tamaño mayor (duplicado)
                Jugador[] nuevoArreglo = new Jugador[clasificacion.length == 0 ? 1 : clasificacion.length * 2]; // Empieza con tamaño 1 si está vacío
                System.arraycopy(clasificacion, 0, nuevoArreglo, 0, clasificacion.length);
                return nuevoArreglo;
            }
}
