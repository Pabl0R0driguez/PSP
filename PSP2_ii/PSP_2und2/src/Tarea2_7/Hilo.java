package Tarea2_7;

public class Hilo extends Thread {

    //Instanciamos la clase Contador para llamar posteriormente a su metodo incrementar
    private Contador contador;

    public Hilo(Contador contador) {
        this.contador = contador;
    }

    //Usamos el synchronized para bloquear el acceso a la variable contador para el resto de hilos hasta que finalize
    public void run() {
        synchronized (contador) {
            for (int i = 0; i < 10; i++) {
                contador.incrementar();
                System.out.println(currentThread().getName() + " " + contador.getContador());
                try {
                    sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

