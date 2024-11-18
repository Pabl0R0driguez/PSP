package Tarea2_10;

public class Productor extends Thread {
    private Cola cola; // Cola compartida
    private int numero; // Identificador del productor

    public Productor(Cola cola, int numero) {
        this.cola = cola;
        this.numero = numero;
    }

    public void run() {
        for (int i = 0; i < 5; i++) { // Produce 5 números
            cola.put(i); // Produce un número
            System.out.println(i + " => Productor: " + numero + ", produce " + i);
            try {
                Thread.sleep(1000); // Simula tiempo de producción
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
