package Act3_9;

import java.io.*;
import java.net.*;

public class ServidorMultiCliente {

    public static void main(String[] args) {
        int puerto = 44444; // Puerto del servidor
        System.out.println("Servidor iniciado...");

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            while (true) {
                // Espera una conexión
                Socket cliente = servidor.accept();
                System.out.println("=>Conecta IP " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());

                // Manejo del cliente en un hilo
                new Thread(new ManejadorCliente(cliente)).start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

class ManejadorCliente implements Runnable {
    private Socket cliente;

    public ManejadorCliente(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        try {
            InputStream input = cliente.getInputStream();
            BufferedReader lector = new BufferedReader(new InputStreamReader(input));

            // Simula interacción
            String mensaje;
            while ((mensaje = lector.readLine()) != null) {
                System.out.println("Cliente dice: " + mensaje);
            }
        } catch (IOException e) {
            System.out.println("=>Desconecta IP " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());
        } finally {
            try {
                System.out.println("=>Desconecta IP " + cliente.getInetAddress() + ", Puerto remoto: " + cliente.getPort());
                cliente.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
