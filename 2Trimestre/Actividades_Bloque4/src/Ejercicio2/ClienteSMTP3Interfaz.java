package Ejercicio2;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;

public class ClienteSMTP3Interfaz {
    public static void main(String[] args) {
        // Crear el frame de la ventana
        JFrame frame = new JFrame("Cliente SMTP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Panel principal con layout flexible
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Títulos y etiquetas
        JLabel titleLabel = new JLabel("Configuración del Cliente SMTP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titleLabel, gbc);

        // Campos de entrada para los datos
        gbc.gridwidth = 1;

        // Servidor SMTP
        gbc.gridy = 1;
        JTextField serverField = new JTextField("smtp.gmail.com");
        serverField.setText("Servidor SMTP");
        serverField.setForeground(Color.GRAY);
        serverField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (serverField.getText().equals("Servidor SMTP")) {
                    serverField.setText("");
                    serverField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (serverField.getText().isEmpty()) {
                    serverField.setText("Servidor SMTP");
                    serverField.setForeground(Color.GRAY);
                }
            }
        });
        gbc.gridx = 1;
        panel.add(serverField, gbc);

        // Usuario (Correo)
        gbc.gridy = 2;
        JTextField usernameField = new JTextField("pablorodseg@gmail.com");
        usernameField.setText("Usuario (Correo)");
        usernameField.setForeground(Color.GRAY);
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (usernameField.getText().equals("Usuario (Correo)")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Usuario (Correo)");
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });
        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        // Contraseña
        gbc.gridy = 3;
        JPasswordField passwordField = new JPasswordField();
        passwordField.setText("Contraseña");
        passwordField.setForeground(Color.GRAY);
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (new String(passwordField.getPassword()).equals("Contraseña")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (new String(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Contraseña");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Puerto
        gbc.gridy = 4;
        JTextField puertoField = new JTextField("587");
        puertoField.setText("Puerto");
        puertoField.setForeground(Color.GRAY);
        puertoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (puertoField.getText().equals("Puerto")) {
                    puertoField.setText("");
                    puertoField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (puertoField.getText().isEmpty()) {
                    puertoField.setText("Puerto");
                    puertoField.setForeground(Color.GRAY);
                }
            }
        });
        gbc.gridx = 1;
        panel.add(puertoField, gbc);

        // Remitente (Correo)
        gbc.gridy = 5;
        JTextField remitenteField = new JTextField("pablorodseg@gmail.com");
        remitenteField.setText("Remitente (Correo)");
        remitenteField.setForeground(Color.GRAY);
        remitenteField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (remitenteField.getText().equals("Remitente (Correo)")) {
                    remitenteField.setText("");
                    remitenteField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (remitenteField.getText().isEmpty()) {
                    remitenteField.setText("Remitente (Correo)");
                    remitenteField.setForeground(Color.GRAY);
                }
            }
        });
        gbc.gridx = 1;
        panel.add(remitenteField, gbc);

        // Destinatario (Correo)
        gbc.gridy = 6;
        JTextField destinoField = new JTextField();
        destinoField.setText("Destinatario (Correo)");
        destinoField.setForeground(Color.GRAY);
        destinoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (destinoField.getText().equals("Destinatario (Correo)")) {
                    destinoField.setText("");
                    destinoField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (destinoField.getText().isEmpty()) {
                    destinoField.setText("Destinatario (Correo)");
                    destinoField.setForeground(Color.GRAY);
                }
            }
        });
        gbc.gridx = 1;
        panel.add(destinoField, gbc);

        // Asunto
        gbc.gridy = 7;
        JTextField asuntoField = new JTextField();
        asuntoField.setText("Asunto");
        asuntoField.setForeground(Color.GRAY);
        asuntoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (asuntoField.getText().equals("Asunto")) {
                    asuntoField.setText("");
                    asuntoField.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (asuntoField.getText().isEmpty()) {
                    asuntoField.setText("Asunto");
                    asuntoField.setForeground(Color.GRAY);
                }
            }
        });
        gbc.gridx = 1;
        panel.add(asuntoField, gbc);

        // Mensaje
        gbc.gridy = 8;
        JTextArea mensajeArea = new JTextArea(5, 20);
        mensajeArea.setText("Mensaje");
        mensajeArea.setForeground(Color.GRAY);
        mensajeArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (mensajeArea.getText().equals("Mensaje")) {
                    mensajeArea.setText("");
                    mensajeArea.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (mensajeArea.getText().isEmpty()) {
                    mensajeArea.setText("Mensaje");
                    mensajeArea.setForeground(Color.GRAY);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(mensajeArea);
        gbc.gridx = 1;
        panel.add(scrollPane, gbc);

        // Área de resultados
        gbc.gridy = 9;
        JTextArea resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        gbc.gridx = 1;
        panel.add(resultScrollPane, gbc);

        // Botón de envío
        JButton sendButton = new JButton("Enviar");
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(sendButton, gbc);

        // Agregar panel al frame
        frame.add(panel);
        frame.setVisible(true);

        // Acción del botón de envío
        sendButton.addActionListener(e -> {
            String server = serverField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            int puerto = Integer.parseInt(puertoField.getText());
            String remitente = remitenteField.getText();
            String destino = destinoField.getText();
            String asunto = asuntoField.getText();
            String mensaje = mensajeArea.getText();

            try {
                // Crear cliente SMTP seguro
                AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

                // Conectar al servidor SMTP
                client.connect(server, puerto);
                resultArea.append("Conexión realizada al servidor SMTP: " + server + "\n");

                // Establecer clave para comunicación segura
                KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                kmf.init(null, null);
                KeyManager km = kmf.getKeyManagers()[0];
                client.setKeyManager(km);

                int respuesta = client.getReplyCode();
                if (!SMTPReply.isPositiveCompletion(respuesta)) {
                    client.disconnect();
                    resultArea.append("Error de conexión\n");
                    return;
                }

                // Enviar comando EHLO
                client.ehlo(server);
                resultArea.append("Respuesta del servidor: " + client.getReplyString() + "\n");

                // Negociar TLS
                if (client.execTLS()) {
                    resultArea.append("TLS iniciado correctamente\n");

                    // Autenticación
                    if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, username, password)) {
                        resultArea.append("Autenticación exitosa\n");

                        // Crear cabecera y enviar mensaje
                        SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino, "Asunto: " + asunto);
                        client.setSender(remitente);
                        client.addRecipient(destino);

                        Writer writer = client.sendMessageData();
                        if (writer != null) {
                            writer.write(cabecera.toString());
                            writer.write(mensaje);
                            writer.close();
                            resultArea.append("Mensaje enviado con éxito.\n");

                            boolean exito = client.completePendingCommand();
                            if (!exito) {
                                resultArea.append("Error al finalizar la transacción.\n");
                            }
                        } else {
                            resultArea.append("Error al enviar los datos.\n");
                        }
                    } else {
                        resultArea.append("Error de autenticación\n");
                    }
                } else {
                    resultArea.append("Error al negociar TLS\n");
                }

                // Desconectar
                client.disconnect();

            } catch (IOException | NoSuchAlgorithmException ex) {
                resultArea.append("Error: " + ex.getMessage() + "\n");
                ex.printStackTrace();
            } catch (KeyStoreException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeySpecException ex) {
                throw new RuntimeException(ex);
            } catch (UnrecoverableKeyException ex) {
                throw new RuntimeException(ex);
            } catch (InvalidKeyException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
