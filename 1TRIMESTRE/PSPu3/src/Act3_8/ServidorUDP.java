package Act3_8;

import java.io.*;
import java.net.*;

public class ServidorUDP {
    public static void main(String[] args) {
        int puertoServidor = 44444;

        try {
            // Crear socket UDP
            DatagramSocket socket = new DatagramSocket(puertoServidor);
            System.out.println("Servidor UDP esperando en el puerto " + puertoServidor);

            while (true) {
                // Recibir el objeto del cliente
                byte[] bufferRecibido = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(bufferRecibido, bufferRecibido.length);
                socket.receive(paqueteRecibido);

                // Convertir bytes a objeto
                ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Personas persona = (Personas) ois.readObject();
                ois.close();
                System.out.println("Objeto recibido: " + persona);

                // Modificar el objeto
                persona.setNombre(persona.getNombre() + " Modificado");
                persona.setEdad(persona.getEdad() + 5);

                // Convertir objeto modificado a bytes
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(persona);
                oos.close();  // Cerrar stream
                byte[] datosEnviados = baos.toByteArray();

                // Enviar el objeto modificado al cliente
                InetAddress direccionCliente = paqueteRecibido.getAddress();
                int puertoCliente = paqueteRecibido.getPort();
                DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length, direccionCliente, puertoCliente);
                socket.send(paqueteEnviado);

                System.out.println("Objeto modificado enviado: " + persona);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
