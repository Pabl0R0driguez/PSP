//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Ejercicio1;

// Creamos la clase Saldo para gestionar los ingresos y reintegros de los clientes
public class Saldo {
    private int saldo ;

    public Saldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        // Establecemos la duración del sleep para obtener el saldo
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return this.saldo;
    }

    // Creamos el metodo setSaldo para actualzar nuestro saldo cada vez que haya un ingreso o un reintegro
    public void setSaldo(int saldo) {
        this.saldo = saldo;

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Metodo para ingresar saldo, le pasamos como parametros la cantidad y el nombre
    public synchronized void ingresarSaldo(int cantidad, String nombre) {
        System.out.println(nombre + ": Saldo inicial : " + this.getSaldo());
        // Si cuando hacemos un ingreso la cantidad supera los 700, nos mostrara un error
        if (this.getSaldo() + cantidad >= 700) {
            System.out.println("Error, no puedes superar esta cifra");
        } else {
            // Si no, actualizamos nuestro saldo
           this.setSaldo(this.getSaldo() + cantidad);
            System.out.println(nombre + " tiene un saldo de después del ingreso de :  " + this.getSaldo());
        }
    }

    // Metodo para restar saldo, le pasamos como parametros la cantidad y el nombre
    public synchronized void restarSaldo(int cantidad, String nombre) {
        System.out.println(nombre + ": Saldo inicial : " + this.getSaldo());
        // Si al restar, el salgo es negativo, nos aparecerá un mensaje de error
        if(this.getSaldo() - cantidad <= 0) {
            System.out.println("El saldo no puede ser negativo");
        }
        // Si todo es correcto se mostrara el saldo actualizado luego del reintegro
        else{
            this.setSaldo(this.getSaldo() - cantidad);
            System.out.println(nombre + " tiene un saldo de después del reintegro de :  " + this.getSaldo());

        }
    }
}
