package Tarea2_8;

public class Saldo {
    private int saldo;

    public Saldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo(){
        try {
            //Aplicamos un sleep que durará entre 1 y 1000
            Thread.sleep((long) (Math.random() * 1000 + 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return saldo;
    }

    public void setSaldo(int saldo){
        this.saldo = saldo;
        try {
            //Aplicamos un sleep que durará entre 1 y 1000
            Thread.sleep((long) (Math.random() * 1000 + 1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //Obtenemos el saldo actual y le añadimos el ingreso hecho
    public synchronized void ingresarSaldo(int cantidad, String nombre){

        System.out.println(nombre + ": Saldo inicial : " + getSaldo());
        setSaldo(getSaldo() + cantidad);
        System.out.println(nombre + " tiene un saldo de después del ingreso de :  " + getSaldo());
    }



}
