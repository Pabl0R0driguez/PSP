package Tarea2_10;

public class Produc_Consum {
    public static void main(String[] args) {

        System.out.println("\n2ª Parte: Ping Pong\n");
        PingCola cola2 = new PingCola();
        PingProductor productorPing = new PingProductor(cola2,1);
        PingConsumidor  consumidorPing = new PingConsumidor(cola2,2);



    }
}
