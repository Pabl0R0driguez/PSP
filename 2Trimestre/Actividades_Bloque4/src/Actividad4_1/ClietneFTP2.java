package Actividad4_1;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class ClietneFTP2 {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String server = "127.0.0.1";
        String user = "usuario";
        String pass = "usuario";

        try {
            // Conectar al servidor
            cliente.connect(server);
            System.out.println("Conectado a: " + server);

            // Intentar iniciar sesión
            boolean login = cliente.login(user, pass);
            if (!login) {
                System.out.println("Login incorrecto");
                return; // Salir si el login falla
            }

            // Obtener y mostrar el directorio de trabajo
            String workingDirectory = cliente.printWorkingDirectory();
            System.out.println("Directorio de trabajo: " + workingDirectory);

            // Realiza otras operaciones, como listar archivos o subir archivos...

        } catch (IOException ex) {
            ex.printStackTrace(); // Mostrar el error si ocurre
        } finally {
            try {
                // Cerrar la conexión
                if (cliente.isConnected()) {
                    cliente.logout();
                    cliente.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
