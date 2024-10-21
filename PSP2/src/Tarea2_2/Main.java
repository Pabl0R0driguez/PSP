package Tarea2_2;

public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {

            Runnable hilo = new Hilo1_1();
            hilo.run();

        }
    }
}