package Ejercicio2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.io.*;
import java.net.*;

import static java.lang.System.in;


public class Servidor {
    public static void main(String args[]) throws Exception {

        // Flujo entrada
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // Creamos el socket multicast
        MulticastSocket ms = new MulticastSocket();        int Puerto = 1209;
        InetAddress grupo = InetAddress.getByName("230.230.230.230");

        String nombre="", especialidad = "", asignatura = "";
        int idprofesor =  1 ;

        System.out.println("Servidor iniciado");
        System.out.println("id:" + idprofesor);
        System.out.println("nombre:" + nombre);
        System.out.println("Asignatura: ");

        asignatura= String.valueOf(in.read());
        // Leemos el resto de datos del profesor
        System.out.println("Tipo de especialidad: ");
        especialidad= String.valueOf(in.read());


            // Composición de un objeto con los datos leído y conversión a array de bytes
            Mensaje m = new Mensaje (nombre,especialidad,asignatura);
            byte[] m_arraybytes = m.toByteArray();

            // Aqui enviariamos los datos al grupo
            DatagramPacket paquete = new DatagramPacket(m_arraybytes, m_arraybytes.length, grupo, Puerto);
            ms.send(paquete);

            // Volvemos a leer otro profesor
            // Incrementamos el contador de profesores
            idprofesor++;
            System.out.println("* Identificador profesor    :" + idprofesor );
            System.out.println("Nombre: ");
            nombre= in.readLine();


        }


    }
