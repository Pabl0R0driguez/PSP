package Tarea2_8;

public class Hilo extends Thread {

    Saldo saldo;
    private String nombre;
    private int cantidad;

    public Hilo(String nombre, Saldo saldo, int cantidad) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.cantidad = cantidad;
    }
    public void run() {

                saldo.ingresarSaldo(cantidad,nombre);
                try {
                    sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


