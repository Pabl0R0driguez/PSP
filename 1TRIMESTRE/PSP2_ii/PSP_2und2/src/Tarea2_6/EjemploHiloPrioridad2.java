package Tarea2_6;

public class EjemploHiloPrioridad2 extends Thread {

    public EjemploHiloPrioridad2(String nombre){
        this.setName(nombre);
    }

    public void run() {
        System.out.println("Ejecutando  [" + getName() + "]");
        for (int i = 0; i < 5; i++) {
            System.out.println("\t(" + getName() + ": " + i + ")");
        }
    }
        public static void main(String[] args){
            EjemploHiloPrioridad2 hilo = new EjemploHiloPrioridad2("Uno");
            EjemploHiloPrioridad2 hilo2 = new EjemploHiloPrioridad2("Dos");
            EjemploHiloPrioridad2 hilo3 = new EjemploHiloPrioridad2("Tres");
            EjemploHiloPrioridad2 hilo4 = new EjemploHiloPrioridad2("Cuatro");
            EjemploHiloPrioridad2 hilo5 = new EjemploHiloPrioridad2("Cinco");

            //Asignamos prioridad
       hilo.setPriority(Thread.MIN_PRIORITY);
       hilo2.setPriority(3);
       hilo3.setPriority(Thread.NORM_PRIORITY);
       hilo4.setPriority(7);
       hilo5.setPriority(Thread.MAX_PRIORITY);

            //se ejecutan los hilos
            hilo.start();
            hilo2.start();
            hilo3.start();
            hilo4.start();
            hilo5.start();
        }
    }



