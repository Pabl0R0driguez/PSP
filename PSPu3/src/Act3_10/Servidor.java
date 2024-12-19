package Act3_10;

import java.io.*;
import java.net.*;

public class Servidor {
    private static final int PUERTO = 55555;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado, esperando jugadores...");

            while (true) {
                Socket socketCliente = servidor.accept();
                new Thread(new ManejadorDeCliente(socketCliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}