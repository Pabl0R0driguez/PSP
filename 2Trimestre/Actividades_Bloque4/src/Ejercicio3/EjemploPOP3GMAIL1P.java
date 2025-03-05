package Ejercicio3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class EjemploPOP3GMAIL1P extends JFrame {
	private JTextField serverField, userField, portField;
	private JPasswordField passField;
	private JTextArea messageArea;
	private JButton connectButton, fetchButton;
	private POP3SClient pop3Client;

	public EjemploPOP3GMAIL1P() {
		setTitle("Cliente POP3");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 0; gbc.gridy = 0;
		add(new JLabel("Servidor:"), gbc);
		gbc.gridx = 1;
		serverField = new JTextField("localhost", 15);
		add(serverField, gbc);

		gbc.gridx = 2;
		add(new JLabel("Puerto:"), gbc);
		gbc.gridx = 3;
		portField = new JTextField("110", 5);
		add(portField, gbc);

		gbc.gridx = 0; gbc.gridy = 1;
		add(new JLabel("Usuario:"), gbc);
		gbc.gridx = 1;
		userField = new JTextField("usuario@pablo2dam", 15);
		add(userField, gbc);

		gbc.gridx = 2;
		add(new JLabel("Contraseña:"), gbc);
		gbc.gridx = 3;
		passField = new JPasswordField(10);
		add(passField, gbc);

		gbc.gridx = 1; gbc.gridy = 2;
		connectButton = new JButton("Conectar");
		add(connectButton, gbc);

		gbc.gridx = 2;
		fetchButton = new JButton("Recuperar Mensajes");
		fetchButton.setEnabled(false);
		add(fetchButton, gbc);

		gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4;
		messageArea = new JTextArea(10, 50);
		messageArea.setEditable(false);
		add(new JScrollPane(messageArea), gbc);

		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectarServidor();
			}
		});

		fetchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recuperarMensajes();
			}
		});
	}

	private void conectarServidor() {
		String server = serverField.getText();
		String user = userField.getText();
		String password = new String(passField.getPassword());
		int port = Integer.parseInt(portField.getText());

		pop3Client = new POP3SClient(true);
		try {
			pop3Client.connect(server, port);
			messageArea.append("Conectado a " + server + "\n");
			if (!pop3Client.login(user, password)) {
				messageArea.append("Error al hacer login\n");
			} else {
				messageArea.append("Login exitoso\n");
				fetchButton.setEnabled(true);
			}
		} catch (IOException ex) {
			messageArea.append("Error: " + ex.getMessage() + "\n");
		}
	}

	private void recuperarMensajes() {
		try {
			POP3MessageInfo[] messages = pop3Client.listMessages();
			if (messages == null) {
				messageArea.append("No se pueden recuperar mensajes.\n");
				return;
			}
			messageArea.append("Número de mensajes: " + messages.length + "\n");
			for (POP3MessageInfo msg : messages) {
				BufferedReader reader = (BufferedReader) pop3Client.retrieveMessage(msg.number);
				if (reader == null) continue;
				messageArea.append("Mensaje " + msg.number + ":\n");
				String line;
				while ((line = reader.readLine()) != null) {
					messageArea.append(line + "\n");
				}
				reader.close();
			}
			pop3Client.logout();
			pop3Client.disconnect();
		} catch (IOException ex) {
			messageArea.append("Error al recuperar mensajes: " + ex.getMessage() + "\n");
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new EjemploPOP3GMAIL1P().setVisible(true));
	}
}
