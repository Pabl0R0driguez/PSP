package Tarea2_9;

//Creamos la clase Cola que será la clase compartida entre Consumidor y Productor
public class Cola {
    private int numero;
    private boolean disponible = false; //Cola vacía inicialmente

//Con este metodo obtendremos el número total de CocaColas
 public int getCola(){
     if(disponible){
         disponible = false;
         return numero;
     }
     return -1;
 }

 //Metodo para añadir coca colas
 public void put(int valor){
     numero = valor;
     disponible = true;
 }
}


