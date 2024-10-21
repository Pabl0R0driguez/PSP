package Tarea2_3;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class ContadorApplet extends Applet implements ActionListener {
    private Button b1, b2; // Botones para detener cada hilo
    private long contador1; // Contador del primer hilo
    private long contador2; // Contador del segundo hilo
    private Font fuente;
    private HiloContador hiloContador1;
    private HiloContador hiloContador2;

    // Inicializo los componentes del applet
    public void init() {
        setBackground(Color.yellow);
        b1 = new Button("Detener contador 1");
        b2 = new Button("Detener contador 2");
        add(b1);
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        fuente = new Font("Verdana", Font.BOLD, 24);

        // Inicializo los contadores con valores diferentes
        contador1 = 0;     // Comienza en 0
        contador2 = 10;    // Comienza en 10

        // Creo los hilos con valores iniciales
        hiloContador1 = new HiloContador(contador1);
        hiloContador2 = new HiloContador(contador2);
        hiloContador1.start();
        hiloContador2.start();
    }

    // Esta es mi clase interna para manejar el hilo del contador
    class HiloContador extends Thread {
        private long contador; // Valor del contador

        // Constructor que recibe el valor inicial
        public HiloContador(long valorInicial) {
            this.contador = valorInicial; // Inicializamos el contador
        }

        public void run() {
            while (true) {
                try {
                    Thread.sleep(400); // Esperar 400 ms
                    contador++; // Incrementar el contador
                    repaint(); // Pido que se vuelva a dibujar el applet
                } catch (InterruptedException e) {
                    System.out.println("Hilo interrumpido: " + e.getMessage());
                }
            }
        }

        public void pararHilo() {
            stop(); // Detener el hilo
        }

        // Metodo que devuelve el valor actual del contador
        public long getContador() {
            return contador;
        }
    }

    // Este metodo se ejecuta cuando se pulsa un botón
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // Detener el primer hilo
            hiloContador1.pararHilo();
        } else if (e.getSource() == b2) {
            // Detener el segundo hilo
            hiloContador2.pararHilo();
        }
    }

    // Este metodo se encarga de dibujar en el applet
    public void paint(Graphics g) {
        g.clearRect(0, 0, 400, 400);
        g.setFont(fuente);
        g.drawString("CONTADOR 1", 20, 100);
        g.drawString(Long.toString(hiloContador1.getContador()), 80, 150);
        g.drawString("CONTADOR 2", 20, 200);
        g.drawString(Long.toString(hiloContador2.getContador()), 80, 250);
    }
}
