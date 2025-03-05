package Ejercicio1;

// Creams la clase persona para realizar las operaciones del programa
public class Persona extends Thread{
    Saldo saldo;
    private int cantidad;
    private String nombre;

    public Persona(  String nombre,Saldo saldo,int cantidad) {
        this.saldo = saldo;
        this.cantidad = cantidad;
        this.nombre = nombre;

    }

    public void run() {
        this.saldo.ingresarSaldo(this.cantidad, this.nombre);
        this.saldo.restarSaldo(this.cantidad, this.nombre);

        this.saldo.ingresarSaldo(this.cantidad, this.nombre);
        this.saldo.restarSaldo(this.cantidad, this.nombre);

        try {
            sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
