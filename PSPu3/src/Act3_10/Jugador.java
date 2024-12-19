package Act3_10;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Jugador {
    private Socket socket;
    private PrintWriter escritor;
    private BufferedReader lector;
    private JTextField campoNumero;
    private JTextArea areaTexto;
    private int idJugador = 2;
    private int intentos = 0;

    public Jugador(String host, int puerto) {
        try {
            socket = new Socket(host, puerto);
            escritor = new PrintWriter(socket.getOutputStream(), true);
            lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            JFrame frame = new JFrame("JUGADOR - ADIVINA UN NÚMERO");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JPanel panel = new JPanel();
            frame.add(panel);
            panel.setLayout(null);

            JLabel labelID = new JLabel("ID: " + idJugador);
            labelID.setBounds(10, 10, 150, 25);
            panel.add(labelID);

            JLabel labelIntentos = new JLabel("INTENTOS: " + intentos);
            labelIntentos.setBounds(10, 40, 150, 25);
            panel.add(labelIntentos);

            JLabel labelNumero = new JLabel("NÚMERO A ADIVINAR:");
            labelNumero.setBounds(10, 70, 150, 25);
            panel.add(labelNumero);

            campoNumero = new JTextField();
            campoNumero.setBounds(170, 70, 100, 25);
            panel.add(campoNumero);

            JButton btnEnviar = new JButton("Enviar");
            btnEnviar.setBounds(100, 100, 100, 30);
            panel.add(btnEnviar);

            JButton btnSalir = new JButton("Salir");
            btnSalir.setBounds(210, 100, 80, 30);
            panel.add(btnSalir);

            areaTexto = new JTextArea();
            areaTexto.setBounds(10, 140, 360, 100);
            areaTexto.setEditable(false);
            panel.add(areaTexto);

            btnEnviar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String numeroStr = campoNumero.getText();
                    if (!numeroStr.isEmpty()) {
                        try {
                            int numero = Integer.parseInt(numeroStr);
                            intentos++;
                            escritor.println(numero);

                            String respuesta = lector.readLine();
                            areaTexto.append("Intento " + intentos + ": " + respuesta + "\n");

                            labelIntentos.setText("INTENTOS: " + intentos);
                            campoNumero.setText("");

                            if (respuesta.equals("¡Correcto!")) {
                                JOptionPane.showMessageDialog(frame, "¡Has adivinado el número!", "Felicidades", JOptionPane.INFORMATION_MESSAGE);
                                socket.close();
                                System.exit(0);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Introduce un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });

            btnSalir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        escritor.println("*");
                        socket.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    System.exit(0);
                }
            });

            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Jugador("127.0.0.1", 55555);
    }
}