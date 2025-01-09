package Act3_9;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int puerto = 44444;

        try (Socket socket = new Socket(host, puerto)) {
            System.out.println("Conectado al servidor.");
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            salida.println("Hola, servidor!");
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
