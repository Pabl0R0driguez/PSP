package Tarea2_4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyHilo hilo = new MyHilo();

        boolean hiloIniciado = false;
        String input;

        while (true) {
            System.out.print("Ingresa una cadena (S para suspender, R para reanudar, * para terminar): ");
            input = scanner.nextLine();

            if (input.equals("*")) {
                hilo.finalizar(); // Finaliza el hilo de forma segura
                break;
            } else if (input.equals("S")) {
                hilo.suspende();
                System.out.println("Hilo suspendido.");
            } else if (input.equals("R")) {
                hilo.reanuda();
                System.out.println("Hilo reanudado.");
            }

            // Inicia el hilo solo una vez
            if (!hiloIniciado) {
                hilo.start();
                hiloIniciado = true;
            }
        }

        // Espera a que el hilo termine
        try {
            hilo.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
        }

        System.out.println("Valor final del contador: " + hilo.getContador());
        scanner.close();
    }
}
