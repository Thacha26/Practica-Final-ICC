/**
 * Clase del juego "Salvados", donde el jugador tiene que adivinar a quien se salvará
 * @author Espinosa Romero Frida Thais
 * @author Carriche Arriaga Dante Raziel
 * 27 noviembre 2024 @version 5
 */
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
        
        // Se muestra el menú al empezar el juego
        while (seguirJugando) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Jugar");
            System.out.println("2. Ver reglas");
            System.out.println("3. Salir");

            // Validación para la entrada del menú
            int opcion = 0;
            boolean opcionValida = false;
            while (!opcionValida) {
                try {
                    System.out.print("Selecciona una opción: ");
                    opcion = Integer.parseInt(sc.nextLine());
                    if (opcion < 1 || opcion > 3) {
                        System.out.println("Opción inválida, sólo puedes ingresar números (1-3).");
                    } else {
                        opcionValida = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("¡Error! Solo se aceptan númerosss. Intenta de nuevooo.");
                }
                
            }

            switch (opcion) {
                case 1:
                    
                    //Iniciar el juego
                    juego = new Salvados(""); 
                    juego.jugar();  // Comienza el juego

                    //Pregunta al usuariosi continuar o salir después de jugar
                    System.out.println("¿Quieres seguir jugando este maravilloso juego? (S/N)");
                    String respuesta = sc.nextLine();
                    if (respuesta.equalsIgnoreCase("N")) {
                        seguirJugando = false;
                        System.out.println("¡Gracias por jugar!, aaaaaaadiossssssss!!!!!!!!!!");
                    } else {
                        //Si el usuario quiere jugar otra vez, simplemente se le reinicia el juego
                        juego = new Salvados("");
                    }
                    break;
                

                case 2:
                    // Se muestran las reglas
                    System.out.println(separador);
                    mostrarReglas();
                    break;

                
                case 3:
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
        }
