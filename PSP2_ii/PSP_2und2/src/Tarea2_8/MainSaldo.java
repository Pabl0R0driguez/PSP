package Tarea2_8;

public class MainSaldo {
    public static void main(String[] args) {
        //Generamos un saldo inicial entre 1 y 1000
        Saldo saldo  = new Saldo((int) (Math.random() * 1000 + 1));

        Hilo hilo = new Hilo("Pablo" , saldo , 500);
        Hilo hilo2 = new Hilo("Pepe" , saldo , 200);
        Hilo hilo3 = new Hilo("Juan" , saldo , 600);
        Hilo hilo4 = new Hilo("Jose" , saldo , 100);

        hilo.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();


    }
}
