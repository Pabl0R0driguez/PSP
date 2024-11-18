package Tarea2_9;

public class Productor extends Thread {

    //Definimos la clase Cola, usaremos sus metodos mas adelante
    private Cola cola;
    private int numero;

    public Productor(Cola cola, int numero) {
        this.cola = cola;
        this.numero = numero;
    }
    public void run() {
        for(int i = 0;i < 5; i++){
            //Añadimos 5 coca colas
            cola.put(i);
            System.out.println(i + " :Productor: " + numero + ", produce:  " + i);
            try{
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

