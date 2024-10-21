package Ejercicio2;

public class Main {
    public static void main(String[] args) {
        Runnable h1 = new Ejercicio2("Hilo 1 -");
        Thread hilo1 = new Thread(h1);

        Runnable h2 = new Ejercicio2("Hilo 2 -");
        Thread hilo2 = new Thread(h2);

        Runnable h3 = new Ejercicio2("hilo 3 -");
        Thread hilo3 = new Thread(h3);

        Runnable h4 = new Ejercicio2("hilo 4 -");
        Thread hilo4 = new Thread(h4);

        Runnable h5 = new Ejercicio2("hilo 5 -");
        Thread hilo5 = new Thread(h5);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

    }
}