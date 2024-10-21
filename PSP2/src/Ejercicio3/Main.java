package Ejercicio3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		{

			int contador = 0;
			for (int i = 0; i < args.length; i++) {
				System.out.println("Nombre: " + args[i]);

			//Referencia a un fichero
			File archivo = new File(args[i]);
			if(archivo.exists()){
				//Leemos contenido del fichero
				FileReader fr = new FileReader(archivo);

				//El read lee carácter a carácter
				//Mientras haya caracteres cuenta
				while(fr.read() != -1){
					contador++;
				}

				System.out.println("El fichero " + args[i] + " tiene " + contador + " caracteres");
				System.out.println("Reseteamos el contador");
				contador = 0;
			}

			}


		}
	}
}