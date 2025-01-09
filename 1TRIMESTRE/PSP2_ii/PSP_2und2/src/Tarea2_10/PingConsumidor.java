package Tarea2_10;

import static java.lang.Thread.sleep;

public class PingConsumidor     {
    private PingCola cola;
    private int n;
    private boolean continuar = true;

    public void detener() {
        continuar = false;
    }
    public PingConsumidor(PingCola cola, int n) {
        this.cola = cola;
        this.n = n;
    }
    public void dormir(){
        try {
            sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        while (continuar) {
            try {
                cola.put("Ping");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dormir();
            try {
                cola.put("pong");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        dormir();
    }
}
