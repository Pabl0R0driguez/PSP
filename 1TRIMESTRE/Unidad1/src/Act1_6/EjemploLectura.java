package Act1_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class EjemploLectura {
		public static void main (String [] args) {
			int numero = 0;
			int numero2 = 0 ;
			Scanner sc = new Scanner(System.in);
	//OutputStream
		if(sc.hasNextInt()) {
			numero = sc.nextInt();
		}
		else{
			System.out.println("Introduce un número entero");
			}
		if(sc.hasNextInt()) {
			numero2 = sc.nextInt();
		}
		else{
			System.out.println("Introduce un número entero");
		}
//Input
		int resultado = numero + numero2;
		System.out.println(resultado);
		}

		}


