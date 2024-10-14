package Tarea2_2;

public class Hilo1_1 implements Runnable{

    String palabra;
    public Hilo1_1(String palabra){
        this.palabra = palabra;
    }

    public void run(){

        System.out.println(palabra);

        try {
            // Pausa de 750 milisegundos entre cada impresión
            Thread.sleep(750);
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }
}

