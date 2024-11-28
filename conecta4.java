import java.util.Scanner;
import java.util.Random;

/**
 * Clase conecta4 que tiene metodos para emuluar el clasico juego de conecta 4 en la clase main
 *
 * @author Espinosa Romero Frida Thais
 * @author Carriche Arriaga Dante Raziel
 * @version 27 de noviembre de 2024
 */
public class conecta4 {
    char jugador = 'O';
    char[][] conecta4 = new char[6][7];

    /**
     * Constructor de la clase que crea un arreglo bidimensional de 6 filas por 7 columnas, todos los espacios iniciados con
     * el valor de ' '
     */
    public conecta4() {
	for(int i = 0; i<conecta4.length; i++) {
	    for(int j = 0; j< conecta4[i].length; j++) {
		conecta4[i][j] = ' ';
	    }
	}
    }

    /**
     * Metodo cambiarColor que sirve para cambiar entre la O y la X, que representan a los dos jugadores
     */
    protected void cambiarColor() {
        if(jugador == 'O'){
	    jugador = 'X';
	} else {
	    jugador = 'O';
	}
    }

    /**
     * Metodo que coloca una ficha dependiendo de si esta en el rango y si el espacio esta disponible
     *
     * @param int col - columna donde se desea colocar la ficha
     * @return true - en caso de que el movimiento si sea posible 
     * @return false - en caso de que el movimiento no sea posible  
     */
    public boolean colocar(int col) {
	if (col >= 0 && col < 7) { 
	    for (int i = 5; i >= 0; i--) { 
		if (conecta4[i][col] == ' ') { 
		    conecta4[i][col] = jugador;
		    this.cambiarColor();
		    return true;
		}
	    }
	}
	System.out.println("Columna " + col + " llena.");
	return false; 
    }

    /**
     * Metodo mostrarTablero que visualisa en pantalla una representacion del tablero de juego
     *
     * @return String tabler - es la representacion del tablero, incluyendo los valores del arreglo 
     */
    public String mostrarTablero() {
	String renInf = "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
	String tablero = "_______________\n";
	int col, fil;

	for (fil = 0; fil < 6; fil++) { 
	    for (col = 0; col < 7; col++) { 
		tablero += "|" + conecta4[fil][col];
	    }
	    tablero += "|\n"; 
	}
	tablero += renInf; 
	return "|0|1|2|3|4|5|6|\n" + tablero + "*************************************************************";
    }

    /**
     * Metodo ganador que devuelve el caracter que representa al jugador que haya ganado al juntar 4 piezas
     *
     * @return char jugador - Representacion del ganador, ya se 'O' o 'X'
     */
    public char ganador() {
	// Horizontal
	for (int i = 0; i < conecta4.length; i++) {
	    for (int j = 0; j + 3 < conecta4[i].length; j++) {
		if (conecta4[i][j] == jugador &&
		    conecta4[i][j + 1] == jugador &&
		    conecta4[i][j + 2] == jugador &&
		    conecta4[i][j + 3] == jugador) {
		    return jugador;
		}
	    }
	}

	// Vertical
	for (int j = 0; j < conecta4[0].length; j++) {
	    for (int i = 0; i + 3 < conecta4.length; i++) {
		if (conecta4[i][j] == jugador &&
		    conecta4[i + 1][j] == jugador &&
		    conecta4[i + 2][j] == jugador &&
		    conecta4[i + 3][j] == jugador) {
		    return jugador;
		}
	    }
	}

	// Diagonal "\"
	for (int i = 0; i + 3 < conecta4.length; i++) {
	    for (int j = 0; j + 3 < conecta4[i].length; j++) {
		if (conecta4[i][j] == jugador &&
		    conecta4[i + 1][j + 1] == jugador &&
		    conecta4[i + 2][j + 2] == jugador &&
		    conecta4[i + 3][j + 3] == jugador) {
		    return jugador;
		}
	    }
	}

	// Diagonal "/"
	for (int i = 3; i < conecta4.length; i++) {
	    for (int j = 0; j + 3 < conecta4[i].length; j++) {
		if (conecta4[i][j] == jugador &&
		    conecta4[i - 1][j + 1] == jugador &&
		    conecta4[i - 2][j + 2] == jugador &&
		    conecta4[i - 3][j + 3] == jugador) {
		    return jugador;
		}
	    }
	}

	for (int i = 0; i < conecta4.length; i++) {
	    for (int j = 0; j < conecta4[i].length; j++) {
		if (conecta4[i][j] == ' ') {
		    return ' '; 
		}
	    }
	}
	return ' ';
    }

    /**
     * Metodo finJuego que sirve para comprobar cuando el juego ya se ha acabado, por que alguien ha ganado o porque
     * el tablero esta lleno
     *
     * @return boolean true - En caso de que el juego ya haya acabado
     * @return boolean false - En caso de que el juego aun no haya acabado
     */
    public boolean finJuego() {
	if (this.ganador() == 'X' || this.ganador() == 'O') {
	    return true;
	}

	for (int i = 0; i < conecta4.length; i++) {
	    for (int j = 0; j < conecta4[i].length; j++) {
		if (conecta4[i][j] == ' ') {
		    return false; 
		}
	    }
	}
	return true;
    }

    /**
     * Metodo versusComputadora que colocar fichas aleatoriamente en el tablero 
     */
    public void versusComputadora() {
	Random posicion = new Random();
	int col;

	do {
	    col = posicion.nextInt(7);
	} while (!colocar(col));
	System.out.println("La computadora coloco en: " + col);
    }

    /**
     * Metodo reiniciarJuego que solo le da el valor de ' ' para reiniciar el arreglo 
     */
    protected void reiniciarJuego() {
        for(int i = 0; i<conecta4.length; i++){
	    for(int j = 0; j<conecta4[i].length; j++){
		conecta4[i][j] = ' ';
	    }
	}
    }
}
