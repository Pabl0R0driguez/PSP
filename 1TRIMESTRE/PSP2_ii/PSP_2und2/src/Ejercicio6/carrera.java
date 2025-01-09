package Ejercicio6;

import javax.swing.*;
import java.awt.*;

public class carrera extends JFrame {
    private JProgressBar progressBar1, progressBar2, progressBar3;
    private JSlider slider1, slider2, slider3, slider4;
    private JTextField textField1, textField2, textField3, textField4;
    private JButton startButton;
    private JLabel winnerLabel;

    private Thread thread1, thread2, thread3;
    private int sleepTime = 50;

    public carrera() {
        setTitle("Carrera de Hilos");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Disposición principal con BorderLayout

        // Botón centrado en la parte superior
        startButton = new JButton("Comenzar Carrera");
        add(startButton, BorderLayout.NORTH);

        // Panel central con la disposición de sliders, text fields y barras de progreso
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridLayout(12, 1, 5, 5)); // 12 filas, 1 columna
        add(centralPanel, BorderLayout.CENTER);

        // Hilo 1
        slider1 = new JSlider(1, 10, 5);
        textField1 = new JTextField("Prioridad Hilo 1: 5");
        textField1.setEditable(false);
        progressBar1 = new JProgressBar(0, 100);

        centralPanel.add(new JLabel("Prioridad Hilo 1:"));
        centralPanel.add(slider1);
        centralPanel.add(textField1);
        centralPanel.add(progressBar1);

        // Hilo 2
        slider2 = new JSlider(1, 10, 5);
        textField2 = new JTextField("Prioridad Hilo 2: 5");
        textField2.setEditable(false);
        progressBar2 = new JProgressBar(0, 100);

        centralPanel.add(new JLabel("Prioridad Hilo 2:"));
        centralPanel.add(slider2);
        centralPanel.add(textField2);
        centralPanel.add(progressBar2);

        // Hilo 3
        slider3 = new JSlider(1, 10, 5);
        textField3 = new JTextField("Prioridad Hilo 3: 5");
        textField3.setEditable(false);
        progressBar3 = new JProgressBar(0, 100);

        centralPanel.add(new JLabel("Prioridad Hilo 3:"));
        centralPanel.add(slider3);
        centralPanel.add(textField3);
        centralPanel.add(progressBar3);

        // Tiempo de Sleep
        slider4 = new JSlider(50, 200, 50);
        textField4 = new JTextField("Sleep: 50ms");
        textField4.setEditable(false);

        centralPanel.add(new JLabel("Tiempo de Sleep (ms):"));
        centralPanel.add(slider4);
        centralPanel.add(textField4);

        // Etiqueta para el ganador en la parte inferior
        winnerLabel = new JLabel("", SwingConstants.CENTER);
        add(winnerLabel, BorderLayout.SOUTH);

        // Listeners de sliders
        slider1.addChangeListener(e -> textField1.setText("Prioridad Hilo 1: " + slider1.getValue()));
        slider2.addChangeListener(e -> textField2.setText("Prioridad Hilo 2: " + slider2.getValue()));
        slider3.addChangeListener(e -> textField3.setText("Prioridad Hilo 3: " + slider3.getValue()));
        slider4.addChangeListener(e -> textField4.setText("Sleep: " + slider4.getValue() + "ms"));

        // Listener del botón
        startButton.addActionListener(e -> startRace());

        setVisible(true);
    }

    private void startRace() {
        startButton.setEnabled(false);
        progressBar1.setValue(0);
        progressBar2.setValue(0);
        progressBar3.setValue(0);
        winnerLabel.setText("");

        sleepTime = slider4.getValue();

        thread1 = new Thread(() -> runRace(progressBar1, "Hilo 1"));
        thread2 = new Thread(() -> runRace(progressBar2, "Hilo 2"));
        thread3 = new Thread(() -> runRace(progressBar3, "Hilo 3"));

        thread1.setPriority(slider1.getValue());
        thread2.setPriority(slider2.getValue());
        thread3.setPriority(slider3.getValue());

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private void runRace(JProgressBar progressBar, String threadName) {
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(sleepTime);
                progressBar.setValue(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (progressBar.getValue() == 100) {
                SwingUtilities.invokeLater(() -> {
                    winnerLabel.setText(threadName + " ha ganado!");
                    startButton.setEnabled(true);
                });
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(carrera::new);
    }
}
