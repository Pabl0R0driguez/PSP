package Ejercicio3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio3 extends Thread {
	int contador;
	String nombre;

	public Ejercicio3(String nombre) {
		this.nombre = nombre;
		contador = 0;
	}

	public void run() {
		File archivo = new File(nombre);
		if (archivo.exists()) {
			//Leemos contenido del fichero
			FileReader fr = null;
			try {
				fr = new FileReader(archivo);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}

			//El read lee carácter a carácter
			//Mientras haya caracteres cuenta
			while (true) {
				try {
					if (!(fr.read() != -1)) break;
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				contador++;
			}

			System.out.println("El fichero " + nombre + " tiene " + contador + " caracteres");
		}

	}
}
