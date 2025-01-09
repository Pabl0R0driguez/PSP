package Act3_7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    String host = "127.0.0.1";
    int puerto = 44444;

    Socket socket = new Socket(host, puerto);

    // Entrada y salida de objetos
    ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
    ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
    Scanner sc = new Scanner(System.in);

    System.out.println("Introduce un número mayor que 0: ");
    int numero = sc.nextInt();

    // Enviamos el objeto Numeros al servidor
    Numeros numeros = new Numeros(numero);
    salida.writeObject(numeros);

    // Si el número es mayor que 0 =
    if (numero > 0){
         Numeros devuelve = (Numeros) entrada.readObject();
        System.out.println("Resultados recibidos del servidor:");
        System.out.println("Número: " + devuelve.getNumero());
        System.out.println("Cuadrado: " + devuelve.getCuadrado());
        System.out.println("Cubo: " + devuelve.getCubo());
    } else {
        System.out.println("El numero debe ser mayor que 0");
    }





    }
}