package Ejercicio5_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        final String DIRECCION_SERVIDOR = "127.0.0.1"; // Especifico la dirección IP del servidor
        final int PUERTO_SERVIDOR = 5000; // Establezco el puerto donde el servidor estará esperando conexiones

        try (Socket conexion = new Socket(DIRECCION_SERVIDOR, PUERTO_SERVIDOR)) { // Intento establecer una conexión con el servidor
            System.out.println("Conexión con el servidor establecida.");

            // Se configuran los flujos de entrada y salida para la comunicación
            PrintWriter flujoSalida = new PrintWriter(conexion.getOutputStream(), true);
            BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

            // Solicito al usuario que ingrese un número
            Scanner scanner = new Scanner(System.in);
            System.out.print("Por favor, ingresa un número entero: ");
            int numero = scanner.nextInt(); // Leo el número entero proporcionado por el usuario

            // Envío el número al servidor
            System.out.println("Enviando número al servidor: " + numero);
            flujoSalida.println(numero);

            // Leo la respuesta del servidor (el cuadrado del número)
            String respuestaServidor = flujoEntrada.readLine();
            System.out.println("Respuesta del servidor (cuadrado del número): " + respuestaServidor);

        } catch (IOException error) { // Si ocurre un error durante la conexión o comunicación
            System.out.println("Se ha producido un error en el cliente: " + error.getMessage());
        }
    }
}
