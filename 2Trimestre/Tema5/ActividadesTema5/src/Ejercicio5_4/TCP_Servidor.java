package Ejercicio5_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Servidor {
    public static void main(String[] args) {
        final int PUERTO = 5000; // Establezco el puerto donde el servidor escuchará las conexiones entrantes

        try (ServerSocket servidor = new ServerSocket(PUERTO)) { // Inicializo el ServerSocket para escuchar en el puerto 5000
            System.out.println("Servidor operativo. A la espera de conexión...");

            // Acepto la conexión del cliente
            Socket socketCliente = servidor.accept();
            System.out.println("Cliente conectado correctamente.");

            // Se configuran los flujos para recibir y enviar datos
            BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter flujoSalida = new PrintWriter(socketCliente.getOutputStream(), true);

            // Obtengo el número enviado por el cliente
            String numeroRecibido = flujoEntrada.readLine();
            System.out.println("Número recibido del cliente: " + numeroRecibido);

            // Convierte el número recibido a tipo entero y calculo su cuadrado
            int numero = Integer.parseInt(numeroRecibido);
            int cuadrado = numero * numero;

            // Envio el cuadrado del número al cliente
            System.out.println("Enviando el cuadrado del número al cliente: " + cuadrado);
            flujoSalida.println(cuadrado); // Envía el resultado al cliente

            // Cierro la conexión con el cliente
            socketCliente.close();
            System.out.println("Conexión con el cliente finalizada.");

        } catch (IOException | NumberFormatException e) { // Capturo errores en la conexión o al procesar el número
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
