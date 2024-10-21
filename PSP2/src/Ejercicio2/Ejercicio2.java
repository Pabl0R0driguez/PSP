package Ejercicio2;

public class Ejercicio2 implements Runnable{
    private String cadena;
    public Ejercicio2(String cadena){
        this.cadena = cadena;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(Thread.currentThread().getId() * 1000);
            System.out.println("Hola mundo: " + cadena + Thread.currentThread().getId());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
		System.out.println("Hilo interrumpido");

    }
}
