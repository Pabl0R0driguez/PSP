package Act1_7;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Actividad1_7 {
	public static void main(String[] args) throws IOException {


		File directorio = new File("out/production/A1_PSP");
		ProcessBuilder p1 = new ProcessBuilder("java", "Act1_7.Ejemplo5", "ffff");

		File fBat = new File("input.txt");
		File fOut = new File("output.txt");
		File fErr = new File("error.txt");


		p1.redirectInput(ProcessBuilder.Redirect.from(fBat));
		p1.redirectInput(ProcessBuilder.Redirect.from(fOut));
		p1.redirectInput(ProcessBuilder.Redirect.from(fErr));


		p1.directory(directorio);
		// Se lanza el proceso
		Process p = p1.start();

		try {
			OutputStream out = p.getOutputStream();
			out.write("messi\n".getBytes());
			out.flush();

		} catch (Exception e) {
			//Para imprimir la pila de errores en caso de error
			e.printStackTrace();
		}


//InputStream, recoge la salida del Process(resultado)
		try {
			InputStream is = p.getInputStream();
			int car;
			//Mientras exista car imprimelo
			while ((car = is.read()) != -1) {
				System.out.print((char) car);
			}

			is.close();

		} catch (Exception e) {
			//Para imprimir la pila de errores en caso de error
			e.printStackTrace();
		}


		int resultado;
		try {
			resultado = p.waitFor();
			System.out.println("El valor de salida es: " + resultado);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}