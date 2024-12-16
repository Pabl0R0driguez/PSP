package Tarea2_10;

public class PingCola {
    private String cadena;
    private boolean disponible = false;

    public synchronized String get() throws InterruptedException {
        while (!disponible) {
            wait();
        }
        disponible = false;
        notify();
        return cadena;
    }

    public synchronized void put(String valor) throws InterruptedException {
        while (disponible) {
            wait();
        }
        cadena = valor;
        disponible = true;
        notify();
    }
}
