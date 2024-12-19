package Act3_10;

import java.io.*;
import java.net.*;

public class ManejadorDeCliente implements Runnable {
    private Socket socket;
    private BufferedReader lector;
    private PrintWriter escritor;
    private static final int NUMERO_A_ADIVINAR = 15;

    public ManejadorDeCliente(Socket socket) {
        this.socket = socket;
        try {
            this.lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.escritor = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String mensaje;
            escritor.println("Bienvenido al juego, adivina el número entre 1 y 25.");
            while ((mensaje = lector.readLine()) != null) {
                if (mensaje.equals("*")) {
                    escritor.println("Juego terminado. ¡Gracias por jugar!");
                    socket.close();
                    return;
                }

                try {
                    int numeroAdivinado = Integer.parseInt(mensaje);

                    if (numeroAdivinado == NUMERO_A_ADIVINAR) {
                        escritor.println("¡Correcto!");
                        socket.close();
                        return;
                    } else if (numeroAdivinado < NUMERO_A_ADIVINAR) {
                        escritor.println("Número demasiado pequeño.");
                    } else {
                        escritor.println("Número demasiado grande.");
                    }
                } catch (NumberFormatException e) {
                    escritor.println("Por favor, ingresa un número válido.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}