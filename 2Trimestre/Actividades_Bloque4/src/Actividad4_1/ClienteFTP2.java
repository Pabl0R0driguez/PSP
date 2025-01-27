package Actividad4_1;

import org.apache.commons.net.ftp.*;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClienteFTP2 {

    public static void main(String[] args) {
        // Definir la dirección del servidor FTP
        String servidor = "ftp.rediris.es"; // Servidor FTP
        // Especificar el puerto FTP estándar
        int puerto = 21; // Puerto FTP por defecto
        // Establecer el nombre de usuario para la conexión
        String usuario = "anonymous"; // Usuario (anónimo)
        // Establecer la contraseña para la conexión
        String contraseña = ""; // Contraseña (vacía para usuarios anónimos)

        // Crear una instancia del cliente FTP
        FTPClient clienteFTP = new FTPClient();

        try {
            // Conexión al servidor FTP
            clienteFTP.connect(servidor, puerto);
            int codigoRespuesta = clienteFTP.getReplyCode();
            // Intentar iniciar sesión con las credenciales proporcionadas
            if (!clienteFTP.login(usuario, contraseña)) {
                // Informar si el inicio de sesión falla
                System.out.println("Error: No se pudo iniciar sesión con el usuario proporcionado.");
                return;
            }

            // Confirmar que la conexión se ha establecido correctamente
            System.out.println("Éxito: Conexión establecida con el servidor FTP.");

            // Cambiar a modo pasivo y binario
            clienteFTP.enterLocalPassiveMode();
            clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);

            // Listar los archivos y directorios del directorio raíz
            System.out.println("Información: Listando directorios y archivos disponibles:");
            FTPFile[] archivos = clienteFTP.listFiles("/");
            if (archivos != null && archivos.length > 0) {
                for (FTPFile archivo : archivos) {
                    // Imprimir el nombre de cada archivo o directorio
                    System.out.println("- " + archivo.getName());
                }
            } else {
                // Informar si no se encontraron archivos o directorios
                System.out.println("Aviso: No se encontraron archivos o directorios en el servidor.");
            }
            // Cerrar la sesión FTP
            clienteFTP.logout();
            // Desconectar del servidor FTP
            clienteFTP.disconnect();
            // Confirmar que se ha desconectado correctamente
            System.out.println("Éxito: Desconectado del servidor FTP.");
        } catch (IOException ex) {
            // Manejar excepciones de entrada/salida
            System.out.println("Error: Ocurrió un problema durante la operación FTP.");
            System.out.println("Detalles del error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
