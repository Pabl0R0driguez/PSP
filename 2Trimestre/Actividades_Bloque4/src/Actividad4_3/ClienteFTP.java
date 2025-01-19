package Actividad4_3;

import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.Arrays;

public class ClienteFTP {
    private static FTPClient cliente = new FTPClient();

    public static void main(String[] args) {
        // Crear interfaz gráfica
        JFrame frame = new JFrame("Cliente FTP");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel para la conexión
        JPanel panelConexion = new JPanel();
        panelConexion.setLayout(new GridLayout(3, 2));
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton connectButton = new JButton("Conectar");

        panelConexion.add(new JLabel("Usuario:"));
        panelConexion.add(userField);
        panelConexion.add(new JLabel("Contraseña:"));
        panelConexion.add(passField);
        panelConexion.add(new JLabel());
        panelConexion.add(connectButton);

        frame.add(panelConexion, BorderLayout.NORTH);

        // Panel para mostrar ficheros
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> fileList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(fileList);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botón para descargar
        JButton downloadButton = new JButton("Descargar");
        downloadButton.setEnabled(false);
        frame.add(downloadButton, BorderLayout.SOUTH);

        // Acción del botón Conectar
        connectButton.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            try {
                cliente.connect("localhost");
                cliente.login(user, pass);
                cliente.enterLocalPassiveMode();

                // Obtener lista de ficheros
                String[] archivos = cliente.listNames();
                if (archivos != null) {
                    listModel.clear();
                    for (String archivo : archivos) {
                        if (!archivo.endsWith("/")) {  // Solo ficheros, no directorios
                            listModel.addElement(archivo);
                        }
                    }
                    downloadButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pudo obtener la lista de ficheros.");
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error de conexión: " + ex.getMessage());
            }
        });

        // Acción del botón Descargar
        downloadButton.addActionListener(e -> {
            String selectedFile = fileList.getSelectedValue();
            if (selectedFile != null) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File(selectedFile));

                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File destinationFile = fileChooser.getSelectedFile();
                    try (OutputStream outputStream = new FileOutputStream(destinationFile)) {
                        boolean success = cliente.retrieveFile(selectedFile, outputStream);
                        if (success) {
                            JOptionPane.showMessageDialog(frame, "Archivo descargado con éxito.");
                        } else {
                            JOptionPane.showMessageDialog(frame, "Error al descargar el archivo.");
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error de descarga: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecciona un archivo.");
            }
        });

        // Mostrar la interfaz
        frame.setVisible(true);
    }
}
