package Actividad4_4;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

public class ClienteSMTP3 {
    public static void main(String[] args) {
        // Crear cliente SMTP seguro
        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();
        Scanner scanner = new Scanner(System.in);

        // Pedir datos del usuario y del servidor
        System.out.print("Introduce el servidor SMTP: ");
        String server = scanner.nextLine();
        System.out.print("Introduce el nombre de usuario (correo): ");
        String username = scanner.nextLine();
        System.out.print("Introduce la contraseña de aplicación: ");
        String password = scanner.nextLine();
        System.out.print("Introduce el puerto (por defecto 587): ");
        int puerto = Integer.parseInt(scanner.nextLine());
        System.out.print("Introduce el remitente (correo): ");
        String remitente = scanner.nextLine();

       // hdpqardxqdojlniw

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
                    System.out.print("Introduce el destinatario: ");
                    String destino1 = scanner.nextLine();
                    System.out.print("Introduce el asunto: ");
                    String asunto = scanner.nextLine();
                    System.out.print("Introduce el mensaje: ");
                    String mensaje = scanner.nextLine();

                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino1, "Asunto: " + asunto);
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
