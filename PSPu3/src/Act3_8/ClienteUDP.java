package Act3_8;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {

            // Dirección y puerto
            InetAddress direccion = InetAddress.getByName("127.0.0.1");
            int puerto = 44444;

            // Creamos un socket UDP
            DatagramSocket socket = new DatagramSocket();

            // Creamos el objeto Persona
            Personas persona = new Personas("Pablo", 19);
            System.out.println(persona);

            // Convertimos a bytes nuestro objeto Persona
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(persona);
            oos.close();
            byte[] enviados_datos = baos.toByteArray();

            // Enviamos datos al servidor
            DatagramPacket enviado_paquete = new DatagramPacket(enviados_datos, enviados_datos.length, direccion, puerto);
            socket.send(enviado_paquete);

            // Objetos modificados, recibir
            byte[] buffer_recibido = new byte[1024];
            DatagramPacket paquete_recibido = new DatagramPacket(buffer_recibido, buffer_recibido.length);
            socket.receive(paquete_recibido);

            // Convertir lso bytes recibidos a tipo objeto
            ByteArrayInputStream bais = new ByteArrayInputStream(paquete_recibido.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Personas persona_modificada = (Personas) ois.readObject();
            System.out.println("Persona modificada: " + persona_modificada);
            ois.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}