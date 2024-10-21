package Tarea2_1;

public class Main {
    public static void main(String[] args) {
        // Crear dos hilos con las palabras "Tic" y "Tac"
      Thread hilo1 = new Hilo1();
	  Thread hilo2 = new Hilo2();


		// Iniciar los hilos para que corran en paralelo
        hilo1.start();
        hilo2.start();
    }
}
