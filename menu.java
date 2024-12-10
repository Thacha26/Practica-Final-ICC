/*Todo esto es un borrador para el programa

Los jugadores no pueden ver ni jugar juegos del dia posterior
En el menu se debe seleccionar el dia
Informarle que durante los dos dias solo tiene 100 creditos 

(El juego no dice cuantos puntos debe de regresar cada juego)

 ********Lo siguiente se hace con serializacion y desserializacion*******

El programa debe iniciar mostrando un menu con las siguientes opciones.

*Registro(Solo pide nombre)
    +Si un jugador ya esta registrado, solo se le informara y se le mostrar su numero de creditos 
    
*Iniciar un nuevo juego
    +Solo del dia donde estan

*Ver a los mejores 3 jugadores
    +(Puntaje de todos los juegos)

*Ver los puntos del jugador actual

*Guardar y salir

Una vez registrado un jugador, se le asignaran 100 creditos
Cada juego cuesta 15 creditos
Sino tiene creditos suficientes pues decirle que se chingo
 
  
 */

/*Planeacion del programa jsjs

  Al iniciar el juego, que te pida el dia en el que quieres jugar
  Luego que te muestre las tres opciones
  Con cada opcion hacer su debido tratamiento

  De momento, solo diseñare un menu basico con las opciones, y de apoco ire implementado las demas especificaciones y las exceptions 

  Para que el menu funcione, se me ocurre hacer una clase jugador, que contenga los puntos, creditos y nombre, y ahi hacer metodos como obtener creditos nombre y asi, ademas de un toString, para en el menu con las opciones de obtener puntaje y asi, serializar y deserializar los objetos y asi comparar o hacer lo necesario, lo que no tengo del todo claro es como se podria deserializar y buscar su nombre dentro de todo el archivo para obtener sus puntos o algo asi
  /*Ya se como hacerlo jasjasjsa, se me habia olvidado que cuando deserializas, haces la conversion directa a un objeto, y osea, segun yo, como ya vas a hacer la conversion directa, pues puedes usar metodos de ese objeto, entonces todo recairia en hacer metodos como obtener los 3 mejores, u obtener puntaje y cosas asi y eso implementarlos con las opciones del menu */
/*Clase jugador(Por decidir)
*Atributos *
*Nombre 
*Creditos
*Puntos 

*Metodos *
*Obtener nombre, creditos y Puntos 
*Obtener los mejores 3 
*Obtener los primeros 3 en acabar un juego 
*Entrada a un juego(Que cheque si hay creditos suficientes para entrar a un juego)
*Cobro(Que cobre la entrada a un juego)
*Agregar puntos(Que cuando acabe un programa sume los puntos)
*equals
*toString*/

import java.io.*;
import java.util.Scanner;

public class menu{
    public static void main(String[] args){
	File archivo = new File("jugadores.txt");
	Scanner scanner = new Scanner(System.in);
	int decision;
	int juego;
	int dia;
	String nombre;
	boolean seguir = true; 
        String cadena = "**************************************************************************";

	System.out.println(cadena);
	System.out.println("Por favor, indica que dia quieres jugar");
	System.out.println(cadena);
	System.out.println("1.Dia uno");
	System.out.println("2.Dia dos");
	System.out.println(cadena);

        dia = scanner.nextInt();

	while(dia != 1 && dia !=2){
	    System.out.println(cadena);
	    System.out.println("Por favor, esocoge una opcion valida");
	    System.out.println(cadena);

	    dia = scanner.nextInt();
	}

	System.out.println(cadena);
	System.out.println("                                  MENU");
	System.out.println(cadena);   
	
	System.out.println(cadena);
	System.out.println("Bienvenido jugador, estas en el dia uno");
	System.out.println(cadena);
	System.out.println("1.Registro");

	if(dia == 1){
	    System.out.println("2.Iniciar un juego (Cuadrado Magico o Conecta 4)");
	} else {
	    System.out.println("2.Iniciar un juego (Salvado o Torres de Hanoi)");  
	}
	
	System.out.println("3.Ver los mejores 3 puntajes");
	System.out.println("4.Ver tus puntos actuales");
	System.out.println("5.Guardar y salir");
	System.out.println(cadena);

	decision = scanner.nextInt();

	while(decision !=1 && decision !=2 && decision !=3 && decision !=4 && decision !=5){
	    System.out.println(cadena);
	    System.out.println("Por favor, escoge una opcion valida.");
	    System.out.println(cadena);

	    decision = scanner.nextInt();
	}

	while(seguir){
	    switch(decision){
		//Registro
	    case 1:
		System.out.println(cadena);
		System.out.println("Por favor, escribe solo tu nombre o apodo");
		System.out.println(cadena);

		nombre = scanner.nextLine();
		
	        //Que aqui siga el codigo con serializacion

		if(archivo.exists()){
		    if(archivo.length() == 0){
			//Serializar directo el archivo, darle 0 puntos, 100 creditos y registrar su nombre  
		    } else {
			//Verificar si el nombre ya esta registrado, si si, pues namas devolver el numero de creditos
		    }
		}
		
		break;

		//Iniciar un juego
	    case 2:
		System.out.println(cadena);
		if(dia == 1){
		    System.out.println("Que juego deseeas jugar?");
		    System.out.println(cadena);
		    System.out.println("1.Cuadrado magico");
		    System.out.println("2.Conecta 4");

		    juego = scanner.nextInt();

		    while(juego != 1 && juego !=2){
			System.out.println(cadena);
			System.out.println("Por favor, escoge una opcion valida");
			System.out.println(cadena);

			juego = scanner.nextInt();
		    }
		    //Que aqui incie cualquiera de los dos juegos del dia uno 
		} else {
		    System.out.println("Que juego deseeas jugar?");
		    System.out.println("1.Salvado");
		    System.out.println("2.Torres de Hanoi");

		    juego = scanner.nextInt();

		    while(juego != 1 && juego !=2){
			System.out.println(cadena);
			System.out.println("Por favor, escoge una opcion valida");
			System.out.println(cadena);

			juego = scanner.nextInt();
		    }
		    //Que aqui inicien los juegos del dia dos 
		}
		break;

		//Mejores 3 puntajes
	    case 3:
		System.out.println(cadena);
		System.out.println("Los mejores 3 puntajes son: ");
		System.out.println(cadena);

		//Que aqui inicie la deserializacion 
		
		break;

		//Tus puntos actuales 
	    case 4:
		System.out.println(cadena);
		System.out.println("Tus puntos actuales son: ");
		System.out.println(cadena);

		//Que aqui inicie la deserializacion 
		break;

		//Guardar y salir 
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
