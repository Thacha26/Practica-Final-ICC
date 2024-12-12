/*FALTA*/
//Documentar un poquititito
//Al final de cada juego, informar al jugador su posicion respecto a los demas
//Al fianl de cada juego, si gana, con el metodo AGREGARPUNTOS() sumar 10 puntos y ya 
import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class menu{
    public static void main(String[] args){
	Scanner scanner = new Scanner(System.in);

	jugador[] players = new jugador[100];
	jugador jugadorActual = null;
	
	int decision;
	int juego;
	int dia;
	String nombre;
	boolean seguir = true; 
        String cadena = "**************************************************************************";
	
	System.out.println(cadena);
	System.out.println("Por favor, indica que dia quieres jugar");
	System.out.println(cadena);
	System.out.println("1.Dia uno");
	System.out.println("2.Dia dos");
	System.out.println(cadena);

	while(true){
	    try{
		dia = scanner.nextInt();
		break;
	    } catch(InputMismatchException e){
		System.out.println(cadena);
		System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
		System.out.println(cadena);
		scanner.nextLine();
	    }
	}

	while(dia != 1 && dia !=2){
	    System.out.println(cadena);
	    System.out.println("Por favor, esocoge una opcion valida");
	    System.out.println(cadena);

	    while(true){
		try{
		    dia = scanner.nextInt();
		    break;
		} catch(InputMismatchException e){
		    System.out.println(cadena);
		    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
		    System.out.println(cadena);
		    scanner.nextLine();
		}
	    }
  
	}

	while(seguir){
	    System.out.println(cadena);
	    System.out.println("                                  MENU");
	    System.out.println(cadena);   
	
	    System.out.println(cadena);
	    System.out.println("Bienvenido jugador, estas en el dia uno");
	    System.out.println(cadena);
	    System.out.println("1.Registro");

	    if(dia == 1){
		System.out.println("2.Iniciar un juego (Cuadrado Magico o Conecta 4)");
	    } else {
		System.out.println("2.Iniciar un juego (Salvado o Torres de Hanoi)");  
	    }
	
	    System.out.println("3.Ver los mejores 3 puntajes");
	    System.out.println("4.Ver tus puntos actuales");
	    System.out.println("5.Guardar y salir");
	    System.out.println(cadena);

	    while(true){
		try{
		    decision = scanner.nextInt();
		    break;
		} catch(InputMismatchException e){
		    System.out.println(cadena);
		    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
		    System.out.println(cadena);
		    scanner.nextLine();
		}
	    }

	    while(decision !=1 && decision !=2 && decision !=3 && decision !=4 && decision !=5){
		System.out.println(cadena);
		System.out.println("Por favor, escoge una opcion valida.");
		System.out.println(cadena);

		while(true){
		    try{
			decision = scanner.nextInt();
			break;
		    } catch(InputMismatchException e){
			System.out.println(cadena);
			System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
			System.out.println(cadena);
			scanner.nextLine();
		    }
		}
		
	    }

	
	    switch(decision){
		
		//Registro
	    case 1:
		
		System.out.println(cadena);
		System.out.println("Por favor, escribe solo tu nombre o apodo");
		System.out.println(cadena);

		scanner.nextLine();
		nombre = scanner.nextLine();

		//Aqui vamos a deserializar para leer el archivo y ver si ya habia jugadores registrados
		ObjectInputStream archivo1 = null;
		try{
		    archivo1 = new ObjectInputStream(new FileInputStream("jugadores.txt"));
		    jugador[] leido = (jugador[]) archivo1.readObject();
		    System.arraycopy(leido, 0, players, 0, leido.length);
		} catch (IOException | ClassNotFoundException e){
		    e.printStackTrace();
		} finally {
		    if (archivo1 != null) {
			try {
			    archivo1.close();
			} catch (IOException e) {
			    System.out.println("Error al cerrar el archivo: " + e.getMessage());
			}
		    }
		}

		boolean existeJug = false;
		boolean espacioDisp = false;

		for(int i = 0; i < players.length; i++){
		    if(players[i] != null && players[i].obtenerNombre().equalsIgnoreCase(nombre)){
			System.out.println("Ya estás registrado, " + nombre + ". Tus créditos actuales son: " + players[i].obtenerCreditos());
			
		        jugadorActual = players[i];
			
			existeJug = true;
			break;
		    }
		}
		if(!existeJug){
		    for(int i = 0; i < players.length; i++){
			if(players[i] == null){
			    players[i] = new jugador(nombre);
			    
			    jugadorActual = players[i];
			    
			    System.out.println(nombre + " felicidades, has sido registrado, tienes un total de 100 creditos");
			    espacioDisp = true;
			    break;
			}
		    }
		}

		ObjectOutputStream archivo2 = null; 
		try {
		    archivo2 = new ObjectOutputStream(new FileOutputStream("jugadores.txt"));
		    archivo2.writeObject(players);
		} catch (IOException e) {
		    System.out.println("Error al guardar los datos: " + e.getMessage());
		} finally {
		    if (archivo2 != null) {
			try {
			    archivo2.close();
			} catch (IOException e) {
			    System.out.println("Error al cerrar el archivo: " + e.getMessage());
			}
		    }
		}
		break;

		
		//Iniciar un juego
	    case 2:

		if(jugadorActual == null){
		    System.out.println("Por favor, registrate primero.");
		    break;
		}
		
		System.out.println(cadena);
		if(dia == 1){
		    System.out.println("Que juego deseeas jugar?");
		    System.out.println(cadena);
		    System.out.println("1.Cuadrado magico");
		    System.out.println("2.Conecta 4");

		    while(true){
			try{
			    juego = scanner.nextInt();
			    break;
			} catch(InputMismatchException e){
			    System.out.println(cadena);
			    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
			    System.out.println(cadena);
			    scanner.nextLine();
			}
		    }

		    while(juego != 1 && juego !=2){
			System.out.println(cadena);
			System.out.println("Por favor, escoge una opcion valida");
			System.out.println(cadena);

			while(true){
			    try{
				juego = scanner.nextInt();
				break;
			    } catch(InputMismatchException e){
				System.out.println(cadena);
				System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
				System.out.println(cadena);
				scanner.nextLine();
			    }
			}
			
		    }

		    if(!jugadorActual.suficientesCreditos()){
			System.out.println(cadena);
			System.out.println("Lo siento, no tienes creditos para jugar algun juego");
			System.out.println(cadena);
		    } else {
			jugadorActual.cobro();
			System.out.println(cadena);
			System.out.println("Disfruta tu juego :).");
			System.out.println(cadena);
		    }

		    //Juegos del dia uno
		    switch(juego){

			//Cuadrado Magico 
		    case 1:

			break;

			//Conecta 4
		    case 2:
			break;
		    }
		     
		} else {
		    System.out.println("Que juego deseeas jugar?");
		    System.out.println("1.Salvado");
		    System.out.println("2.Torres de Hanoi");

		    while(true){
			try{
			    juego = scanner.nextInt();
			    break;
			} catch(InputMismatchException e){
			    System.out.println(cadena);
			    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
			    System.out.println(cadena);
			    scanner.nextLine();
			}
		    }
		    
		    while(juego != 1 && juego !=2){
			System.out.println(cadena);
			System.out.println("Por favor, escoge una opcion valida");
			System.out.println(cadena);

			while(true){
			    try{
				juego = scanner.nextInt();
				break;
			    } catch(InputMismatchException e){
				System.out.println(cadena);
				System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
				System.out.println(cadena);
				scanner.nextLine();
			    }
			}
			
		    }

		    if(!jugadorActual.suficientesCreditos()){
			System.out.println("Lo siento, no tienes creditos para jugar algun juego");
		    } else {
			jugadorActual.cobro();
			System.out.println("Disfruta tu juego :).");
		    }

		    //Juegos del dia dos
		    switch(juego){
			
			//Salvados 
		    case 1:
			
			break;

			//Torres de hanoi
		    case 2:
			
			break;
		    }		    
		}
		
		break;

		//Mejores 3 puntajes
	    case 3:
		
		//Aqui va a checar que la posicion 3 sea diferente de nulo
		//Porque sino hay 3 jugadores, no hay mejores 3 puntajes 
		if(players[2] == null){
		    System.out.println(cadena);
		    System.out.println("No puedes ver los mejores 3 jugadores porque no hay jugadores suficientes.");
		    System.out.println(cadena);
		} else {
		    System.out.println(cadena);
		    jugador.mejores3(players);
		    System.out.println(cadena);
		}
		break;

		//Puntos actuales
	    case 4:
		
		if(jugadorActual == null){
		    System.out.println("Por favor, registrate primero.");
		} else {
		    System.out.println(cadena);
		    System.out.println("Tus puntos actuales son: " + jugadorActual.obtenerPuntos());
		    System.out.println(cadena);
		} 
		break;

		//Guardar y salir 
	    case 5:
		
		seguir = false;
		System.out.println(cadena);
		System.out.println("Gracias por jugar, vuelve pronto");
		System.out.println(cadena);
		break;
	    }
	}
    }
}
