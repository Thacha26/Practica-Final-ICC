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

    /*CONSTRUCTOR*/
    //El constructor de siempre que crea el cuadraadoMagico con valores nulos en un inicio 
    public cuadradoMagico() {
	for(int i = 0; i<cuadrado.length; i++){
	    for(int j = 0; j<cuadrado[i].length; j++){
		cuadrado[i][j] = 0;
	    }
	}
    }

    /*TOSTRING*/ 
    //Una caja con cada espacio del arreglo
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
	
	
    /*METODOS*/
    
    /*colocar (boolean)*/
    //* Que cheque si el movimiento esta dentro del 4x4 
    //* Que cheque que el numero no este ya en el arreglo 
    //* Que cheque que en esa casilla no haya otro numero
    //* Que cheque que sea un numero entre 1-16

    public boolean colocar(int col, int fil, int num) {
	if(col >= 0 && col <= 4 && fil >= 0 && fil <= 4) {
	    if(cuadrado[col][fil] == 0){
		if(num >= 1 && num <= 16) {
		    for(int i = 0; i < cuadrado.length; i++) {
			for(int j = 0; j < cuadrado[i].length; j++) {
			    if(cuadrado[i][j] != num) {
				cuadrado[col][fil] = num;
				return true;
			    } else {
				return false;
			    }
			}
		    }
		    return false;
		}
		return false;
	    }
	    return false;
	}
	return false;
    }

    /*colocarAleatorio (void)*/
    //Primero que tire un numero entre el 1 y el 8 para que caiga en 8 opcione, luego que genere 3 numeros entre el 1 y
    //el 16 para definir toda la columna o fila o diagonal
    //Metodo que llene una columna fila o diagonal con la cual se inicia el juego
    public void colocarAleatorio() {
	Random random = new Random();
	int decision = random.nextInt(10);
	/*Checar que no genere numeros repetidos con el colocar*/
	int posicion1 = random.nextInt(15) + 1;
	int posicion2 = random.nextInt(15) + 1;
	int posicion3 = random.nextInt(15) + 1;
	int posicion4 = random.nextInt(15) + 1;

	while(posicion1 + posicion2 + posicion3 + posicion4 != 34 && posicion1 == posicion2 && posicion2 == posicion3 && posicion3 == posicion4) {
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
	case 3:
	    cuadrado[2][0] = posicion1;
	    cuadrado[2][1] = posicion2;
	    cuadrado[2][2] = posicion3;
	    cuadrado[2][3] = posicion4;
	    break;

	    //Para la cuarta vertical 0 0 0 1
	case 4:
	    cuadrado[3][0] = posicion1;
	    cuadrado[3][1] = posicion2;
	    cuadrado[3][2] = posicion3;
	    cuadrado[3][3] = posicion4;

	    //Para primer horizontal 1 0 0 0 
	case 5:
	    cuadrado[0][0] = posicion1;
	    cuadrado[1][0] = posicion2;
	    cuadrado[2][0] = posicion3;
	    cuadrado[3][0] = posicion4;
	    break;

	    //Para segunda horizontal 0 1 0 0
	case 6:
	    cuadrado[1][0] = posicion1;
	    cuadrado[1][1] = posicion2;
	    cuadrado[1][2] = posicion3;
	    cuadrado[1][3] = posicion4;
	    break;

	    //Para tercer horizontal 0 0 1 0
	case 7:
	    cuadrado[2][0] = posicion1;
	    cuadrado[2][1] = posicion2;
	    cuadrado[2][2] = posicion2;
	    cuadrado[2][3] = posicion4;
	    break;

	    //Para cuarta horizontal 0 0 0 1
	case 8:
	    cuadrado[3][0] = posicion1;
	    cuadrado[3][1] = posicion2;
	    cuadrado[3][2] = posicion3;
	    cuadrado[3][3] = posicion4;

	    //Para primer diagonal /
	case 9:
	    cuadrado[0][0] = posicion1;
	    cuadrado[1][1] = posicion2;
	    cuadrado[2][2] = posicion3;
	    cuadrado[3][3] = posicion4;
	    break;

	    //Para segunda diagonal \
	case 10:
	    cuadrado[0][3] = posicion1;
	    cuadrado[1][2] = posicion2;
	    cuadrado[2][1] = posicion1;
	    cuadrado[3][0] = posicion4;
	    break; 	    
	}
    }

    /*suma (boolean)*/
    //Metodo para que cheque que la suma de los elementos de las columnas, filas y diagonales sean iguales
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
    
    /*finJuego (boolean)*/
    //Primero que cheque que todas sena diferentes de 0 y luego que vea si la suma es igual a 34 
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
}
