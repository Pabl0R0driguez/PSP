package Act3_11;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class ClienteChat extends JFrame implements ActionListener, Runnable {
    private static final long serialVersionUID = 1L;
    Socket socket = null;

    // streams
    DataInputStream fentrada; //PARA LELER LOS MENSAJES
    DataOutputStream fsalida; //PARA ESCRIBIR MENSAJES

    String nombre;
    static JTextField mensaje = new JTextField();

    private JScrollPane scrollpanel;
    static JTextArea textareal;
    JButton botonEnviar = new JButton("Enviar");
    JButton botonSalir = new JButton("Salir");
    boolean repetir = true;

    // constructor
    public ClienteChat(Socket s, String nombre) {
        super(" CONEXIÓN DEL CLIENTE CHAT: " + nombre);
        setLayout(null);
        mensaje.setBounds(10, 10, 400, 30);
        add(mensaje);
        textareal = new JTextArea();
        scrollpanel = new JScrollPane(textareal);
        scrollpanel.setBounds(10, 50, 400, 300);
        add(scrollpanel);
        botonEnviar.setBounds(420, 10, 100, 30);
        add(botonEnviar);
        botonSalir.setBounds(420, 50, 100, 30);
        add(botonSalir);
        textareal.setEditable(false);
        botonEnviar.addActionListener(this);
        botonSalir.addActionListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        socket = s;
        this.nombre = nombre;
        try {
            fentrada = new DataInputStream(socket.getInputStream());
            fsalida = new DataOutputStream(socket.getOutputStream());
            String texto = " > Entra en el Chat " + nombre;
            fsalida.writeUTF(texto);
        } catch (IOException e) {
            System.out.println("ERROR DE E/S");
            e.printStackTrace();
            System.exit(0);
        }
    }// fin constructor

    // accion cuando pulsamos botones
    public void actionPerformed(ActionEvent e) {
        String texto = "";
        if (e.getSource() == botonEnviar) { // SE PULSA ENVIAR
            if (mensaje.getText().trim().length() == 0) {
                return;
            }
            texto = nombre + " > " + mensaje.getText();
            try {
                mensaje.setText("");
                fsalida.writeUTF(texto); // Envía el mensaje
            } catch (IOException el) {
                el.printStackTrace();
            }
        }

        if (e.getSource() == botonSalir) { // SE PULSA BOTÓN SALIR
            texto = " > Abandona el Chat: " + nombre;
            try {
                fsalida.writeUTF(texto); // Notifica al servidor
                fsalida.writeUTF("*"); // Envía el carácter de desconexión
                repetir = false; // Para salir del bucle
            } catch (IOException el) {
                el.printStackTrace();
            }
        }
    }

    public void run() {
        String texto = "";
        while (repetir) {
            try {
                texto = fentrada.readUTF(); //leer mensajes
                textareal.setText(texto); //visualizarlos
            } catch (IOException e) {
// este error sale cuando el servidor se cierra
                JOptionPane.showMessageDialog(null,
                        "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" +
                                e.getMessage(), "<<MENSAJE DE ERROR:2>>",
                        JOptionPane.ERROR_MESSAGE);
                repetir = false; //salir del bucle
            }
        } // fin while
        try {
            socket.close(); //cerrar socket
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// fin run

    public static void main(String args[]) {
        int puerto = 44444;
        Socket s = null;
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre o nick:");
        if (nombre.trim().length() == 0) {
            System.out.println("El nombre está vacío....");
            return;
        }
        try {
            s = new Socket("172.16.3.108", puerto);

            ClienteChat cliente = new ClienteChat(s, nombre);
            cliente.setBounds(0, 0, 540, 400);
            cliente.setVisible(true);
            new Thread(cliente).start(); //lanzar hilo cliente
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "IMPOSIBLE CONECTAR CON EL SERVIDOR\n" +
                            e.getMessage(), "<<MENSAJE DE ERROR:1>>",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// main
}//..ClienteChat



