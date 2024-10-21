package Ejercicio3;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainHilo {
	public static void main(String[] args) throws IOException {
		{
			for(int i = 0;i<args.length;i++){
				Thread hilo = new Ejercicio3(args[i]);
				hilo.start();
			}
		}
	}

}
