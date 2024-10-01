package com.example.act1_psp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Actividad1_4 {
    public static void main(String[] args) throws IOException {

        File directorio = new File("/home/usuario/IdeaProjects/PSP/out/production/PSP");
        //ruta java
       ProcessBuilder p1 = new ProcessBuilder("/home/usuario/.jdks/openjdk-23/bin/java" , "Tarea1_1.LeerNombre" , "Juan");


       // se configura el directorio del directorio donde está el
        // proceso llamado, es decir, LeerNombre
       p1.directory(directorio);
       // Se lanza el proceso
        Process p = p1.start();


        try {
            //Recoge el flujo de salida del proceso como flujo de entrada propio
            InputStream is = p.getInputStream();
            int car;
            //Mientras exista car imprimelo
            while((car = is.read() ) != -1){
                System.out.print((char)car);
            }

            is.close();

        }catch (Exception e){
            //Para imprimir la pila de errores en caso de error
            e.printStackTrace();
        }

        int resultado;
        try {
         resultado = p.waitFor();
            System.out.println("El valor de salida es: " + resultado);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
