package Actividad4_1;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class ClienteFTP1 {
    public static void main(String[] args) {
        // Definir la dirección del servidor FTP
        String servidor = "localhost"; // Servidor FTP
        // Especificar el puerto FTP estándar
        int puerto = 21; // Puerto FTP por defecto
        // Establecer el nombre de usuario para la conexión
        String usuario = "usuario"; // Usuario configurado
        // Establecer la contraseña para la conexión
        String contraseña = "usuario"; // Contraseña configurada

        // Crear una instancia del cliente FTP
        FTPClient clienteFTP = new FTPClient();

        try {
            // Establecer conexión con el servidor FTP
            clienteFTP.connect(servidor, puerto);
            // Intentar iniciar sesión con las credenciales proporcionadas
            if (!clienteFTP.login(usuario, contraseña)) {
                // Informar si el inicio de sesión falla
                System.out.println("Login fallido.");
                return;
            }

            // Confirmar que la conexión se ha establecido correctamente
            System.out.println("Conectado al servidor.");

            // Mostrar archivos y carpetas en el directorio especificado
            System.out.println("Listando archivos:");
            var archivos = clienteFTP.listFiles("/home/usuario");
            for (var archivo : archivos) {
                // Imprimir el nombre de cada archivo o directorio
                System.out.println(archivo.getName());
            }

            // Cerrar la sesión FTP
            clienteFTP.logout();
            // Confirmar que la sesión se ha cerrado correctamente
            System.out.println("Sesión cerrada correctamente.");
        } catch (IOException ex) {
            // Manejar excepciones de entrada/salida
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                // Verificar si el cliente FTP sigue conectado
                if (clienteFTP.isConnected()) {
                    // Desconectar del servidor FTP
                    clienteFTP.disconnect();
                    // Confirmar que la conexión se ha cerrado
                    System.out.println("Conexión cerrada.");
                }
            } catch (IOException ex) {
                // Manejar excepciones al intentar desconectar
                ex.printStackTrace();
            }
        }
    }
}
