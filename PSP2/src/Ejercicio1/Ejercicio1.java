package Ejercicio1;

public class Ejercicio1 extends Thread{

    String palabra;
    public Ejercicio1(String palabra){
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

