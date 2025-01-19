package Actividad4_1;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.net.SocketException;

public class ClietneFTP2 {
    public static void main(String[] args) {
        FTPClient cliente = new FTPClient();
        String servFTP = "ftp.rediris.es";
        System.out.println("Nos conoctamos a: " + servFTP);
        String usuario = "admin";
        String contraseña = "admin";


        try {
            cliente.connect(servFTP);
            cliente.enterLocalPassiveMode(); // Modo pasivo

            boolean login = cliente.login(usuario, contraseña);
            if (login) {
                System.out.println("Login correctamente");
            } else {
                System.out.println("Login incorrectamente");
                cliente.disconnect();
                System.out.println(1);
            }
            System.out.println("Directorio actual es: " + cliente.printWorkingDirectory());
            FTPFile[] files = cliente.listFiles();
            System.out.println("Lista de ficheros: " + files.length);

            // Array para visualizar el tipo de fichero
            String tipos[] = {"Fichero", "Directorio ", "Enlace simb."};
            for (int i = 0; i < files.length; i++) {
                System.out.println("\t" + files[i].getName() + " - " + " =>" + tipos[files[i].getType()]);
            }
            boolean logout = cliente.logout();
            if (logout) {
                System.out.println("Logout correctamente");
            } else {
                System.out.println("Logout incorrectamente");
            }

            //
            cliente.disconnect();

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
