package Act1_6;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Actividad1_6 {
	public static void main(String[] args) throws IOException {

File directorio= new File("out/production/A1_PSP");
ProcessBuilder pb = new ProcessBuilder("java", "Act1_6.EjemploLectura");
pb.directory(directorio);
Process p = pb.start();

//OutputStream, envía los argumentos al Process
		try {
			OutputStream out = p.getOutputStream();
			out.write("5\n".getBytes());
			out.write("6\n".getBytes());
			out.flush();

		}catch (Exception e){
			//Para imprimir la pila de errores en caso de error
			e.printStackTrace();
		}


//InputStream, recoge la salida del Process(resultado)
	try {
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

