package Tarea2_7;

public class MainHIlo {
    public static void main(String[] args) {
        //Instanciamos la clase contador
        Contador contador = new Contador(200);
        Hilo hilo;

        //Variable contador compartida por los 5 hilos
        for (int i = 0; i < 5; i++) {
            hilo = new Hilo(contador);
            hilo.start();
        }
        System.out.println("Valor final : " + contador.getContador());
    }
}