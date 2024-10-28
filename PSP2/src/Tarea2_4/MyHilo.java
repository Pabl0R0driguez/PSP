package Tarea2_4;

public class MyHilo extends Thread {
    private SolicitaSuspender suspender = new SolicitaSuspender();
    private int contador = 0; // Contador que incrementa de uno en uno
    private boolean activo = true; // Variable de control para finalizar el hilo

    public void suspende() {
        suspender.set(true); // Suspende el hilo
    }

    public void reanuda() {
        suspender.set(false); // Reanuda el hilo
    }

    public int getContador() {
        return contador; // Devuelve el valor actual del contador
    }

    public void finalizar() {
        activo = false; // Cambia a false para detener el hilo
        this.interrupt(); // Interrumpe el hilo para que salga del estado de espera
    }

    @Override
    public void run() {
        while (activo) { // Corre mientras el hilo esté activo
            try {
                suspender.esperandoParaRenaudar(); // Verifica si debe suspenderse
                contador++; // Incrementa el contador
                System.out.println("Contador: " + contador);
                Thread.sleep(1000); // Espera 1 segundo
            } catch (InterruptedException e) {
                // Captura la interrupción si el hilo está esperando
                if (!activo) break; // Sale del bucle si se solicitó detener el hilo
            }
        }
    }
}
