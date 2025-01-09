package Tarea2_10;

import static java.lang.Thread.sleep;

public class PingProductor {
    private PingCola cola;
    private int n;
    boolean continuar=true;

    public void detener() {
        continuar = false;
    }

    public PingProductor(PingCola cola, int n) {
        this.cola = cola;
        this.n = n;
    }

    public void run() {
        while (continuar) {
            try {
                System.out.println(cola.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(310);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
