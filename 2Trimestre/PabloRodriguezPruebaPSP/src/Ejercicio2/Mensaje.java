/* La clase Mensaje  está fomado por:
 * 	.- Atributos: para recoger*/ 



package Ejercicio2;


import java.io.*;

public class Mensaje implements Serializable
{
	 public String nombre;
	 public String especialidad;
	 public String asignatura;
	 
	    
   
    public Mensaje(String nombre, String  especialidad, String asignatura)
    {
        this.nombre=nombre;
        this.especialidad=especialidad;
        this.asignatura=asignatura;
    }
   

    public byte [] toByteArray()
    {
        try
        {
           // Se hace la conversion usando un ByteArrayOutputStream y un bjetOutputStream.
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream (bytes);
            os.writeObject(this);
            os.close();
            return bytes.toByteArray();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
 // Se convierte el array de bytes que recibe en un objeto Mensaje.
	    public static Mensaje fromByteArray (byte [] bytes)
    {
        try
        {
            // Se realiza la conversion usando un ByteArrayInputStream y un
            // ObjectInputStream
            ByteArrayInputStream byteArray = new ByteArrayInputStream(bytes);
            ObjectInputStream is = new ObjectInputStream(byteArray);
            Mensaje aux = (Mensaje)is.readObject();
            is.close();
            return aux;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
