package Actividad4_6;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Actividad4_6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String username, password;

        // Bucle principal para la autenticación del usuario
        while (true) {
            System.out.print("Por favor, introduce tu nombre de usuario (* para salir): ");
            username = scanner.nextLine();

            // Salir del bucle si se introduce "*"
            if (username.equals("*")) {
                System.out.println("Cerrando el sistema. ¡Hasta la próxima!");
                break;
            }

            System.out.print("Por favor, ingresa tu contraseña: ");
            password = scanner.nextLine();

            // Verificar las credenciales en el servidor FTP
            if (authenticateUserOnFTP(username, password)) {
                System.out.println("¡Autenticación completada exitosamente! Bienvenido.");
                logUserSession(username, password);
            } else {
                System.out.println("Las credenciales son incorrectas. Se enviará una notificación al administrador.");
                notifyAdminOfFailure(username);
            }
        }

        scanner.close();
    }

    // Método para verificar las credenciales del usuario en el servidor FTP
    private static boolean authenticateUserOnFTP(String username, String password) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("localhost");
            boolean isAuthenticated = ftpClient.login(username, password);
            ftpClient.logout();
            ftpClient.disconnect();
            return isAuthenticated;
        } catch (IOException e) {
            System.err.println("Ocurrió un error al intentar autenticar en el servidor FTP: " + e.getMessage());
            return false;
        }
    }

    // Método para registrar la sesión del usuario en el servidor FTP
    private static void logUserSession(String username, String password) {
        FTPClient ftpClient = new FTPClient();

        try {
            // Conectar al servidor FTP
            ftpClient.connect("localhost");
            if (ftpClient.login(username, password)) {
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                // Crear la carpeta de registro si no existe
                String logDirectoryPath = "/" + username + "/LOG";
                if (!ftpClient.changeWorkingDirectory(logDirectoryPath)) {
                    ftpClient.makeDirectory(logDirectoryPath);
                    ftpClient.changeWorkingDirectory(logDirectoryPath);
                }

                // Preparar el contenido para el archivo de registro
                String logFileName = "LOG.TXT";
                String timestamp = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH).format(new Date());
                String logContent = "Se ha registrado la conexión del usuario:\nHora de conexión: " + timestamp + "\n\n";

                // Subir o actualizar el archivo LOG.TXT
                InputStream inputStream = new ByteArrayInputStream(logContent.getBytes());
                ftpClient.storeFile(logFileName, inputStream);
                inputStream.close();

                System.out.println("La conexión ha sido registrada en " + logDirectoryPath + "/" + logFileName);
            } else {
                System.out.println("La autenticación en el servidor FTP ha fallado. Por favor, intenta de nuevo.");
            }

            ftpClient.logout();
        } catch (IOException e) {
            System.err.println("Se produjo un error al intentar registrar la conexión: " + e.getMessage());
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException ex) {
                System.err.println("Hubo un problema al desconectar del servidor FTP: " + ex.getMessage());
            }
        }
    }

    // Método simulado para enviar una alerta por correo electrónico al administrador
    private static void notifyAdminOfFailure(String username) {
        System.out.println("[SIMULACIÓN] Se ha enviado una notificación al administrador: \"El usuario " + username + " ha intentado iniciar sesión con credenciales inválidas.\"");
    }
}
