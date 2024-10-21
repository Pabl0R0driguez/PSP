package Tarea2_1;

public class Hilo2 extends Thread {
	public void run() {
		while (true) {
			try {
				System.out.println("TIC");
				Thread.sleep(3000);


			} catch (Exception e) {
				System.out.println("Hilo interrumpido");
			}
		}

	}
}