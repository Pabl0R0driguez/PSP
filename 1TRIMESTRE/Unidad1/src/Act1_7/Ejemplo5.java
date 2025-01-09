package Act1_7;

import java.io.*;
import java.util.Scanner;


public class Ejemplo5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String texto;
		try {
			System.out.println("Introduce una cadena ");
			texto = sc.next();
			System.out.println("Cadena escrita: " + texto);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}