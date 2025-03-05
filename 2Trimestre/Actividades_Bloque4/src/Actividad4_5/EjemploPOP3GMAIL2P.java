package Actividad4_5;

import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

import java.io.BufferedReader;
import java.io.IOException;

public class EjemploPOP3GMAIL2P {
	public static void main(String[] args) throws  IOException  {

		String server = "pop.gmail.com",
		username = "pablorodseg@gmail.com",
		password = "hdpqardxqdojlniw\n";
		int puerto = 995;

		try {
			
			//CON gmail IMPLICITO
			POP3SClient pop3 = new POP3SClient(true);
			pop3.connect(server, puerto);
			
			System.out.println("Conexi�n realizada al servidor POP3 " + server);
			// nos logueamos
			if (!pop3.login(username, password))
				System.err.println("Error al hacer login");
			else {
				POP3MessageInfo[] men = pop3.listMessages();
				if (men == null)
					System.out.println("Imposible recuperar mensajes.");
				else {
					System.out.println("N� de mensajes: " + men.length);
					RecuperarMensajes(men, pop3);
				}
               
				//finalizar sesi�n
				pop3.logout();
			}
			//nos desconectamos
			pop3.disconnect();

		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}// main

	private static void RecuperarMensajes(POP3MessageInfo[] men, POP3SClient pop3)
			throws IOException {
		
		for (int i=0; i< men.length; i++) {
			System.out.println("Mensaje: " + (i+1));
			POP3MessageInfo msginfo = men[i]; //lista de mensajes
						
			//solo recupera cabecera
			System.out.println("Cabecera y Cuerpo del mensaje:");
			BufferedReader reader = 
			    (BufferedReader) pop3.retrieveMessage(msginfo.number);
						
			String linea;
			while ((linea = reader.readLine()) != null)
				System.out.println(linea.toString());
			reader.close();
			
		}//for				
	}//RecuperarMensajes
	
}// EjemploPOP3GMAIL