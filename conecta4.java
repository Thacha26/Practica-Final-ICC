import java.util.Scanner;
import java.util.Random;

public class conecta4 {
    char jugador = 'O';
    char[][] conecta4 = new char[6][7];

    public conecta4() {
	for(int i = 0; i<conecta4.length; i++) {
	    for(int j = 0; j< conecta4[i].length; j++) {
		conecta4[i][j] = ' ';
	    }
	}
    }

    protected void cambiarColor() {
        if(jugador == 'O'){
	    jugador = 'X';
	} else {
	    jugador = 'O';
	}
    }

    public boolean colocar(int col) {
	if (col >= 0 && col < 7) { 
	    for (int i = 5; i >= 0; i--) { 
		// Verifica la condición de la casilla vacía
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
    
    public String mostrarTablero() {
	String renInf = "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯";
	String tablero = "_______________\n";
	int col, fil;

	for (fil = 0; fil < 6; fil++) { 
	    for (col = 0; col < 7; col++) { 
		tablero += "|" + conecta4[fil][col];
	    }
	    tablero += "|\n"; 
	}
	tablero += renInf; 
	return "|0|1|2|3|4|5|6|\n" + tablero;
    }

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

    
    public void versusComputadora() {
	Random posicion = new Random();
	int col;

	do {
	    col = posicion.nextInt(7);
	} while (!colocar(col));
	System.out.println("La computadora coloco en: " + col);
    }

    protected void reiniciarJuego() {
        for(int i = 0; i<conecta4.length; i++){
	    for(int j = 0; j<conecta4[i].length; j++){
		conecta4[i][j] = ' ';
	    }
	}
    }
}
