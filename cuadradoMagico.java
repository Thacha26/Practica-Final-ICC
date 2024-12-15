import java.util.Random;

/**
 * Clase que contiene los metodos necesarios para jugar el cuadrado magico en un metodo main
 *
 * @author Espinosa Romero Frida Thais
 * @author Carriche Arriaga Dante Raziel
 * @version 1 de diciembre de 2024
 */
public class cuadradoMagico {
    /*ATRIBUTOS*/
    //arreglo de enteros de 4x4
    int[][] cuadrado = new int[4][4];
    //La forma para obtener la constante magica es a traves de la formula n(n^2 + 1)/2, con n el numero de filas  
    int constanteMagica = 34;

    /**
     * Constructor que crea un arreglo de enteros de 4 por 4
     */
    public cuadradoMagico() {
	for(int i = 0; i<cuadrado.length; i++){
	    for(int j = 0; j<cuadrado[i].length; j++){
		cuadrado[i][j] = 0;
	    }
	}
    }

    /**
     * Metodo mostrarTablero que imprime en pantalla el tablero con todas las posiciones y su contenido
     *
     * @return tabler - Un String que es la representacion de un tablero 
     */
    public String mostrarTablero() {
	String tablero = "   | 0 | 1 | 2 | 3 |\n";
	String guionSup = "   _________________\n";
	String guionInf = "   ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
	tablero+= guionSup;
	for(int col = 0; col<cuadrado.length; col++){
	    tablero += col;
	    for(int fil = 0; fil<cuadrado[col].length; fil++){
		tablero += "  |" + cuadrado[col][fil];
	    }
	    tablero += "  | \n";
	}
	return tablero += guionInf;
    }
	

    /**
     * Metodo colocar que regresa un booleano que indica si un movimiento es posible o no
     *
     * @return false - Si el movimiento no es posible 
     * @return true - Si el movimiento si es posible 
     */
    public boolean colocar(int col, int fil, int num) {
	if(col >= 0 && col <= 3 && fil >= 0 && fil <= 3) {
	    if(cuadrado[col][fil] == 0){
		if(num >= 1 && num <= 16) {
		    for(int i = 0; i < cuadrado.length; i++) {
			for(int j = 0; j < cuadrado[i].length; j++) {
			    if(cuadrado[i][j] == num){
			        return false;
			    }
			}
		    }
		    cuadrado[col][fil] = num;
		    return true;
		}
	    }
	}
	return false;
    }

    /**
     * Metodo colocarAleatorio que coloca una fila, columna o diagonal con numeros aleatorios que suman 34 y no se repiten
     */
    public void colocarAleatorio() {
	Random random = new Random();
	
	int decision = random.nextInt(9);
	
	int posicion1 = random.nextInt(15) + 1;
	int posicion2 = random.nextInt(15) + 1;
	int posicion3 = random.nextInt(15) + 1;
	int posicion4 = random.nextInt(15) + 1;
	
        //Restriccion para checar que no genere ni numeros repetidos y que su suma sea 34
	while(posicion1 + posicion2 + posicion3 + posicion4 != 34 || posicion1 == posicion2 || posicion1 == posicion3 || posicion1 == posicion4 || posicion2 == posicion1 || posicion2 == posicion3 || posicion2 == posicion4 || posicion3 == posicion4){
		posicion1 = random.nextInt(15) + 1;
		posicion2 = random.nextInt(15) + 1;
		posicion3 = random.nextInt(15) + 1;
		posicion4 = random.nextInt(15) + 1;
	}
	
	switch(decision) {
	    //Para primer vertical 1 0 0 0
	case 0:
	    cuadrado[0][0] = posicion1;
	    cuadrado[0][1] = posicion2;
	    cuadrado[0][2] = posicion3;
	    cuadrado[0][3] = posicion4;
	    break;

	    //Para segunda vertical 0 1 0 0 
	case 1:
	    cuadrado[1][0] = posicion1;
	    cuadrado[1][1] = posicion2;
	    cuadrado[1][2] = posicion3;
	    cuadrado[1][3] = posicion4;
	    break;

	    //Para tercer vertical 0 0 1 0 
	case 2:
	    cuadrado[2][0] = posicion1;
	    cuadrado[2][1] = posicion2;
	    cuadrado[2][2] = posicion3;
	    cuadrado[2][3] = posicion4;
	    break;

	    //Para la cuarta vertical 0 0 0 1
	case 3:
	    cuadrado[3][0] = posicion1;
	    cuadrado[3][1] = posicion2;
	    cuadrado[3][2] = posicion3;
	    cuadrado[3][3] = posicion4;
	    break;

	    //Para primer horizontal 1 0 0 0 
	case 4:
	    cuadrado[0][0] = posicion1;
	    cuadrado[1][0] = posicion2;
	    cuadrado[2][0] = posicion3;
	    cuadrado[3][0] = posicion4;
	    break;

	    //Para segunda horizontal 0 1 0 0
	case 5:
	    cuadrado[1][0] = posicion1;
	    cuadrado[1][1] = posicion2;
	    cuadrado[1][2] = posicion3;
	    cuadrado[1][3] = posicion4;
	    break;

	    //Para tercer horizontal 0 0 1 0
	case 6:
	    cuadrado[2][0] = posicion1;
	    cuadrado[2][1] = posicion2;
	    cuadrado[2][2] = posicion3;
	    cuadrado[2][3] = posicion4;
	    break;

	    //Para cuarta horizontal 0 0 0 1
	case 7:
	    cuadrado[3][0] = posicion1;
	    cuadrado[3][1] = posicion2;
	    cuadrado[3][2] = posicion3;
	    cuadrado[3][3] = posicion4;
	    break;

	    //Para primer diagonal /
	case 8:
	    cuadrado[0][0] = posicion1;
	    cuadrado[1][1] = posicion2;
	    cuadrado[2][2] = posicion3;
	    cuadrado[3][3] = posicion4;
	    break;

	    //Para segunda diagonal \
	case 9:
	    cuadrado[0][3] = posicion1;
	    cuadrado[1][2] = posicion2;
	    cuadrado[2][1] = posicion3;
	    cuadrado[3][0] = posicion4;
	    break; 	    
	}
    }

