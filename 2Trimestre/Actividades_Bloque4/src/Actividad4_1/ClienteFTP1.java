package Actividad4_1;

import java.net.SocketException;
import java.io.IOException;
import org.apache.commons.net.ftp.*;

public class ClienteFTP1 {

    public static void main(String[] args) throws IOException {
        FTPClient cliente = new FTPClient();
        String servFTP = "ftp.rediris.es";
        System.out.println("Nos conectamos a: " + servFTP);
        cliente.connect(servFTP);


        // Respuesta del servidor FTP
        System.out.println(cliente.getReplyString());

        // Código de respuesta
        int respuesta = cliente.getReplyCode();
        System.out.println("Respuesta: " + respuesta);

        // Comprobación del código de respuesta
        if (!FTPReply.isPositiveCompletion(respuesta)) {
            cliente.disconnect();
            System.out.println("FTP desconectado");
            System.exit(0);
        }

        // Desconexión del servidor FTP
        cliente.disconnect();
        System.out.println("FTP desconectado");

    }
}
