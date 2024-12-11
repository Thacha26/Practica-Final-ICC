import java.util.Scanner;

public class Hanoi {
    protected int numD; //variable para los discos

    public Hanoi(int numD) { 
        this.numD = 6; //se inicializa el juego con 6 discos, siempre serán 6.
    }

    public void jugar() { //Se declaran los ponstes (torres)
        int[] poste1 = new int[numD];
        int[] poste2 = new int[numD];
        int[] poste3 = new int[numD];
        for (int i = 0; i < numD; i++) { //Todos los discos se inicializan en el primer poste
            poste1[i] = numD - i; //Los discos se inicializan de mayor a menor, repetando las reglas del juego
        }

        int movimientos = 0; //Se inicializa l contador de movimientos en 0
        boolean jugar = true; //mientras sea verdadero estará dentro del ciclo
        Scanner sc = new Scanner(System.in);

        while (jugar) {
            verPostes(poste1, poste2, poste3); //Muestra el usuario el estado de los postes
            System.out.println("Seleccione el poste(torre) para mover tu disco (1, 2, 3) o pulse 4 para salir:");
            int inicio = torreseleccionada(sc);
            if (inicio == 4) { //Sie el usuario selecciona el número 4 sale del juego y regresa al menú principal
                System.out.println("Terminaste el juego, see ya'");
                System.out.println("............................................");
                System.out.println("\n Hola otra vez, regresaste al menú principal :) ");
                break; //Se cierre el bucle del while y permita al usuario regresar al menú, si no, no puede salir nunca
            }

            System.out.println("Selecciona el poste(torre) de destino para el disco (1, 2, 3):");
            int allegar = torreseleccionada(sc); //La torre que el usuario seleccione será a la que el disco que esté hasta arriba llegará, de esta manera, solo un disco se moverá

            if (moverDisco(inicio, allegar, poste1, poste2, poste3)) { //mueve al disco
                movimientos++; //Se incrementa el número de movimientos
                if (gana(poste3)) { //verifica si el usuario ya ganó
                    verPostes(poste1, poste2, poste3); //muestra como se ven los postes después de ese movimietno
                    System.out.println("Bieeeeeeeen! ya terminaste el juego y fueron en " + movimientos + " movimientos.");
                    jugar = false; //se termina el juego
                }
            } else {
                System.out.println("Nonononoooo, ese movimiento no se puede, intenta otra vez"); //Si el disco no cumple con las reglas se pide que intente otra vez
            }
        }
    }

    //verifica que el número de poste que se infresó cumpla con los requerimentos, o sea, que sea 1, 2, 3
    protected int torreseleccionada(Scanner sc) {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("UY!, Error, trata de nuez.");
            }
        }
    }

    // Método para mostrar el estado actual de los postes
    @Override
    protected void verPostes(int[] poste1, int[] poste2, int[] poste3) {
        System.out.println("Tus torres se ven así:");
        System.out.println("Poste 1: " + verPostes(poste1));
        System.out.println("Poste 2: " + verPostes(poste2));
        System.out.println("Poste 3: " + verPostes(poste3));
    }

   protected String verPostes(int[] poste) {
        String resultado = "";
        for (int disco : poste) {
            if (disco != 0) {
                resultado += disco + " "; // Concatenación directa
            }
        }
        return resultado.trim(); // Elimina el último espacio adicional
    }

    // Método para mover un disco de un poste a otro
    protected boolean moverDisco(int inicio, int allegar, int[] poste1, int[] poste2, int[] poste3) {
        int[] posteinicio = seleccionarPoste(inicio, poste1, poste2, poste3);
        int[] posteallegar = seleccionarPoste(allegar, poste1, poste2, poste3);

        int disco = dicoDeArriba(posteinicio);
        if (disco == 0 || !movValido(disco, posteallegar)) {
            return false;
        }

        eliminarDiscoDeArriba(posteinicio);
        ponerDisco(disco, posteallegar);
        return true;
    }

    // Métodos auxiliares de la clase

    protected int[] seleccionarPoste(int num, int[] poste1, int[] poste2, int[] poste3) {
        if (num == 1) return poste1;
        if (num == 2) return poste2;
        return poste3;
    }

    protected int dicoDeArriba(int[] poste) {
        for (int i = poste.length - 1; i >= 0; i--) {
            if (poste[i] != 0) return poste[i];
        }
        return 0;
    }

    protected void eliminarDiscoDeArriba(int[] poste) {
        for (int i = poste.length - 1; i >= 0; i--) {
            if (poste[i] != 0) {
                poste[i] = 0;
                break;
            }
        }
    }

    protected void ponerDisco(int disco, int[] poste) {
        for (int i = 0; i < poste.length; i++) {
            if (poste[i] == 0) {
                poste[i] = disco;
                break;
            }
        }
    }

    protected boolean movValido(int disco, int[] posteallegar) {
        int dicoDeArriba = dicoDeArriba(posteallegar);
        return dicoDeArriba == 0 || disco < dicoDeArriba;
    }

    protected boolean gana(int[] poste3) {
        return poste3[poste3.length - 1] == 1; // El disco más pequeño está en la base de la torre final.
    }

    // Método para calcular los movimientos mínimos de forma recursiva
    public int movimientosMin(int num, int inicio, int temporal, int allegar) {
        if (num == 1) {
            return 1;
        } else {
            return 2 * movimientosMin(num - 1, inicio, allegar, temporal) + 1;
        }
    }

    public void mostrarSolucion(int num, int inicio, int temporal, int allegar)
    {
       
        if (num == 1) {
            System.out.println("Mover disco del poste (torre)" + inicio + " al poste " + allegar);
        } else {
            mostrarSolucion(num - 1, inicio, allegar, temporal);
            System.out.println("Mover disco del poste (torre) " + inicio + " al poste " + allegar);
            mostrarSolucion(num - 1, temporal, inicio, allegar);
        }
    }
}
