package Ejercicio1;

public class Ejercicio1 extends Thread {
	public void run() {

			try {
				System.out.println("Hola mundo + " + "id: " + Thread.currentThread().getId());
				Thread.sleep(3000);

			}catch (Exception e){
				System.out.println("Hilo interrumpido");
			}

		}
	}

