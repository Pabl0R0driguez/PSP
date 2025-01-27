package Actividad4_4;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;

public class ClienteSMTP3 {
    public static void main(String[] args) {
        // Crear cliente SMTP seguro
        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        // Datos del usuario y del servidor
        String server = "smtp.gmail.com";
        String username = "tu_correo@gmail.com"; // Cambia por tu correo
        String password = "contraseña_de_aplicacion"; // Usa contraseña de aplicación
        int puerto = 587;
        String remitente = "tu_correo@gmail.com"; // Cambia por tu correo

        try {
            int respuesta;

            // Conectar al servidor SMTP
            client.connect(server, puerto);
            System.out.println("1 - " + client.getReplyString());

            // Establecer clave para comunicación segura
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];
            client.setKeyManager(km);

            respuesta = client.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.out.println("Error de conexión");
                return;
            }

            // Enviar comando EHLO
            client.ehlo(server);
            System.out.println("2 - " + client.getReplyString());

            // Negociar TLS
            if (client.execTLS()) {
                System.out.println("3 - " + client.getReplyString());

                // Autenticación
                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
                    System.out.println("4 - " + client.getReplyString());

                    // Enviar mensaje
                    String destino1 = "destino@gmail.com"; // Cambia por el destino
                    String mensaje = "Este es un correo de prueba.\nSaludos.";

                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino1, "Asunto: Prueba SMTP");
                    client.setSender(remitente);
                    client.addRecipient(destino1);
                    System.out.println("5 - " + client.getReplyString());

                    Writer writer = client.sendMessageData();
                    if (writer != null) {
                        writer.write(cabecera.toString());
                        writer.write(mensaje);
                        writer.close();
                        System.out.println("6 - " + client.getReplyString());

                        boolean exito = client.completePendingCommand();
                        System.out.println("7 - " + client.getReplyString());

                        if (exito) {
                            System.out.println("MENSAJE ENVIADO CON ÉXITO...");
                        } else {
                            System.out.println("FALLO AL FINALIZAR TRANSACCIÓN");
                        }
                    } else {
                        System.out.println("FALLO AL ENVIAR DATA");
                    }
                } else {
                    System.out.println("USUARIO NO AUTENTICADO");
                }
            } else {
                System.out.println("FALLO AL EJECUTAR STARTTLS");
            }

            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            client.disconnect();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fin de envío");
        System.out.println(0);
    }
}
