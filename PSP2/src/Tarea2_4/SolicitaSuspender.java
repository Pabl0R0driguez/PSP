package Tarea2_4;

public class SolicitaSuspender {
    private boolean suspender; // Variable para indicar si el hilo está suspendido

    public synchronized void set(boolean b) {
        suspender = b; // Cambia el estado del objeto
        notifyAll(); // Notifica a todos los hilos en espera
    }

    public synchronized void esperandoParaRenaudar() throws InterruptedException {
        // Espera mientras suspender esté en true
        while (suspender) {
            System.out.println("Hilo en espera, ID: " + Thread.currentThread().getId());
            wait(); // Suspende el hilo hasta recibir una notificación
        }
    }
}

