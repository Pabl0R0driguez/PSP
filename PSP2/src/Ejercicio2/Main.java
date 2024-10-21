package Ejercicio2;

public class Main {
    public static void main(String[] args) {

		for(int i = 0;i<5 ; i++) {
			Runnable h1 = new Ejercicio2("Hilo ");
			Thread hilo1 = new Thread(h1);
			hilo1.start();

		}


    }
}