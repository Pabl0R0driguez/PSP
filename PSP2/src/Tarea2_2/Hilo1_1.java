package Tarea2_2;

public class Hilo1_1 implements Runnable {
	public void run() {

		try {
			System.out.println("Hola mundo + " + "id: " + Thread.currentThread().getId());
			Thread.sleep(3000);

		}catch (Exception e){
			System.out.println("Hilo interrumpido");
		}

	}
}

