package Tarea2_10;

public class Produc_Consum {
    public static void main(String[] args) {
        // Cola compartida entre el productor y sus consumidores
        Cola cola = new Cola();

        // Productor 1
        Productor productor1 = new Productor(cola, 1);

        // Consumidores del productor 1
        Consumidor consumidor1 = new Consumidor(cola, 1);
        Consumidor consumidor2 = new Consumidor(cola, 2);

        // Iniciar los hilos
        productor1.start();
        consumidor1.start();
        consumidor2.start();
    }
}
