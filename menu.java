/*Falta*/
// * Que regrese bien los puntos en todos los juegos 
//Se importan las librerías necesarias
import java.io.*; //para la serialización deobjetos
import java.util.Scanner;
import java.util.InputMismatchException; //para los errores
public class menu{
    
    
    //Los siguientes métodos ayudarán a regresar correctamente los puntos del usuario
    public static int evaluarResultadoSalvados(int adivina, int ultimaSilla) {
        //verificar si el movimiento es válido según las reglas del cuadrado mágico
        if (adivina == ultimaSilla) {
            //movimiento válido
            return 1; //movimiento correcto, ganaste
        } else {
            //movimiento inválido
            return 2; //movimiento incorrecto, perdiste
        }
    }

    public static int evaluarResultadoCuadrado(cuadradoMagico juego) {
        //verificar si el movimiento es válido según las reglas del cuadrado mágico.
        if (juego.tableroLLeno()) {
            return 1;  
        }
	return 2;  
    } 
	
    // Método para evaluar el resultado
    public static int evaluarResultadoConecta4(conecta4 jugadorActual) {
        // Si el resultado es 'O', entonces el jugador ha ganado
        if (jugadorActual.ganador() == 'O') {
            return 1; //El jugador 'O' ha ganado, asigna 15 puntos
        }
	if (jugadorActual.ganador() == 'X') {
	    return 2;
	}
	return 3;
    }
    
    public static int evaluarResultadoHanoi(int movimientos, int resultado, int movimientosMin) {
        
        if (movimientos == movimientosMin) {
            return 1;
        } else if (resultado <= movimientosMin + 10) {
            return 2;
        } else {
            return 3;
        }
    }

