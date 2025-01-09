package Tarea2_7;

public class Contador {
    private int contador ;

    public Contador(int contador) {
        this.contador = contador;
    }
    public void incrementar() {
        contador = contador + 1;
    }

    public int getContador(){
        return contador;
    }



}