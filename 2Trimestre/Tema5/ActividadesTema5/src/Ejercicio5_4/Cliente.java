package Ejercicio5_4;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // Asegúrate de que esto sea correcto
        int puerto = 1111; // Asegúrate de que este puerto coincida con el del servidor

        try {
            // Definimos el fichero almacén que contendrá el certificado y clave para acceder
            FileInputStream ficAlmacen = new FileInputStream("/home/pablo/keystore/Servidor.jks");
            String clave = "123456";

            // Cargar en un KeyStore el almacén que contiene el certificado
            KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
            almacen.load(ficAlmacen, clave.toCharArray());

            // Creamos el gestor de claves a partir del objeto KeyStore e inicializarlo con la clave del almacén
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(almacen);

            // Creación del contexto con soporte TLS
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            // Creación del socket SSL de cliente a partir del contexto
            SSLSocketFactory sfact = context.getSocketFactory();
            SSLSocket socket = (SSLSocket) sfact.createSocket(host, puerto);
            System.out.println("Conectado al servidor en " + host + ":" + puerto);

            // Entrada y salida de objetos
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            Scanner sc = new Scanner(System.in);

            System.out.println("Introduce un número mayor que 0: ");
            int numero = sc.nextInt();

            // Enviamos el objeto Numeros al servidor
            Numeros numeros = new Numeros(numero);
            salida.writeObject(numeros);

            // Si el número es mayor que 0
            if (numero > 0) {
                Numeros devuelve = (Numeros) entrada.readObject();
                System.out.println("Resultados recibidos del servidor:");
                System.out.println("Número: " + devuelve.getNumero());
                System.out.println("Cuadrado: " + devuelve.getCuadrado());
                System.out.println("Cubo: " + devuelve.getCubo());
            } else {
                System.out.println("El número debe ser mayor que 0");
            }

            socket.close(); // Cerrar el socket después de usarlo

        } catch (IOException | ClassNotFoundException | KeyStoreException | CertificateException |
                 NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