    /**
     * Metodo suma que verifica si todas las columnas, filas y diagonales suman lo mismo 
     */
    public boolean suma() {
	//Primer vertical
	int resultado1 = cuadrado[0][0] + cuadrado[0][1] + cuadrado[0][2] + cuadrado[0][3];
	//Segunda vertical 
	int resultado2 = cuadrado[1][0] + cuadrado[1][1] + cuadrado[1][2] + cuadrado[1][3];
	//Tercer vertical 
	int resultado3 = cuadrado[2][0] + cuadrado[2][1] + cuadrado[2][2] + cuadrado[2][3];
	//Cuarta vertical
	int resultado4 = cuadrado[3][0] + cuadrado[3][1] + cuadrado[3][2] + cuadrado[3][3];
	//Primer horizontal  
	int resultado5 = cuadrado[0][0] + cuadrado[1][0] + cuadrado[2][0] + cuadrado[3][0];
	//Segunda horizontal  
	int resultado6 = cuadrado[0][1] + cuadrado[1][1] + cuadrado[2][1] + cuadrado[3][1];
	//Tercer horizontal
	int resultado7 = cuadrado[0][2] + cuadrado[1][2] + cuadrado[2][2] + cuadrado[3][2];
	//Cuarta horizontal
	int resultado8 = cuadrado[0][3] + cuadrado[1][3] + cuadrado[2][3] + cuadrado[3][3];
	//Primer diagonal /
	int resultado9 = cuadrado[0][3] + cuadrado[1][2] + cuadrado[2][1] + cuadrado[3][0];
	//Segunda diagonal \
	int resultado10 = cuadrado[0][0] + cuadrado[1][1] + cuadrado[2][2] + cuadrado[3][3];

	if(resultado1 == resultado2 && resultado2 == resultado3 && resultado3 == resultado4 && resultado4 == resultado5 && resultado5 == resultado6 && resultado6 == resultado7 && resultado7 == resultado8 && resultado8 == resultado9 && resultado9 == resultado10 && resultado10 == constanteMagica){
	    return true;
	} return false;
    }

