import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase mainCuadradoMagico que implementa todos los metodos de cuadradoMagico para emular el juego de un cuadrado Magico
 *
 * @author Espinosa Romero Frida Thais
 * @author Carriche Arriaga Dante Raziel
 * @version 8 de diciembre de 2024 
 */
public class mainCuadradoMagico {
    public static void main(String[] args) {
	cuadradoMagico juego = new cuadradoMagico();
	Scanner scanner = new Scanner(System.in);
	String separacion = "**************************************************************************";
	int col;
	int fil;
	int num;
	int decision;
	boolean seguir = true;

	System.out.println(separacion);
	System.out.println("Hola y bienvenido a este increible juego llamado cuadrado magico");
	System.out.println(separacion);
	System.out.println("El objetivo del juego es que coloques numeros del 1 al 16 y que todas las");
	System.out.println("filas, columnas y diagonales sumen lo mismo, sin embargo alguna de ellas");
	System.out.println("comenzara resuelta.");
	System.out.println("Solo hay dos reglas:");
	System.out.println("  + Una vez colocado un numero, no puedes cambiarlo.");
	System.out.println("  + Cuando el sistema detecte que no puedes ganar, se acabara el juego");
	System.out.println(separacion);

	while(seguir){
	    System.out.println("Deseas jugar?:");
	    System.out.println("1. Sipi");
	    System.out.println("2. Nopo");
	    System.out.println(separacion);

	    while(true){
		try{
		    decision = scanner.nextInt();
		    break;
		} catch(InputMismatchException e){
		    System.out.println(separacion);
		    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
		    System.out.println(separacion);
		    scanner.nextLine();
		}
	    }

	    while(decision != 1 && decision != 2){
		System.out.println(separacion);
		System.out.println("Por favor escoje una opcion valida");
		System.out.println(separacion);

		while(true){
		    try {
			decision = scanner.nextInt();
			break;
		    } catch(InputMismatchException e){
			System.out.println(separacion);
			System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
			System.out.println(separacion);
			scanner.nextLine();
		    }
		}
		
	    }

	    while(seguir){
		switch(decision){
		    //Caso donde se decide salir
		case 2:
		    seguir = false;
		    break;

		    //Caso donde quiere seguir
		case 1:
		    System.out.println(separacion);
		    System.out.println("Por favor, ingresa primero la columna, luego la fila  y al final el numero");
		    System.out.println(separacion);
		    juego.colocarAleatorio();

		    while(!juego.finJuego()) {
			System.out.println(juego.mostrarTablero());

			System.out.print("Columna: ");
			
			while(true){
			    
			    while(true){
				try {
				    col = scanner.nextInt();
				    break;
				} catch(InputMismatchException e){
				    System.out.println(separacion);
				    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
				    System.out.println(separacion);
				    scanner.nextLine();
				}
				
			    }
			    if(col >= 0 && col <= 3){
				break;
			    } else {
				System.out.println(separacion);
				System.out.println("Por favor, coloca una columna valida.");
				System.out.println(separacion);
				scanner.nextLine();
			    }
			}

			System.out.print("Fila: ");
			
			while(true){
			    
			    while(true){
				try{
				    fil = scanner.nextInt();
				    break;
				} catch(InputMismatchException e){
				    System.out.println(separacion);
				    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
				    System.out.println(separacion);
				    scanner.nextLine();
				}
			    }
			    
			    if(fil >= 0 && fil <= 3){
				break;
			    } else {
				System.out.println(separacion);
				System.out.println("Por favor, coloca una fila valida.");
				System.out.println(separacion);
				scanner.nextLine();
			    }
			}

			System.out.print("Numero: ");

			while(true){

			    while(true){
				try{
				    num = scanner.nextInt();
				    break;
				} catch(InputMismatchException e){
				    System.out.println(separacion);
				    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
				    System.out.println(separacion);
				    scanner.nextLine();
				}
			    }
		      
			    if(num >= 1 && num <= 16){
				break;
			    } else {
				System.out.println(separacion);
				System.out.println("Por favor, coloca un numero valido.");
				System.out.println(separacion);
				scanner.nextLine();
			    }
			}
			
			if(!juego.colocar(fil, col, num)){
			    System.out.println(separacion);
			    System.out.println("Movimiento invalido, intenta de nuevo.");
			    System.out.println(separacion);
			} else {
			    System.out.println(separacion);
			    System.out.println("Colocaste el numero " + num + " en la posicion " + col +", " + fil + ".");
			    System.out.println(separacion);
			}
		      		
		    }
		    if(juego.tableroLLeno()){
			System.out.println(separacion);
			System.out.println(juego.mostrarTablero());
			System.out.println(separacion);
			System.out.println("Felicidades, has ganado");
			System.out.println(separacion);
		    } else {
			System.out.println(separacion);
			System.out.println(juego.mostrarTablero());
			System.out.println(separacion);
			System.out.println("Con los numeros que colocaste, ya no es posible ganar.");
			System.out.println(separacion);
		    }
		    
		    System.out.println(separacion);
		    System.out.println("Quieres volver a jugar?");
		    System.out.println("1. Sipirili");
		    System.out.println("2. Noporolo");
		    
		    while(true){
			try{
			    decision = scanner.nextInt();
			    break;
			} catch(InputMismatchException e){
			    System.out.println(separacion);
			    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
			    System.out.println(separacion);
			    scanner.nextLine();
			}
		    }

		    if(decision == 2){
			seguir = false;
		    }
		    juego.limpiar();
		}
	    }
	}
        System.out.println(separacion);
	System.out.println("Muchas gracias, vuelve pronto.");
	System.out.println(separacion);
    }
}