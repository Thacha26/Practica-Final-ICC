/**
 * Clase del juego "Hanoi" EL jugador debe de mover los discos correctamente
 * @author Espinosa Romero Frida Thais
 * @author Carriche Arriaga Dante Raziel
 * 11 Diciembre 2024 @version 8
 */

 import java.util.Scanner;

 public class Hanoi {
     public int numD; // Variable para el número de discos
     String separador = "..............................................";
    
     public Hanoi(String var) {
         this.numD = 6; //se inicializa el juego con 6 discos, siempre serán 6.
     }
   
     /**
      * Método principal
      * El jugador debe de mover todos los discos entre los postes hasta completar el juego siempre siguiendo las reglas
      * El número de movimientos se cuenta y si lo hace en el ínimo se le dann los puntos máximos, si no, se le descuentan
      */

      
     public void jugar() {
         // Se declaran los ponstes (torres) y el num de discos
         int[] poste1 = new int[numD];
         int[] poste2 = new int[numD];
         int[] poste3 = new int[numD];
         //Todos los discos se inicializan en el primer poste
         //Los discos se inicializan de mayor a menor, repetando las reglas del juego
         for (int i = 0; i < numD; i++) {
             poste1[i] = numD - i;
         }
 
         int movimientos = 0; // Contador de movimientos realizados.
         boolean jugar = true; //mientras sea verdadero estará dentro del ciclo
         Scanner sc = new Scanner(System.in);
 
         while (jugar) {
             verPostes(poste1, poste2, poste3); // Muestra el estado actual de los postes
             System.out.println("Seleccione el poste(torre) para mover tu disco (1, 2, 3) o pulse 4 para salir:");
             int inicio = torreseleccionada(sc);
 
             if (inicio == 4) { //Sie el usuario selecciona el número 4 sale del juego y regresa al menú principal
                 System.out.println("Terminaste el juego, see ya'");
                 System.out.println("............................................");
                 System.out.println("\n Hola otra vez, vas a regresar al menú principal :) ");
                 break; //Se cierre el bucle del while y permita al usuario regresar al menú, si no, no puede salir nunca
             }
 
             System.out.println("Selecciona el poste(torre) de destino para el disco (1, 2, 3):");
             int allegar = torreseleccionada(sc); //La torre que el usuario seleccione será a la que el disco que esté hasta arriba llegará, de esta manera, solo un disco se moverá
 
             if (moverDisco(inicio, allegar, poste1, poste2, poste3)) { // Mueve el disco si el moviento es válido
                 movimientos++; // Incrementa número de movimientos
                 if (gana(poste3)) { // Verifica si el usuario ya ganó
                     verPostes(poste1, poste2, poste3); //muestra como se ven los postes después de ese movimietno
                     System.out.println("Bieeeeeeeen! ya terminaste el juego y fueron en " + movimientos + " movimientos.");

                     jugar = false; //se termina el juego.
                 }
             } else {
                 System.out.println("Nonononoooo, ese movimiento no se puede, intenta otra vez"); //Si el disco no cumple con las reglas se pide que intente otra vez
             }
         }
     }
 
     /**
      * Método psrs verificar que el número ingresado para seleccionar un poste sea válido, o sea que esté dentro del rngo establecido
      *
      * @param sc El escáner para leer lo qie el usuario ingrese
      * @return El poste seleccionado
      */
      public int torreseleccionada(Scanner sc) {
         while (true) {     //verifica que el número de poste que se infresó cumpla con los requerimentos, o sea, que sea 1, 2, 3
             try {
                 int seleccion = Integer.parseInt(sc.nextLine());
                 if (seleccion >= 1 && seleccion <= 4) {
                     return seleccion; // Retorna el número del poste si es válido.
                 } else {
                     System.out.println("Solo puedes seleccionar un número a la vez y que esté entre 1 y 3, o en su defecto 4 para salir");
                 }
             } catch (NumberFormatException e) {
                 System.out.println("UY!, Error, trata de nuevo. Debes ingresar un número.");
             }
         }
     }
 
     /**
      * Método para mostrar el estado actual de los postes.
      *
      * @param poste1 El primer poste (torre).
      * @param poste2 El segundo poste (torre).
      * @param poste3 El tercer poste (torre).
      */
      public void verPostes(int[] poste1, int[] poste2, int[] poste3) {
         System.out.println("Tus torres se ven así:");
         System.out.println("Poste 1: " + verPostes(poste1));
         System.out.println("Poste 2: " + verPostes(poste2));
         System.out.println("Poste 3: " + verPostes(poste3));
     }
 
     /**
      * Convierte el estado de un poste en una cadena de texto donde muestra los discos qu e tiene
      *
      * @param poste El arreglo que representa a ell poste
      * @return la cadena de texto con los discos ordenados
      */
      public String verPostes(int[] poste) {
         String resultado = "";
         for (int disco : poste) {
             if (disco != 0) {
                 resultado += disco + " "; // Concatena discos 
             }
         }
         return resultado.trim(); //Elimina el último espacio que sobra
     }
 
     /**
      *  Método para mover un disco de un poste a otro
      *
      * @param inicio El número del poste de inicio (1, 2, o 3).
      * @param allegar El número del poste de destino (1, 2, o 3).
      * @param poste1 El primer poste (torre).
      * @param poste2 El segundo poste (torre).
      * @param poste3 El tercer poste (torre).
      * @return true si el movimiento es válido, false si no es válido
      */
      public boolean moverDisco(int inicio, int allegar, int[] poste1, int[] poste2, int[] poste3) {
         int[] posteinicio = seleccionarPoste(inicio, poste1, poste2, poste3);
         int[] posteallegar = seleccionarPoste(allegar, poste1, poste2, poste3);
 
         int disco = dicoDeArriba(posteinicio);
         if (disco == 0 || !movValido(disco, posteallegar)) {
             return false; //Movimiento inválido si no hay disco en el poste de inicio o el movimiento no cumple con las reglas de los discos
         }
 
         eliminarDiscoDeArriba(posteinicio); //Elimina el disco de arriba del poste de inicio
         ponerDisco(disco, posteallegar); //Se mueve a el disco en el poste de destino
         return true;
     }
 
     /**
      * Selecciona el poste que el usuario quiere (1, 2 o 3).
      *
      * @param num El número del poste seleccionado.
      * @param poste1 El primer poste (torre).
      * @param poste2 El segundo poste (torre).
      * @param poste3 El tercer poste (torre).
      * @return El arreglo según el poste seleccionado
      */
      public int[] seleccionarPoste(int num, int[] poste1, int[] poste2, int[] poste3) {
         if (num == 1) return poste1;
         if (num == 2) return poste2;
         return poste3;
     }
 
     /**
      * Obtiene el disco superior del poste (el disco en la posición más alta).
      *
      * @param poste El arreglo del poste 
      * @return El valor del disco de hasata arriba, o 0 si el poste no tiene ningún disco
      */
      public int dicoDeArriba(int[] poste) {
         for (int i = poste.length - 1; i >= 0; i--) {
             if (poste[i] != 0) return poste[i]; // Retorna el disco de hasta arriba
         }
         return 0; //si el poste está vacío regresa 0
     }
 
     /**
      * Elimina el disco de hasta arriba del poste.
      *
      * @param poste El arreglo del poste del que se va a eliminar el disco superior.
      */
      public void eliminarDiscoDeArriba(int[] poste) {
         for (int i = poste.length - 1; i >= 0; i--) {
             if (poste[i] != 0) {
                 poste[i] = 0; //Elimina el disco para que no se duplique
                 break;
             }
         }
     }
 
     /**
      * Coloca un disco en el poste seleccionado, en la primera posición vacía, o sea hasata arriba
      *
      * @param disco El número del disco que se va a colocar.
      * @param poste El arreglo del poste donde se va a colocar el disco.
      */
      public void ponerDisco(int disco, int[] poste) {
         for (int i = 0; i < poste.length; i++) {
             if (poste[i] == 0) {
                 poste[i] = disco; //coloca el disco emnor hasta arriba
                 break;
             }
         }
     }
 
     /**
      * Verifica si el movimiento de un disco es válido.
      * Solo puede moverse si el poste de destino está vacío o si el disco a mover es más pequeño que el disco que está en la parte de hasta arriba en ese momento
      *
      * @param disco El disco que se va a mover.
      * @param posteallegar El arreglo del poste de destino.
      * @return true si el movimiento es válido, false si no lo es
      */
      public boolean movValido(int disco, int[] posteallegar) {
         int dicoDeArriba = dicoDeArriba(posteallegar);
         return dicoDeArriba == 0 || disco < dicoDeArriba; //verifica si el disco de hasta arriba es menor que el que se encuentra en el poste seleccionado
     }
 
     /**
      * Verifica si el usuario ya ganó
      * El jugador gana si el disco más pequeño (el 1) está en la base del tercer poste
      *
      * @param poste3 El tercer poste
      * @return true si el jugador ha ganado, false si no
      */
     public boolean gana(int[] poste3) {
         return poste3[poste3.length - 1] == 1; // El disco más pequeño debe estar en la base de la torre final
     }
 
     /**
      * Calcula de forma recursiva el número mínimo de movimientos necesarios para resolver el juego.
      *
      * @param num El número de discos.
      * @param inicio El poste de inicio.
      * @param temporal El poste temporal para usar en el proceso.
      * @param allegar El poste final donde deben ir los discos.
      * @return El número mínimo de movimientos necesarios para resolver el juego.
      */
     public int movimientosMin(int num, int inicio, int temporal, int allegar) {
         if (num == 1) {
             return 1;
         } else {
             return 2 * movimientosMin(num - 1, inicio, allegar, temporal) + 1;
         }
     }
 
     /**
      * Muestra la solución del juego de las Torres de Hanoi, es decir, la secuencia de movimientos necesarios para resolverlo
      *
      * @param num El número de discos.
      * @param inicio El poste de inicio.
      * @param temporal El poste temporal.
      * @param allegar El poste final.
      */
     public void mostrarSolucion(int num, int inicio, int temporal, int allegar) {
         if (num == 1) {
             System.out.println("Mover disco del poste (torre)" + inicio + " al poste " + allegar);
         } else {
             mostrarSolucion(num - 1, inicio, allegar, temporal); 
             System.out.println("Mover disco del poste (torre) " + inicio + " al poste " + allegar);
             mostrarSolucion(num - 1, temporal, inicio, allegar); 
         }
         
     }
 }