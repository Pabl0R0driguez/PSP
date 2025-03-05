package Ejercicio1;



public class    Main {

    public Main() {

    }

    // En la clase principal , crearemos un objeto cuenta, que comprartiran las dos personas que hemos creado
    // El saldo inicial es de 40 para ambas
    public static void main(String[] args) {
        // Instanciamos la clase Saldo, para definir el saldo inicial que deseemos (40)
        Saldo saldo = new Saldo(40);
        // Creamos dos cantidades para que cada persona , haga una transaccon distinta
        int cantidad=  (int) (Math.random() * 500 + 1);
        int cantidad2=  (int) (Math.random() * 500 + 1);

        Persona persona1 = new Persona("Juan", saldo, cantidad);
        Persona persona2 = new Persona("Messi", saldo, cantidad2);

        persona1.start();
        persona2.start();
    }
}
