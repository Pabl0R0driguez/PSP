package Act3_7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 44444;
        try{
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor conectado: " + puerto);
            while(true){
                Socket socket = servidor.accept(); // Espera una conexión
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                // Entrada y salida de objetos
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());

                // Recoge el objeto Numeros del cliente
                Numeros n = (Numeros) entrada.readObject();

                if(n.getNumero() <= 0){
                    System.out.println("Servidor desconectado");
                    break;
                }

                // Calculamos el cuadrado del número
                int numero = n.getNumero();
                n.setCuadrado((long) numero * numero);
                n.setCubo((long) numero * numero * numero);

                // Enviamos el objeto modificado al cliente
                saida.writeObject(n);
                System.out.println("Servidor enviado: " + n.getCuadrado() + " " + n.getCubo() + " " + n.getCubo());
                socket.close();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