    public static void main(String[] args){
        
        Scanner scanner = new Scanner(System.in);
        jugador[] players = new jugador[100];
        jugador jugadorActual = null;

        int decision;
        int juego;
        int dia = -1; //permite cambiar de día
        int posicion = -1; //
        String nombre;
        boolean seguir = true;
        String cadena = "**************************************************************************";

        

        System.out.println(cadena);
        System.out.println("Por favor, indica qué día quieres jugar");
        System.out.println(cadena);
        System.out.println("1. Día uno");
        System.out.println("2. Día dos");
        System.out.println("3. Quiero salir");
        System.out.println(cadena);

        
        //Se selecciona el día
        while (dia != 1 && dia != 2 && dia != 3) {
            try {
                System.out.println(cadena);
                System.out.println("Por favor, escoge una opción válida");
                System.out.println(cadena);

                dia = scanner.nextInt();
                if (dia == 3) { //Si selecciona la opción 3 sale del proframa
                    System.out.println("Ni modo, te pierdes de mucho, habían buenos juegos, ¡adiós!");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println(cadena);
                System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
                System.out.println(cadena);
                scanner.nextLine();
            }
        }

        while (seguir) {
            System.out.println(cadena);
            System.out.println("                                MENÚ ");
            System.out.println(cadena);

            System.out.println(cadena);
            System.out.println("Bienvenido jugador, estás en el día " + (dia == 1 ? "uno" : "dos"));
            System.out.println(cadena);
            System.out.println("1. Registro");

            if (dia == 1) {
                System.out.println("2. Iniciar un juego (Cuadrado Mágico o Conecta 4)");
            } else {
                System.out.println("2. Iniciar un juego (Salvado o Torres de Hanoi)");
            }

            System.out.println("3. Ver los mejores 3 puntajes");
            System.out.println("4. Ver tus puntos actuales");
            System.out.println("5. Guardar y salir");
            System.out.println("6. Quiero cambiar de día");
            System.out.println(cadena);

            while (true) {
                try {
                    decision = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(cadena);
                    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
                    System.out.println(cadena);
                    scanner.nextLine(); 
                }
            }

            //Validar la opción
            while (decision != 1 && decision != 2 && decision != 3 && decision != 4 && decision != 5 && decision != 6) {
                System.out.println(cadena);
                System.out.println("Por favor, escoge una opción válida.");
                System.out.println(cadena);

                while (true) {
                    try {
                        decision = scanner.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println(cadena);
                        System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
                        System.out.println(cadena);
                        scanner.nextLine(); 
                    }
                }
            }

            switch (decision) {
                //Registro
	    case 1:
		System.out.println(cadena);
		System.out.println("Por favor, escribe solo tu nombre o apodo");
		System.out.println(cadena);

		scanner.nextLine(); 
		nombre = scanner.nextLine();

		//Aqui vamos a deserializar para leer el archivo y ver si ya habia jugadores registrados
		ObjectInputStream archivo1 = null;
		try {
		    archivo1 = new ObjectInputStream(new FileInputStream("jugadores.txt"));
		    jugador[] leido = (jugador[]) archivo1.readObject();
		    System.arraycopy(leido, 0, players, 0, leido.length);
		} catch (IOException | ClassNotFoundException e) {
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

		//Agranda/guarda el arreflo de los jugadores
		for (int i = 0; i < players.length; i++) {
		    if (players[i] != null && players[i].obtenerNombre().equalsIgnoreCase(nombre)) { //Verifica si un jugador ya está registrado o no, comparando con la posición
			System.out.println("Ya estás registrado, " + nombre + ". Tus créditos actuales son: " + players[i].obtenerCreditos());
			jugadorActual = players[i];
			existeJug = true;
			break;
		    }
		}
		//Si el jugador no está registrado busca una posición vacía en el arreglo y registrarlo
		if (!existeJug) { 
		    for (int i = 0; i < players.length; i++) {
			if (players[i] == null) {
			    players[i] = new jugador(nombre);
			    jugadorActual = players[i];
			    System.out.println(nombre + " felicidades, has sido registrado, tienes un total de 100 créditos");
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
		if (jugadorActual == null) {
		    System.out.println("Por favor, regístrate primero.");
		    break;
		}

		System.out.println(cadena);
		if (dia == 1) { //Muestra los juegos que están en el día 1
		    System.out.println("¿Qué juego deseas jugar?");
		    System.out.println(cadena);
		    System.out.println("1. Cuadrado Mágico");
		    System.out.println("2. Conecta 4");

		    while (true) {
			try {
			    juego = scanner.nextInt();
			    break;
			} catch (InputMismatchException e) {
			    System.out.println(cadena);
			    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
			    System.out.println(cadena);
			    scanner.nextLine();
			}
		    }

		    //Si la opción que ingresa el jugador no está dentro de las opcciones lanza un error
		    while (juego != 1 && juego != 2) {
			System.out.println(cadena);
			System.out.println("Por favor, escoge una opción válida");
			System.out.println(cadena);

			while (true) {
			    try {
				juego = scanner.nextInt();
				break;
			    } catch (InputMismatchException e) {
				System.out.println(cadena);
				System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
				System.out.println(cadena);
				scanner.nextLine();
			    }
			}
		    }

		    //Verifica si el jugador tiene los créditos suficientes o no
		    if (!jugadorActual.suficientesCreditos()) {
			System.out.println(cadena);
			System.out.println("Lo siento, no tienes créditos para jugar algún juego");
			System.out.println(cadena);
		    } else {
			System.out.println(cadena);
			jugadorActual.cobro();
			System.out.println("Se te ha hecho el cobro de 15 créditos");
			System.out.println("\n Disfruta tu juego :)");
			System.out.println(cadena);

			//Juegos del dia uno
			switch (juego) {
			case 1:  //Cuadrado Mágico
			    cuadradoMagico juegocuadradoMagico = new cuadradoMagico();
			    mainCuadradoMagico.main(new String[]{}); //Se llama al main de Cuadrado para que se muestre todo el menú creado
                   
			    //Se escriben las variables para que permita devolver corrextamente las variables
			    int resultado = evaluarResultadoCuadrado(juegocuadradoMagico); //Se mandda a llamar el método para poder usar el siguiente if y asignar los puntos
        
			    //Asignar puntos basados en el resultado
			    if (resultado == 1) { //Da 10pts si el jugador ganó
				jugadorActual.agregarPuntos(10);
				System.out.println("Has ganado: 10 puntos");
			    } else {
				jugadorActual.agregarPuntos(2); //si el jugador no ganó nada más devuelve 2
				System.out.println("Has ganado: 2 puntos");
			    } 
                    
			    archivo2 = null;
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

			    //muestra la posición, puntos y créditos
			    System.out.println("Tus puntos actuales son: " + jugadorActual.obtenerPuntos());
			    System.out.println("Ya solo tienes " + jugadorActual.obtenerCreditos() + " creditos");
			    //Aqui va a checar que la posicion 2 sea diferente de nulo
			    //Porque si solo hay un jugador, siempre sera el primero 
			    if (players[1] == null) {
				System.out.println(cadena);
				System.out.println("Estas en la posicion 1 con " + jugadorActual.obtenerPuntos() + " puntos");
				System.out.println(cadena);
			    } else {
				System.out.println(cadena);
				System.out.println("Posiciones");
				System.out.println(cadena);
				jugador.posiciones(players);
				System.out.println(cadena);
			    }
								
			    break;

			    //Conecta 4
			case 2:  
			    conecta4 juegoConecta4 = new conecta4();
			    main.main(new String[]{}); //Se llama al main de conecta4 para que se muestre todo el menú creado
			
			    int resultadow = evaluarResultadoConecta4(juegoConecta4);

			    if (resultadow == 1){
				jugadorActual.agregarPuntos(15);
				System.out.println("Has ganado: 15 puntos");
			    } else if (resultadow == 2){
				jugadorActual.agregarPuntos(5);
				System.out.println("Has ganado: 5 puntos");
			    } else {
				jugadorActual.agregarPuntos(2);
				System.out.println("Has ganado: 2 puntos");
			    }
                      
			    System.out.println("Tus puntos actuales son: " + jugadorActual.obtenerPuntos());
			    System.out.println("Ya solo tienes " + jugadorActual.obtenerCreditos() + " creditos");
			    //Aqui va a checar que la posicion 2 sea diferente de nulo
			    //Porque si solo hay un jugador, siempre sera el primero 
			    if (players[1] == null) {
				System.out.println(cadena);
				System.out.println("Estas en la posicion 1 con " + jugadorActual.obtenerPuntos() + " puntos");
				System.out.println(cadena);
			    } else {
				System.out.println(cadena);
				System.out.println("Posiciones");
				System.out.println(cadena);
				jugador.posiciones(players);
				System.out.println(cadena);
			    }				
						
			    archivo2 = null;
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
                         
			}
			
		    }
                        
		} else {
		    System.out.println("Qué juego deseas jugar?");
		    System.out.println("1. Salvado");
		    System.out.println("2. Torres de Hanoi");

		    while (true) {
			try {
			    juego = scanner.nextInt();
			    break;
			} catch (InputMismatchException e) {
			    System.out.println(cadena);
			    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
			    System.out.println(cadena);
			    scanner.nextLine(); 
			}
		    }

		    while (juego != 1 && juego != 2) {
			System.out.println(cadena);
			System.out.println("Por favor, escoge una opción válida");
			System.out.println(cadena);

			while (true) {
			    try {
				juego = scanner.nextInt();
				break;
			    } catch (InputMismatchException e) {
				System.out.println(cadena);
				System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
				System.out.println(cadena);
				scanner.nextLine(); 
			    }
			}
		    }

		    if (!jugadorActual.suficientesCreditos()) {
			System.out.println("Lo siento, no tienes créditos para jugar algún juego");
		    } else {
			jugadorActual.cobro();
			System.out.println(cadena);
			System.out.println("Disfruta tu juego :).");
			System.out.println(cadena);

			//Juegos del día dos
			switch (juego) {
			case 1:
			    Salvados juegoSalvados = new Salvados(jugadorActual.obtenerNombre());
			    MainSalvados.main(new String[]{});//Se llama al main de Salvados para que se muestre todo el menú creado
			    //Se escriben las variables para que menu pueda tener acceso a ellas y las identifique
			    int res = 0;
			
			    int ultimaSilla = juegoSalvados.obtenerUltimaSilla();
                                
			    int resultado = evaluarResultadoSalvados(res, ultimaSilla); //Evalúa los resultados para asignar lospuntos

			    if (resultado == 1) {
				jugadorActual.agregarPuntos(12);  //Si el jugador ganó le da 12 pts
				System.out.println("Has ganado 12 puntos");
			    } else {
				jugadorActual.agregarPuntos(2);  //Si el jugador no ganó solo le da 2 pts
				System.out.println("Has ganado 2 puntos");
			    } 
			    System.out.println("Tus puntos actuales son: " + jugadorActual.obtenerPuntos());
			    System.out.println("Ya solo tienes " + jugadorActual.obtenerCreditos() + " creditos");
			    //Aqui va a checar que la posicion 2 sea diferente de nulo
			    //Porque si solo hay un jugador, siempre sera el primero 
			    if (players[1] == null) {
				System.out.println(cadena);
				System.out.println("Estas en la posicion 1 con " + jugadorActual.obtenerPuntos() + " puntos");
				System.out.println(cadena);
			    } else {
				System.out.println(cadena);
				System.out.println("Posiciones");
				System.out.println(cadena);
				jugador.posiciones(players);
				System.out.println(cadena);
			    }					
						
			    archivo2 = null;
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

			case 2: // Torres de Hanoi
                             
			 
			    Hanoi seguirJugandoHanoi = new Hanoi("\n");
			    mainHanoi.main(new String[]{});
			    //Prueba para que se den los puntos en el Hanoi.
			    String resultado1 = "0";
			    int resultado1Int = Integer.parseInt(resultado1);
			    int numD = 6;
			    int movimientos = Hanoi.movimientosMin(numD, 1, 2, 3); // Inicializamos la variable movimientos
			    int movimientosMinimos = Hanoi.movimientosMin(6, 1, 2, 3);  // Suponiendo que este método retorna el mínimo de movimientos
			    int resultadoH = evaluarResultadoHanoi(movimientos, resultado1Int, movimientosMinimos);
			
			    if(movimientos == 1){
				jugadorActual.agregarPuntos(15);
				System.out.println("Has ganado 15 puntos");
			    } else if (movimientos == 2){
				jugadorActual.agregarPuntos(10);
				System.out.println("Has ganado 10 puntos");

			    } else {
				jugadorActual.agregarPuntos(2);
				System.out.println("Has ganado 2 puntos");

			    }
		
			    System.out.println("\n Has ganado " + jugadorActual.obtenerPuntos() + " \n Tus puntos actuales son: " + jugadorActual.obtenerPuntos() + "\n Ya solo tienes " + jugadorActual.obtenerCreditos() + " créditos");

			    if (players[1] == null) {
				System.out.println(cadena);
				System.out.println("Estas en la posicion 1 con " + jugadorActual.obtenerPuntos() + " puntos");
				System.out.println(cadena);
			    } else {
				System.out.println(cadena);
				System.out.println("Posiciones");
				System.out.println(cadena);
				jugador.posiciones(players);
				System.out.println(cadena);
			    }
			}  
			
		    }

		}

		break;

                //Mejores 3 puntajes
	    case 3:
                //Aqui va a checar que la posicion 3 sea diferente de nulo
		//Porque sino hay 3 jugadores, no hay mejores 3 puntajes 
		if (players[2] == null) {
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
		if (jugadorActual == null) {
		    System.out.println("Por favor, regístrate primero.");
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

                //cambiar de día por si el jugador no quiere jugar ninguno de esos días
	    case 6:
		System.out.println(cadena);
		System.out.println("Has decidido cambiar de día.");
		dia = -1; //vuelve a pedile al jugador que vuelva a seleccionae el
		System.out.println(cadena);
		while (dia != 1 && dia != 2) {
		    try {
			System.out.println(cadena);
			System.out.println("Por favor, indica qué día quieres jugar");
			System.out.println(cadena);
			System.out.println("1. Día uno");
			System.out.println("2. Día dos");

			dia = scanner.nextInt(); // Lee la opción del día

			if (dia == 3) {
			    System.out.println("Ni modo, te pierdes de mucho, ¡adiós!");
			    return; // Si el jugador elige el día 3, termina el juego
			}

		    } catch (InputMismatchException e) {
			System.out.println(cadena);
			System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
			System.out.println(cadena);
			scanner.nextLine(); 
		    }
		}
		break;
            }
        }
    }
}
