package Tarea2_1;

public class Hilo1 extends Thread {
    private String palabra;
    private boolean conexion = true;

    public Hilo1(String palabra) {
        this.palabra = palabra;
    }

    public void run() {
        while (conexion) {
            for (int i = 0; i <= 10; i--) {

                System.out.println(palabra);

                try {
                    // Pausa de 750 milisegundos entre cada impresión
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    System.out.println("Hilo interrumpido");
                }
            }
        }
    }
}
