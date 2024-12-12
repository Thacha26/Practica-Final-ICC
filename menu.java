import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

public class menu {
    public static void main(String[] args) {
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
        System.out.println("Por favor, indica qué día quieres jugar");
        System.out.println(cadena);
        System.out.println("1. Día uno");
        System.out.println("2. Día dos");
        System.out.println(cadena);

        while (true) {
            try {
                dia = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println(cadena);
                System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
                System.out.println(cadena);
                scanner.nextLine();
            }
        }

        while (dia != 1 && dia != 2) {
            System.out.println(cadena);
            System.out.println("Por favor, escoge una opción válida");
            System.out.println(cadena);

            while (true) {
                try {
                    dia = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(cadena);
                    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
                    System.out.println(cadena);
                    scanner.nextLine();
                }
            }
        }

        while (seguir) {
            System.out.println(cadena);
            System.out.println("------ MENÚ ------");
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

            while (decision != 1 && decision != 2 && decision != 3 && decision != 4 && decision != 5) {
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

                case 1:
                    System.out.println(cadena);
                    System.out.println("Por favor, escribe solo tu nombre o apodo");
                    System.out.println(cadena);

                    scanner.nextLine();
                    nombre = scanner.nextLine();

                    // Deserializar para leer el archivo y ver si ya había jugadores registrados
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

                    for (int i = 0; i < players.length; i++) {
                        if (players[i] != null && players[i].obtenerNombre().equalsIgnoreCase(nombre)) {
                            System.out.println("Ya estás registrado, " + nombre + ". Tus créditos actuales son: " + players[i].obtenerCreditos());

                            jugadorActual = players[i];

                            existeJug = true;
                            break;
                        }
                    }
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

                // Iniciar un juego
                case 2:
                    if (jugadorActual == null) {
                        System.out.println("Por favor, regístrate primero.");
                        break;
                    }

                    System.out.println(cadena);
                    if (dia == 1) {
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
                            System.out.println(cadena);
                            System.out.println("Lo siento, no tienes créditos para jugar algún juego");
                            System.out.println(cadena);
                        } else {
                            jugadorActual.cobro();
                            System.out.println(cadena);
                            System.out.println("Disfruta tu juego :)");
                            System.out.println(cadena);
                        }

                        // Juegos del día 1
                        switch (juego) {
                            // Caso 1: Cuadrado Mágico
                            case 1:
                                CuadradoMagico juegoCuadradoMagico = new CuadradoMagico();
                                juegoCuadradoMagico.colocarAleatorio();
                                System.out.println(juegoCuadradoMagico.mostrarTablero());
                                boolean juegoTerminado = false;
                                while (!juegoTerminado) {
                                    System.out.println("¿Dónde quieres colocar el número? (Fila, Columna, Número)");
                                    int fila = scanner.nextInt();
                                    int columna = scanner.nextInt();
                                    int numero = scanner.nextInt();

                                    if (juegoCuadradoMagico.colocar(columna, fila, numero)) {
                                        System.out.println(juegoCuadradoMagico.mostrarTablero());
                                    } else {
                                        System.out.println("Movimiento inválido. Intenta de nuevo.");
                                    }

                                    System.out.println("\nHas ganado " + jugadorActual.obtenerPuntos() + "\nTus puntos actuales son: " + jugadorActual.obtenerPuntos());
                                    juegoTerminado = true;
                                }
                                break;

                            // Caso 2: Conecta 4
                            case 2:
                                conecta4 juegoConecta4 = new conecta4();

                                System.out.println(juegoConecta4.mostrarTablero());

                                boolean juegoTerminadoConecta4 = false;

                                while (!juegoTerminadoConecta4) {
                                    System.out.println("Es el turno del jugador " + (juegoConecta4.jugador == 'O' ? "O" : "X"));
                                    System.out.println("Elige una columna (0-6) para colocar tu ficha:");

                                    int columna = scanner.nextInt();

                                    if (juegoConecta4.colocar(columna)) {
                                        System.out.println(juegoConecta4.mostrarTablero());
                                    } else {
                                        System.out.println("Movimiento inválido. Intenta de nuevo.");
                                        continue;
                                    }

                                    char ganador = juegoConecta4.ganador();
                                    if (ganador != ' ') {
                                        if (ganador == 'O') {
                                            System.out.println("¡El jugador O ha ganado!");
                                        } else {
                                            System.out.println("¡El jugador X ha ganado!");
                                        }
                                        juegoTerminadoConecta4 = true;
                                    }

                                    if (juegoConecta4.finJuego()) {
                                        System.out.println("El juego terminó en empate.");
                                        juegoTerminadoConecta4 = true;
                                    }
                                }
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
						int posicion = -1;

						//Juegos del dia dos
						switch(juego){
							case 1:
							Salvados juegoSalvados = new Salvados(jugadorActual.obtenerNombre());
							   juegoSalvados.jugar();  // Aquí el jugador juega el juego de "Salvados"
					
							System.out.println("\n Has ganado " + jugadorActual.obtenerPuntos() + " \n Tus puntos actuales son: " + jugadorActual.obtenerPuntos());
				
							for (int i = 0; i < players.length; i++) {
								if (players[i] != null && players[i].obtenerNombre().equalsIgnoreCase(jugadorActual.obtenerNombre())) {
									posicion = i + 1;
									break;
								}
							}
							
							// Muestra la posición
							System.out.println("¡Felicidades! Has quedado en la posición " + posicion + " con " + jugadorActual.obtenerPuntos() + " puntos.");
						
						break;
				
							//Torres de hanoi
						   
						case 2: // Torres de Hanoi
							Hanoi seguirJugando = new Hanoi("\n");
							seguirJugando.jugar();  // Aquí se inicia el juego de Torres de Hanoi
				
							System.out.println("\n Has ganado " + jugadorActual.obtenerPuntos() + " \n Tus puntos actuales son: " + jugadorActual.obtenerPuntos());
							
							for (int i = 0; i < players.length; i++) {
								if (players[i] != null && players[i].obtenerNombre().equalsIgnoreCase(jugadorActual.obtenerNombre())) {
									posicion = i + 1;
									break;
								}
							}
							
							// Muestra la posición
							System.out.println("¡Felicidades! Has quedado en la posición " + posicion + " con " + jugadorActual.obtenerPuntos() + " puntos.");
							
						} 
						break;
					}
						

                // Ver mejores 3 puntajes
                case 3:
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

                // Ver puntos actuales
                case 4:
                    if (jugadorActual == null) {
                        System.out.println("Por favor, regístrate primero.");
                    } else {
                        System.out.println(cadena);
                        System.out.println("Tus puntos actuales son: " + jugadorActual.obtenerPuntos());
                        System.out.println(cadena);
                    }
                    break;

                // Guardar y salir
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
