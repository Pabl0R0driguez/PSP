package Actividad4_2;

import org.apache.commons.net.ftp.FTPSClient;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SubirFichero {
    private static FTPSClient cliente = new FTPSClient("TLS"); // Especificar el protocolo TLS

    public static void main(String[] args) {
        // Crear interfaz gráfica
        JFrame frame = new JFrame("Cliente FTPS");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 1));

        // Etiqueta de estado
        JLabel statusLabel = new JLabel("Estado: Sin conexión", SwingConstants.CENTER);
        frame.add(statusLabel);

        // Campo para servidor
        JTextField serverField = new JTextField("localhost");
        frame.add(new JLabel("Servidor:"));
        frame.add(serverField);

        // Campo para usuario y contraseña
        JTextField userField = new JTextField("usuario");
        JPasswordField passField = new JPasswordField("usuario");
        frame.add(new JLabel("Usuario:"));
        frame.add(userField);
        frame.add(new JLabel("Contraseña:"));
        frame.add(passField);

        // Botón para conectar
        JButton connectButton = new JButton("Conectar");
        frame.add(connectButton);

        // Botón para seleccionar archivo
        JButton uploadButton = new JButton("Subir Archivo");
        uploadButton.setEnabled(false);
        frame.add(uploadButton);

        // Acción del botón de conexión
        connectButton.addActionListener(e -> {
            String server = serverField.getText();
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            try {
                statusLabel.setText("Conectando...");
                cliente.connect(server,21); // Conectar al servidor
                cliente.execAUTH("TLS"); // Autenticar con TLS
                cliente.enterLocalPassiveMode(); // Modo pasivo

                // Configurar el cliente para usar PROT P
                cliente.execPROT("P"); // Cambiar a PROT P

                boolean login = cliente.login(user, pass);

                if (login) {
                    statusLabel.setText("Conectado a " + server);
                    uploadButton.setEnabled(true);
                } else {
                    statusLabel.setText("Error: Usuario o contraseña incorrectos");
                }
            } catch (IOException ex) {
                statusLabel.setText("Error de conexión: " + ex.getMessage());
            }
        });

        // Acción del botón de subida
        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                try (InputStream inputStream = new FileInputStream(selectedFile)) {
                    boolean success = cliente.storeFile(selectedFile.getName(), inputStream);
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Archivo subido con éxito: " + selectedFile.getName());
                        listarDirectorio();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error al subir el archivo.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });

        // Mostrar la interfaz
        frame.setVisible(true);
    }

    // Método para listar el contenido del directorio raíz
    private static void listarDirectorio() {
        try {
            String[] archivos = cliente.listNames();
            if (archivos != null && archivos.length > 0) {
                System.out.println("Contenido del directorio raíz:");
                for (String archivo : archivos) {
                    System.out.println(archivo);
                }
            } else {
                System.out.println("El directorio está vacío.");
            }
        } catch (IOException e) {
            System.out.println("Error al listar el directorio: " + e.getMessage());
        }
    }

    private static void cerrarConexion() {
        if (cliente.isConnected()) {
            try {
                cliente.logout();
                cliente.disconnect();
            } catch (IOException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
