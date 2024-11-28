import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase main que implementa todos los metodos de conecta4 para emular un juego de conecta 4
 *
 * @author Espinosa Romero Frida Thais
 * @author Carriche Arriaga Dante Raziel
 * @version 27 de noviembre de 2024
 */
public class main{
    public static void main(String[] args) {
	conecta4 juego = new conecta4();
	Scanner scanner = new Scanner(System.in);
	String separacion = "*************************************************************";
	int col;
	int decision;
	boolean seguir = true;
	char resultado = juego.ganador();

	System.out.println(separacion);
	System.out.println("Hola y bienvenido a este increible juego llamado conecta 4");
	System.out.println(separacion);

	while(seguir){
	    System.out.println("Elige el modo en el que quieres jugar: ");
	    System.out.println("1. Jugador contra Jugador");
	    System.out.println("2. Jugador contra computadora");
	    System.out.println("0. Salir del juego");
	    System.out.println(separacion);

	    while (true) {
		try {
		    decision = scanner.nextInt();
		    break; 
		} catch (InputMismatchException e) {
		    System.out.println(separacion);
		    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
		    System.out.println(separacion);
		    scanner.nextLine();
		}
	    }
	    
	    while (decision != 0 && decision != 1 && decision != 2) {
		System.out.println(separacion);
		System.out.println("Por favor, escoge un numero valido");
		decision = scanner.nextInt();
		System.out.println(separacion);
	    }
	    if(decision == 0) {
		System.out.println(separacion);
		System.out.println("Muchas gracias, vuelve pronto.");
		System.out.println(separacion);
		seguir = false;
	    }


	    while(seguir){ 
		switch(decision) {
		case 1:
		    //Caso para cuando es jugador contra jugador
		    System.out.println(separacion);
		    System.out.println("Has elegido Jugador contra Jugador.");
		    System.out.println("Por favor, cuando hagas un movimiento, solo coloca la columna.");

		    while (seguir) {
			System.out.println(juego.mostrarTablero());
			System.out.println("Jugador " + juego.jugador + ", introduce la columna:");

			while (true){
			    try {
				col = scanner.nextInt();
				break;
			    } catch (InputMismatchException e) {
				System.out.println(separacion);
				System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
				System.out.println(separacion);
				scanner.nextLine();
			    }
			}
			
			System.out.println(separacion);

			if (!juego.colocar(col)) {
			    System.out.println("Movimiento inválido, intenta de nuevo.");
			    System.out.println(separacion);
			    continue;
			}

			juego.cambiarColor();
			
			if (juego.finJuego()) {
			    resultado = juego.ganador();
			    System.out.println(juego.mostrarTablero());
			    if (resultado != ' ') {
				System.out.println("¡Enhorabuena, el jugador " + resultado + " ha ganado!");
			    } else {
				System.out.println("El tablero se ha llenado, es un empate.");
			    }
			    seguir = false;
			}
			break;

		    case 2:
			//Caso para cuando es jugador contra computadora 
			System.out.println(separacion);
			System.out.println("El modo de juego es jugador contra computadora.");
			System.out.println("Por favor, cuando hagas un movimiento, solo coloca la columna.");

			System.out.println(juego.mostrarTablero());
			if(juego.jugador == 'X'){
			    System.out.println("Turno de la computadora");
			    juego.versusComputadora();
			    System.out.println(separacion);
			} else {
			
			    while (true){
				try {
				    col = scanner.nextInt();
				    break;
				} catch (InputMismatchException e) {
				    System.out.println(separacion);
				    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
				    System.out.println(separacion);
				    scanner.nextLine();
				}
			    }
			
			    System.out.println("Jugador " + juego.jugador + ", introduce la columna");

			    if(!juego.colocar(col)) {
				System.out.println(separacion);
				System.out.println("Movimiento invalido, intenta de nuevo");
				continue;
			    }
			}

			if(juego.finJuego()) {
			    System.out.println(juego.mostrarTablero());
			    resultado = juego.ganador();
			    if(resultado != ' '){
				System.out.println("En hora buena, el jugador "+ juego.ganador()  +" a ganado");
				System.out.println(separacion);
			    } else {
				System.out.println("El tablero se ha llenado, es un empate");
			    }
			}
			seguir = false;
			break; 
		
		    default:
			System.out.println("Por favor escoge una opcion valida, ya sea el 1, 2 o 3.");

		    }		    
		}
	    }
	    

	    //Si la decicion es 0, termina el programa con un return
	    if(decision == 0) {
		return;
	    }
	    System.out.println(separacion);
	    System.out.println("Deseas seguir jugando?");
	    System.out.println("1. Sipirili");
	    System.out.println("2. Noporolo");
	    System.out.println(separacion);

	    while (true) {
		try {
		    decision = scanner.nextInt();
		    break; 
		} catch (InputMismatchException e) {
		    System.out.println(separacion);
		    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
		    System.out.println(separacion);
		    scanner.nextLine();
		}
	    }

	    System.out.println(separacion);

	    while(decision != 1 && decision != 2){
		System.out.println("Por favor, escoje 1 o 2");

		while (true){
		    try {
			decision = scanner.nextInt();
			break;
		    } catch (InputMismatchException e) {
			System.out.println(separacion);
			System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
			System.out.println(separacion);
			scanner.nextLine();
		    }
		}
		
	    }

	    if(decision == 2) {
		System.out.println(separacion);
		System.out.println("Muchas gracias, vuelve pronto.");
		System.out.println(separacion);
		seguir = false;
	    } else {
		seguir = true;
	    }
	    //Reinicia el juego para una nueva partida 
	    juego.reiniciarJuego();
	}
    }    
} 
