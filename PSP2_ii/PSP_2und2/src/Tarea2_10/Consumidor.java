package Tarea2_10;

public class Consumidor extends Thread {
    private Cola cola; // Cola compartida
    private int numero; // Identificador del consumidor

    public Consumidor(Cola cola, int numero) {
        this.cola = cola;
        this.numero = numero;
    }

    public void run() {
        for (int i = 0; i < 5; i++) { // Consumidor ejecuta 5 iteraciones
            int valor = cola.getCola(numero); // Consume el número asignado
            System.out.println(i + " => Consumidor: " + numero + ", consume " + valor);
            try {
                Thread.sleep(1000); // Simula tiempo de consumo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
