package Act1_5;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Actividad1_5 {
	public static void main(String[] args) throws IOException {



		File directorio = new File("out/production/A1_PSP");
		ProcessBuilder p1 = new ProcessBuilder("java" , "Act1_4.LeerNombre3");

		// se configura el directorio del directorio donde está el
		// proceso llamado, es decir, Act1_4.LeerNombre
		p1.directory(directorio);
		// Se lanza el proceso
		Process p = p1.start();

		try {
			//Recoge el flujo de salida del proceso como flujo de entrada propio
			InputStream is = p.getInputStream();
			int car;
			//Mientras exista car imprimelo
			while((car = is.read() ) != -1){
				System.out.print((char)car);
			}

			is.close();

		}catch (Exception e){
			//Para imprimir la pila de errores en caso de error
			e.printStackTrace();
		}

		int resultado;
		try {
			resultado = p.waitFor();
			System.out.println("El valor de salida es: " + resultado);

		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
