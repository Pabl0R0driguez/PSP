package Ejercicio2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;


// He entendido mal la actividad tarde, intentaba rellenar los datos de un profesor en el cliente,
// y luego mostrarlo en el servidor, el id del profesor avanza automáticamente, y simpolemente rellenariamos
// los datos. Me basado en un ejemplo que tenia de una actividad parecida

// NO FUNCIONA
public class ClienteProfesor {
    public static void main(String args[]) throws Exception {

        int Puerto = 1209;

        {
            MulticastSocket ms = null;
            try {
                ms = new MulticastSocket(Puerto);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            InetAddress grupo = null;
            try {
                grupo = InetAddress.getByName("230.230.230.230");
            } catch (UnknownHostException ex) {
                throw new RuntimeException(ex);
            }

            // Nos unimos al grupo
            try {
                ms.joinGroup(grupo);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            // Inicializamos el id de profesor
            int idprofesor = 1;
            // Mensaje general y lectura del primer profesor
            System.out.println("Programa cliente iniciado");
            System.out.println("Id profesor:" + idprofesor);


            // Recibe el paquete del servidor :
            byte[] buf = new byte[1000];
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);
            try {
                ms.receive(paquete);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            // Convierte el array de bytes a objeto (deserializa)
            Mensaje m = Mensaje.fromByteArray(paquete.getData());

            // mostramos datos
            System.out.println("Nombre: " + m.nombre);

            while (!m.nombre.trim().equals("*")) {

                // Mostrar el resto de datos en pantalla
                System.out.println("Especialidad: " + m.especialidad);
                System.out.println("Asignatura: " + m.asignatura);

                // Esperamos el siguiente mensaje
                try {
                    ms.receive(paquete);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                // Convierte el array de bytes a objeto (deserializa)
                m = Mensaje.fromByteArray(paquete.getData());


                // Incrementamos el contador de profesor
                idprofesor++;

                // Mostramos nombre del siguiente mensaje
                System.out.println("Id profesor:" + idprofesor);
                System.out.println("**** Nombre: " + m.nombre);

            }
            // Abandonamos el grupo
            try {
                ms.leaveGroup(grupo);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            // Cerramos el socket
            ms.close();
            System.out.println("Socket cerrado...");


        }

    }
}
