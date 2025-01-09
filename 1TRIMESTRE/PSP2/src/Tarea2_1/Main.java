package Tarea2_1;

public class Main {
    public static void main(String[] args) {
        // Crear dos hilos con las palabras "Tic" y "Tac"
        Hilo1 h1 = new Hilo1("Tic");
        Hilo1 h2 = new Hilo1("Tac");

        // Iniciar los hilos para que corran en paralelo
        h1.start();
        h2.start();
    }
}
