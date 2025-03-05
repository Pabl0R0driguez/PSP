package Ejercicio5_4;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 1111; // Cambia este número si quieres usar otro puerto.

        try {
            // Definimos el fichero almacén que contendrá el certificado y clave para acceder
            FileInputStream ficAlmacen = new FileInputStream("/home/pablo/keystore/Servidor.jks");
            String clave = "123456";

            // Cargar en un KeyStore el almacén que contiene el certificado
            KeyStore almacen = KeyStore.getInstance(KeyStore.getDefaultType());
            almacen.load(ficAlmacen, clave.toCharArray());

            // Creamos el gestor de claves a partir del objeto KeyStore e inicializarlo con la clave del almacén
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(almacen, clave.toCharArray());

            // Creación del contexto con soporte TLS
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(kmf.getKeyManagers(), null, null);

            // Creación del socket SSL de servidor a partir del contexto
            SSLServerSocketFactory ssf = context.getServerSocketFactory();
            SSLServerSocket servidorSSL = (SSLServerSocket) ssf.createServerSocket(puerto);
            System.out.println("Servidor conectado en el puerto: " + puerto);

            while (true) {
                // Espera una conexión
                SSLSocket socket = (SSLSocket) servidorSSL.accept();
                System.out.println("Cliente conectado: " + socket.getInetAddress());

                // Entrada y salida de objetos
                ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());

                // Recoge el objeto Numeros del cliente
                Numeros n = (Numeros) entrada.readObject();

                if (n.getNumero() <= 0) {
                    System.out.println("El número debe ser mayor que 0. Servidor desconectado.");
                    break;
                }

                // Calculamos el cuadrado y cubo del número
                int numero = n.getNumero();
                n.setCuadrado((long) numero * numero);
                n.setCubo((long) numero * numero * numero);

                // Enviamos el objeto modificado al cliente
                salida.writeObject(n);
                System.out.println("Servidor enviado: " + n.getCuadrado() + " " + n.getCubo());
                socket.close(); // Cerrar el socket después de procesar la solicitud
            }
        } catch (IOException | ClassNotFoundException | KeyStoreException | CertificateException |
                 NoSuchAlgorithmException | UnrecoverableKeyException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
