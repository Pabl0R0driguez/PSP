package Ejercicio5_6;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {


        try {
            ACT5_6 login = new ACT5_6();

            // Creamos Subject que representa al usuario
            Subject subject = new Subject();
            // Creamos el CallbackHandler para obtener nombre y contraseña
            CallbackHandler callbackHandler = new MyCallbackHandler();
            // Inicializar la clase de login
            login.initialize(subject, callbackHandler, null, null);

            // Hacemos login
            if (login.login()) {
                System.out.println("Autenticación exitosa");
                // Permitir operaciones de lectura/escritura de archivos
                opciones();
            } else {
                System.out.println("Autenticación fallida");
            }
        } catch (LoginException e) {
            System.err.println("Error en la autenticación: " + e.getMessage());
        } catch (SecurityException se) {
            System.err.println("Error de seguridad: " + se.getMessage());
        }
    }

    private static void opciones() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Elige una opción: leer, escribir o salir ");
            String accion = br.readLine().trim().toLowerCase();

            while (!accion.equals("salir")) {
                if (accion.equals("leer")) {
                    System.out.print("Introduce el nombre del archivo a leer: ");
                    String nombreArchivo = br.readLine();
                    leerArchivo(nombreArchivo);
                } else if (accion.equals("escribir")) {
                    System.out.print("Introduce el nombre del archivo a escribir: ");
                    String nombreArchivo = br.readLine();
                    System.out.print("Introduce el contenido a escribir: ");
                    String contenido = br.readLine();
                    escribirArchivo(nombreArchivo, contenido);
                } else {
                    System.out.println("Acción no válida. Intenta de nuevo.");
                }

                System.out.println("Elige una opción: leer, escribir o salir ");
                accion = br.readLine().trim().toLowerCase();
            }
        } catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
    }

    private static void leerArchivo(String nombreArchivo) {

        Path path = Paths.get(nombreArchivo);
        try {
            // Leer el contenido del archivo
            String contenido = Files.readString(path);
            System.out.println("Contenido del archivo:");
            System.out.println(contenido);
        } catch (IOException e) {
            System.err.println("No se pudo leer el archivo: " + e.getMessage());
        }
    }

    private static void escribirArchivo(String nombreArchivo, String contenido) {
        Path path = Paths.get(nombreArchivo);
        try {
            // Escribir el contenido en el archivo
            Files.writeString(path, contenido);
            System.out.println("Contenido escrito en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("No se pudo escribir en el archivo: " + e.getMessage());
        }
    }
}