    /**
     * Metodo finJuego que verifica si un juego ha terminado
     *
     * @return true - Si se completa alguna fila columna o diagonal que no sume 34
     * @return true - Si no se cumple lo anterior 
     */
    public boolean finJuego() {
	if(this.suma()) {
	    return true;
	}
	//Primer vertical 1 0 0 0
	if(cuadrado[0][0] != 0 && cuadrado[0][1] != 0 && cuadrado[0][2] != 0 && cuadrado[0][3] != 0){
	    if(cuadrado[0][0] + cuadrado[0][1] + cuadrado[0][2] + cuadrado[0][3] != 34){
		return true;
	    }
	}
	//Segunda vertical 0 1 0 0
	if(cuadrado[1][0] != 0 && cuadrado[1][1] != 0 && cuadrado[1][2] != 0 && cuadrado[1][3] != 0){
	    if(cuadrado[1][0] + cuadrado[1][1] + cuadrado[1][2] + cuadrado[1][3] != 34){
		return true;
	    }
	}
	//Tercera vertical 0 0 1 0
	if(cuadrado[2][0] != 0 && cuadrado[2][1] != 0 && cuadrado[2][2] != 0 && cuadrado[2][3] != 0){
	    if(cuadrado[2][0] + cuadrado[2][1] + cuadrado[2][2] + cuadrado[2][3] != 34){
		return true;
	    }
	}
	//Cuarta vertical 0 0 0 1
	if(cuadrado[3][0] != 0 && cuadrado[3][1] != 0 && cuadrado[3][2] != 0 && cuadrado[3][3] != 0){
	    if(cuadrado[3][0] + cuadrado[3][1] + cuadrado[3][2] + cuadrado[3][3] != 34){
		return true;
	    }
	}
	//Primera horizontal 1 0 0 0
	if(cuadrado[0][0] != 0 && cuadrado[1][0] != 0 && cuadrado[2][0] != 0 && cuadrado[3][0] != 0){
	    if(cuadrado[0][0] + cuadrado[1][0] + cuadrado[2][0] + cuadrado[3][0] != 34){
		return true;
	    }
	}
	//Segunda horizontal 0 1 0 0
	if(cuadrado[0][1] != 0 && cuadrado[1][1] != 0 && cuadrado[2][1] != 0 && cuadrado[3][1] != 0){
	    if(cuadrado[0][1] + cuadrado[1][1] + cuadrado[2][1] + cuadrado[3][1] != 34){
		return true;
	    }
	}
	//Tercera horizontal 0 0 1 0
	if(cuadrado[0][2] != 0 && cuadrado[1][2] != 0 && cuadrado[2][2] != 0 && cuadrado[3][2] != 0){
	    if(cuadrado[0][2] + cuadrado[1][2] + cuadrado[2][2] + cuadrado[3][2] != 34){
		return true;
	    }
	}
	//Cuarta horizontal 0 0 0 1
	if(cuadrado[0][3] != 0 && cuadrado[1][3] != 0 && cuadrado[2][3] != 0 && cuadrado[3][3] != 0){
	    if(cuadrado[0][3]+ cuadrado[1][3] + cuadrado[2][3] + cuadrado[3][3] != 34){
		return true;
	    }
	}
	//Primer diagonal /
	if(cuadrado[0][3] != 0 && cuadrado[1][2] != 0 && cuadrado[2][1] != 0 && cuadrado[3][0] != 0){
	    if(cuadrado[0][3] + cuadrado[1][2] + cuadrado[2][1] + cuadrado[3][0] != 34){
		return true;
	    }
	}
	//Segunda diagonal \
	if(cuadrado[0][0] != 0 && cuadrado[1][1] != 0 && cuadrado[2][2] != 0 && cuadrado[3][3] != 0){
	    if(cuadrado[0][0] + cuadrado[1][1] + cuadrado[2][2] + cuadrado[3][3] != 34){
		return true;
	    }
	}
	return false;
    }

    /**
     * Metodo tableroLLeno que verifica si el tablero se ha llenado, es decir, si todos los espacios son diferentes de 0
     *
     * @return false - Si se encuentra un espacio igual a 0
     * @return true - Si todo el tablero es diferente a 0
     */
    public boolean tableroLLeno() {
	for(int i = 0; i < cuadrado.length; i++){
	    for(int j = 0; j < cuadrado[i].length; j++){
		if(cuadrado[i][j] == 0){
		    return false;
		}
	    }
	}
	return true;
    }

    /**
     * Metodo limpiar que restablece todos los valores del arreglo iguales a 0
     */
    public void limpiar() {
	for(int i = 0; i < cuadrado.length; i++) {
	    for(int j = 0; j < cuadrado[i].length; j++){
		cuadrado[i][j] = 0;
	    }
	}
    }
}
