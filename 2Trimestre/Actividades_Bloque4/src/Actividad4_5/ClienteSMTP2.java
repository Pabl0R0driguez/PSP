package Actividad4_5;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import java.io.IOException;
import java.io.Writer;

// Envio mensaje servidor local

public class ClienteSMTP2 {

	public static void main(String[] args)  {
		SMTPClient client= new SMTPClient();
		 try {
		      int respuesta;
			 // CONECTAMOS CON EL SERVIDOR y obtenemos código y mensaje de respuesta
		      client.connect("localhost");   //se conecta al servidor de correo
		      System.out.print(client.getReplyString());
		      respuesta = client.getReplyCode();

		      if(!SMTPReply.isPositiveCompletion(respuesta)) {
		        client.disconnect();
		        System.err.println("SMTP server refused connection.");
		        System.exit(1);
		      }
			 // Nos presentamos antes el servidor
		      client.login();  //inicio de sesión -HELO

			 // Se componen variables para los distintos campos del mensaje
		      String remitente ="usuario@pablo2dam";
		      String destino1="usuario@pablo2dam";
		      //String destino2="usuario2@servidorcorreo";
		      String asunto="prueba de prog java ";
		      String mensaje = "Hola, usando SimpleSMTPHeader.";
		      
		      //se crea la cabecera: from, to, subject
		      SimpleSMTPHeader cabecera = new SimpleSMTPHeader (remitente , destino1, asunto);
		     // cabecera.addCC(destino2);

			 // se envía el remitente. Equivale a enviar el comando MAIL FROM:
		      client.setSender(remitente);

			 // se envía los destinatarios. Equivale a enviar el comando RCPT TO:
		      client.addRecipient(destino1);//hay que a�adir los dos
		   //   client.addRecipient(destino2);

			 // Se envía el inicio del mensaje,es decir, el comando DATA
		      Writer writer = client.sendMessageData();   
		      if(writer == null) { //fallo	       
		    	  System.out.println("FALLO AL ENVIAR DATA.");			     
			      System.exit(1);
		      }

			 // Envíamos cabecera, en primer lugar, después el mensaje, y por último cerramos la sesión con QUIT
		      System.out.println(cabecera.toString());
		      writer.write(cabecera.toString()); //primero escribo cabecera    
	          writer.write(mensaje);//luego mensaje
	          writer.close();
	          
	       	  if(!client.completePendingCommand())  { //fallo
	       		  System.out.println("FALLO AL FINALIZAR LA TRANSACCI�N.");			     
			      System.exit(1);
		      }

			 // se envía el comamndo QUIT
	       	  client.logout(); 
	          client.disconnect();
	          
		    } catch (IOException e) {
				System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");
				e.printStackTrace();
				System.exit(2);
			}
		    
		    System.exit(0);
		}
}//..