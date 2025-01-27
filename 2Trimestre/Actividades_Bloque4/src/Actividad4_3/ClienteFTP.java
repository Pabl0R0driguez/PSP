package Actividad4_3;

import org.apache.commons.net.ftp.FTPClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClienteFTP extends JFrame {

    private static FTPClient ftpClient = new FTPClient();

    public static void main(String[] args) {
        // Crear ventana principal
        JFrame frame = new JFrame("Descargar Ficheros");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500); // Ajustar tamaño de la ventana principal
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Panel para ingresar usuario y contraseña
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createTitledBorder("Conexión FTP"));

        // Configuración de las restricciones para el GridBagLayout
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        JTextField userField = new JTextField(20); // Aumentar el ancho del campo de texto
        loginPanel.add(userField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        JPasswordField passField = new JPasswordField(20); // Aumentar el ancho del campo de texto
        loginPanel.add(passField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupa dos columnas
        JButton connectButton = new JButton("Conectar");
        loginPanel.add(connectButton, gbc);

        // Añadir el panel de conexión al marco
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa dos columnas
        frame.add(loginPanel, gbc);

        // Lista de archivos
        DefaultListModel<String> fileListModel = new DefaultListModel<>();
        JList<String> fileList = new JList<>(fileListModel);
        JScrollPane scrollPane = new JScrollPane(fileList);
        scrollPane.setPreferredSize(new Dimension(700, 250)); // Aumentar el tamaño del JScrollPane

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        frame.add(scrollPane, gbc);

        // Botones
        JPanel buttonPanel = new JPanel();
        JButton downloadButton = new JButton("Descargar");
        JButton exitButton = new JButton("Salir");
        buttonPanel.add(downloadButton);
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        frame.add(buttonPanel, gbc);

        // Acción para conectarse al servidor FTP
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userField.getText();
                String password = new String(passField.getPassword());
                try {
                    ftpClient.connect("localhost", 21); // Conectar al servidor FTP
                    boolean login = ftpClient.login(user, password); // Intentar iniciar sesión
                    ftpClient.changeWorkingDirectory("/home/usuario"); // Cambiar el directorio de trabajo

                    if (login) {
                        JOptionPane.showMessageDialog(frame, "Conexión establecida exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        ftpClient.enterLocalPassiveMode(); // Habilitar modo pasivo para la transferencia de archivos

                        // Listar archivos disponibles
                        fileListModel.clear();
                        String[] files = ftpClient.listNames(); // Obtener lista de nombres de archivos
                        if (files != null) {
                            for (String file : files) {
                                fileListModel.addElement(file); // Agregar archivos a la lista
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "No se encontraron archivos en el directorio.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Usuario o contraseña incorrectos.", "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "No se pudo establecer conexión: " + ex.getMessage(), "Error de Conexión", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Acción para descargar un archivo
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFile = fileList.getSelectedValue();
                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(frame, "Por favor, seleccione un archivo para descargar.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Seleccionar directorio de destino
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showSaveDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File directory = fileChooser.getSelectedFile();
                    File localFile = new File(directory, selectedFile);

                    try (FileOutputStream fos = new FileOutputStream(localFile)) {
                        boolean success = ftpClient.retrieveFile(selectedFile, fos); // Descargar archivo
                        if (success) {
                            JOptionPane.showMessageDialog(frame, "Archivo descargado correctamente en: " + localFile.getAbsolutePath(), "Descarga Completa", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "No se pudo descargar el archivo.", "Error de Descarga", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Error al guardar el archivo: " + ex.getMessage(), "Error de Guardado", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Acción para salir
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (ftpClient.isConnected()) {
                        ftpClient.logout(); // Cerrar sesión en el servidor FTP
                        ftpClient.disconnect(); // Desconectar del servidor FTP
                    }
                } catch (IOException ex) {
                    ex.printStackTrace(); // Imprimir la excepción en caso de error
                }
                frame.dispose(); // Cerrar la ventana
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centra la ventana en la pantalla
    }
}
