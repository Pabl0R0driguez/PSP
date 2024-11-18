package Tarea2_9;

public class Consumidor extends Thread {

    //Definimos la clase Cola, usaremos sus metodos mas adelante
    private Cola cola;
    private int numero;

    public Consumidor(Cola cola, int numero){
        this.cola = cola;
        this.numero = numero;
    }

    public void run(){
        int valor = 0;
        for(int i = 0; i < 5; i++){
            valor = cola.getCola(); //Guardamos en valor el número de coca colas existentes
            System.out.println(i + " :Consumidor: " + numero + ",consume " + valor);
        }
    }

}
