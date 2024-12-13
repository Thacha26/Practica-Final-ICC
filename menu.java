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
        int dia = -1;
        int posicion = -1;
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

        // Selección de día
        while (dia != 1 && dia != 2 && dia != 3) {
            try {
                System.out.println(cadena);
                System.out.println("Por favor, escoge una opción válida");
                System.out.println(cadena);

                dia = scanner.nextInt();
                if (dia == 3) {
                    System.out.println("Ni modo, te pierdes de mucho, habían buenos juegos, ¡adiós!");
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println(cadena);
                System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
                System.out.println(cadena);
                scanner.nextLine(); // Limpiar el buffer de entrada
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

            // Leer opción del menú
            while (true) {
                try {
                    decision = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println(cadena);
                    System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
                    System.out.println(cadena);
                    scanner.nextLine(); // Limpiar el buffer de entrada
                }
            }

            // Validar la opción
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
                        scanner.nextLine(); // Limpiar el buffer de entrada
                    }
                }
            }

            switch (decision) {
                //Registro
                case 1:
                    System.out.println(cadena);
                    System.out.println("Por favor, escribe solo tu nombre o apodo");
                    System.out.println(cadena);

                    scanner.nextLine(); // Limpiar el buffer de entrada
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
                    //Iniciar un juego

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
                                scanner.nextLine(); // Limpiar el buffer de entrada
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
                                    scanner.nextLine(); // Limpiar el buffer de entrada
                                }
                            }
                        }

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
                        }

                        //Juegos del dia uno
                        switch (juego) {
                            case 1:  //Cuadrado Mágico
                                cuadradoMagico juegocuadradoMagico = new cuadradoMagico();
                                mainCuadradoMagico.main(new String[]{});
                                System.out.println("Tus puntos actuales son: " + jugadorActual.obtenerPuntos());
                                System.out.println("Ya solo tienes " + jugadorActual.obtenerCreditos() + " créditos");
                                break;

                            //Conecta 4
                            case 2:  
                                conecta4 juegoConecta4 = new conecta4();
                                main.main(new String[]{});
                                System.out.println("\nHas ganado " + jugadorActual.obtenerPuntos() + "\nTus puntos actuales son: " + jugadorActual.obtenerPuntos() + "\nYa solo tienes " + jugadorActual.obtenerCreditos() + " créditos");
                                break;
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
                                    scanner.nextLine(); // Limpiar el buffer de entrada
                                }
                            }
                        }

                        if (!jugadorActual.suficientesCreditos()) {
                            System.out.println("Lo siento, no tienes créditos para jugar algún juego");
                        } else {
                            jugadorActual.cobro();
                            System.out.println("Disfruta tu juego :).");
                        }

                        //Juegos del día dos
                        switch (juego) {
                            case 1:
                                Salvados juegoSalvados = new Salvados(jugadorActual.obtenerNombre());
                                MainSalvados.main(new String[]{});
                                System.out.println("\n Has ganado " + jugadorActual.obtenerPuntos() + " \n Tus puntos actuales son: " + jugadorActual.obtenerPuntos() + "\n Ya solo tienes " + jugadorActual.obtenerCreditos() + " créditos");
                                break;

                            case 2: // Torres de Hanoi
                                Hanoi seguirJugando = new Hanoi("\n");
                                mainHanoi.main(new String[]{});
                                System.out.println("\n Has ganado " + jugadorActual.obtenerPuntos() + " \n Tus puntos actuales son: " + jugadorActual.obtenerPuntos() + "\n Ya solo tienes " + jugadorActual.obtenerCreditos() + " créditos");
                                break;
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

                // Cambiar de día
                case 6:
                    System.out.println(cadena);
                    System.out.println("Has decidido cambiar de día.");
                    dia = -1; // Vuelve a pedir la selección del día
                    System.out.println(cadena);
                    while (dia != 1 && dia != 2 && dia != 3) {
                        try {
                            System.out.println(cadena);
                            System.out.println("Por favor, indica qué día quieres jugar");
                            System.out.println(cadena);
                            System.out.println("1. Día uno");
                            System.out.println("2. Día dos");
                            System.out.println("3. Día tres");
                            System.out.println(cadena);

                            dia = scanner.nextInt(); // Lee la opción del día

                            if (dia == 3) {
                                System.out.println("Ni modo, te pierdes de mucho, ¡adiós!");
                                return; // Si el jugador elige el día 3, termina el juego
                            }

                        } catch (InputMismatchException e) {
                            System.out.println(cadena);
                            System.out.println("Por favor, ingresa un número entero. Intenta de nuevo.");
                            System.out.println(cadena);
                            scanner.nextLine(); // Limpiar el buffer de entrada
                        }
                    }
                    break;
            }
        }
    }
}
