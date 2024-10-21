package Ejercicio1;

public class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            Ejercicio1 hilo1 = new Ejercicio1("Hola mundo " + i);
            hilo1.start();

        }
    }
}
