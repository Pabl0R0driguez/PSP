package Ejercicio3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainHilo {
	private static long t_comienzo;
	private static long t_final;

	//Con dos hilos
	public static void main(String[] args) throws IOException {
		//Inicio del proceso
		t_comienzo = System.currentTimeMillis();
		{
			for(int i = 0;i<args.length;i++){
				Thread hilo = new Ejercicio3(args[i]);
				hilo.start();
			}

			//Inicio del proceso
			t_final = System.currentTimeMillis();
			System.out.println("El proceso ha tardado: " + (t_final - t_comienzo) + " milisengundos");

		}
	}

}
