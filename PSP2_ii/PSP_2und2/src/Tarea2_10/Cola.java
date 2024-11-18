package Tarea2_10;

public class Cola {
    private int numero; // Número producido
    private boolean disponible = false; // Marca si hay un número disponible
    private int turno = 1; // Alterna entre el primer y el segundo consumidor

    // Método para que los consumidores obtengan un número
    public synchronized int getCola(int consumidorID) {
        while (!disponible || turno != consumidorID) { // Espera su turno
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        disponible = false; // Marca que el número fue consumido
        turno = (turno == 1) ? 2 : 1; // Cambia el turno entre los consumidores
        notifyAll(); // Notifica al productor o al siguiente consumidor
        return numero;
    }

    // Método para que el productor coloque un número
    public synchronized void put(int valor) {
        while (disponible) { // Espera si ya hay un número disponible
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        numero = valor; // Asigna el número producido
        disponible = true; // Marca que hay un número disponible
        notifyAll(); // Notifica a los consumidores
    }
}
