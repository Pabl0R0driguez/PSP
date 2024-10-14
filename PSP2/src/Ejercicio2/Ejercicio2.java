package Ejercicio2;

public class Ejercicio2 implements Runnable{
    private String cadena;
    public Ejercicio2(String cadena){
        this.cadena = cadena;
    }
    @Override
    public void run() {
        System.out.println("Hola mundo " + cadena);
    }
}
