import java.io.Serializable;
/**
 * Clase jugador que tiene metodos para simular las caracteristicas de un jugador, tales como nombre, creditos y puntos
 *
 * @author Espinosa Romer Frida Thais
 * @author Carriche Arriaga Dante Raziel
 * @version 11 de ciciembre de 2024
 */
public class jugador implements Serializable, Comparable<jugador>{

    /*Atributos*/
    private String nombre;
    private int creditos;
    private int puntos;

    /**
     * Constructor por parametros que genera un jugador con nombre, 0 puntos y 100 creditos
     *
     * @param String nombre - Nombre con el cual se va a crear el jugador 
     */
    public jugador(String nombre){
	this.nombre = nombre;
	this.creditos = 100;
	this.puntos = 0;
    }
    
    /*Metodos*/

    /**
     * Metodo obtenerNombre que devuelve el nombre de un jugador
     *
     * @return nombre - Nombre del jugador
     */
    public String obtenerNombre(){
	return nombre;
    }

    /**
     * Metodo obtenerCreditos que devuelve los creditos totales de un jugador
     *
     * @return creditos - Numero de creditos de un jugador 
     */
    public int obtenerCreditos(){
	return creditos;
    }

    /**
     * Metodo obtenerPuntos que devuelve los puntos totales de un jugador
     *
     * @return puntos - Numero de puntos de un jugador 
     */
    public int obtenerPuntos(){
	return puntos;
    }

    /**
     * Metodo suficientesCreditos que evalua si un jugador puede jugar con los creditos que posee
     *
     * @return true - Si tiene los creditos suficientes
     * @return false - Si no tiene creditos suficeintes 
     */
    public boolean suficientesCreditos(){
	if(this.creditos >= 15){
	    return true;
	} return false;
    }

    /**
     * Metodo cobro que resta creditos a un jugador a la hora de entrar a un juego
     */
    public void cobro(){
	this.creditos -= 15;
    }

    /**
     * Metodo agregarPuntos que suma los puntos ganados en un juego
     *
     * @param int puntos - Puntos sumados al ganar en un juego 
     */
    public void agregarPuntos(int puntos){
	this.puntos += puntos;
    }

    /**
     * Metodo compareTo que compara dos jugadores y establece una prepoderancia en funcion de los puntos
     *
     * @param jugador j2 - Jugador con el que se va a hacer la comparacion
     * @return int - 1 si es mayor el primero al segundo, 0 si son iguales y -1 si el segundo es mayor al primero 
     */
    public int compareTo(jugador j2){
	return Integer.compare(j2.obtenerPuntos(), this.puntos);
    }

    /**
     * Metodo mejores3 que ordena el arreglo de manera ascendente y devuelve solo las primeras 3 posiciones
     *
     * @param jugador[] j - Arreglo sobre el que se obtendra los mayores 3
     */
    public static void mejores3(jugador[] j){
	jugador[] ordenado = j.clone(); // Clonar para no modificar el arreglo original
	jugador aux;
	int cont1, cont2;

	if(ordenado == null) {
	    throw new NullPointerException("No puedes ordenar un arreglo vacío");
	}

	// Arreglo para que los espacios vacíos los tome con 0 puntos
	for(int i = 0; i < ordenado.length; i++) {
	    if(ordenado[i] == null) {
		ordenado[i] = new jugador(" ");
	    }
	}

	// Implementación de Insertion Sort
	for(cont1 = 1; cont1 < ordenado.length; cont1++) {
	    aux = ordenado[cont1];

	    for(cont2 = cont1 - 1; cont2 >= 0 && ordenado[cont2].compareTo(aux) > 0; cont2--) {
		ordenado[cont2 + 1] = ordenado[cont2];
	    }
	    ordenado[cont2 + 1] = aux;
	}

	// Mostrar los 3 mejores jugadores
	System.out.println("Los 3 mejores jugadores son:");
	for (int i = 0; i < 3 && i < ordenado.length; i++) {
	    if (!ordenado[i].obtenerNombre().equals(" ")) {
		System.out.println(ordenado[i]);
	    }
	}
    }

    /**
     * Metodo toString que da un formato de impresion a los jugadores
     *
     * @return String jugador - Formato de imprecion de un jugador, que posee nombre, creditos y puntos 
     */
    public String toString(){
        String jugador = "Nombre: " + nombre + "\n"
	    + "Creditos: " + creditos + "\n"
	    + "Puntos: " + puntos;
	return jugador;
    }
}
