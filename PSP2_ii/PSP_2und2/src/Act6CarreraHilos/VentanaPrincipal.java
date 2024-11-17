package UT2.actividades.MisSoluciones.Act6CarreraHilos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal  extends JFrame {
    private static JPanel contentPane;
    private static JTextField Contador;
    private static JTextField contador2;
    private static JLabel lblHilo1;
    private static JLabel lblHilo2;
    private static JProgressBar barra1;
    private static JProgressBar barra2;
    private static JProgressBar barra3;
    private static JTextField contador3;
    private static JLabel lblGanador;
    private static JButton btnComenzar;
    private static JSlider prioridad1;
    private static JSlider prioridad2;
    private static JSlider prioridad3;
    private static JLabel lblNewLabel;
    private static JLabel label;
    private static JLabel label_1;
    private static JLabel lp1;
    private static JLabel lp2;
    private static JLabel lp3;


    public static void main(String[] args) {
        VentanaPrincipal marco = new VentanaPrincipal();
        marco.setVisible(true);

    }

    public  VentanaPrincipal()
    {
        /**********************  Marco   ***********************************/
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);;

            }
        });

        setTitle("USANDO PRIORIDADES. CARRERA DE HILOS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 510, 352);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


       /**********************  Botón comenzar   ***********************************/
        btnComenzar = new JButton("Comenzar Carrera");
        btnComenzar.setBounds(142, 11, 178, 23);
        contentPane.add(btnComenzar);

        // El botón Comenzar llama a un método del Controlador donde se inician los hilos.
        // En esta clase, de la vista, asociamos un listener al botón.
        btnComenzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("dentro actionPerformed");
                lblGanador.setText("     Ganastes                ");
                System.out.println("Valor de contador antes: " + Contador.getText());
                Contador.setText("999");
                System.out.println("Valor de contador después: " + Contador.getText());
                ControladorCarrera.iniciarHilos();
                ControladorCarrera.comenzarCarrera();
                ControladorCarrera.pararCarrera();
            }});

 //**********************  Hilo 1   ***********************************//*

        lblHilo1 = new JLabel("HILO 1");
        lblHilo1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblHilo1.setHorizontalAlignment(SwingConstants.CENTER);
        lblHilo1.setForeground(Color.RED);
        lblHilo1.setBounds(-25, 65, 146, 14);
        contentPane.add(lblHilo1);

        prioridad1 = new JSlider();
        prioridad1.setToolTipText("Prioridad Hilo 1");
        prioridad1.setValue(5);
        prioridad1.setMinimum(1);
        prioridad1.setMaximum(10);
        prioridad1.setBounds(152, 50, 161, 26);
        contentPane.add(prioridad1);

        lblNewLabel = new JLabel("1                5               10");
        lblNewLabel.setBounds(152, 73, 158, 14);
        contentPane.add(lblNewLabel);


        lp1 = new JLabel("Prioridad: 5");
        lp1.setHorizontalAlignment(SwingConstants.RIGHT);
        lp1.setForeground(Color.RED);
        lp1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lp1.setBounds(323, 64, 161, 14);
        contentPane.add(lp1);

        barra1 = new JProgressBar();
        barra1.setBounds(23, 89, 461, 18);
        contentPane.add(barra1);


        /**********************  Hilo 2   ***********************************/
        lblHilo2 = new JLabel("HILO 2");
        lblHilo2.setHorizontalAlignment(SwingConstants.CENTER);
        lblHilo2.setForeground(Color.GREEN);
        lblHilo2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblHilo2.setBounds(-25, 130, 147, 14);
        contentPane.add(lblHilo2);

        prioridad2 = new JSlider();
        prioridad2.setToolTipText("Prioridad Hilo 2");
        prioridad2.setValue(5);
        prioridad2.setMinimum(1);
        prioridad2.setMaximum(10);
        prioridad2.setBounds(152, 113, 161, 26);
        contentPane.add(prioridad2);

        label = new JLabel("1                5               10");
        label.setBounds(152, 136, 158, 14);
        contentPane.add(label);

        lp2 = new JLabel("Prioridad: 5");
        lp2.setHorizontalAlignment(SwingConstants.RIGHT);
        lp2.setForeground(Color.GREEN);
        lp2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lp2.setBounds(323, 130, 161, 14);
        contentPane.add(lp2);


        barra2 = new JProgressBar();
        barra2.setBounds(24, 150, 461, 18);
        contentPane.add(barra2);

        /**********************  Hilo 3   ***********************************/
        JLabel lblHilo = new JLabel("HILO 3");
        lblHilo.setHorizontalAlignment(SwingConstants.CENTER);
        lblHilo.setForeground(Color.BLUE);
        lblHilo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblHilo.setBounds(-21, 193, 147, 14);
        contentPane.add(lblHilo);

        prioridad3 = new JSlider();
        prioridad3.setToolTipText("Prioridad Hilo 3");
        prioridad3.setValue(5);
        prioridad3.setMinimum(1);
        prioridad3.setMaximum(10);
        prioridad3.setBounds(152, 179, 161, 26);
        contentPane.add(prioridad3);

        label_1 = new JLabel("1                5               10");
        label_1.setBounds(152, 203, 158, 14);
        contentPane.add(label_1);


        lp3 = new JLabel("Prioridad: 5");
        lp3.setHorizontalAlignment(SwingConstants.RIGHT);
        lp3.setForeground(Color.BLUE);
        lp3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lp3.setBounds(323, 195, 161, 14);
        contentPane.add(lp3);

        barra3 = new JProgressBar();
        barra3.setBounds(24, 218, 461, 18);
        contentPane.add(barra3);

        /********************** Contadores   ***********************************/
        Contador = new JTextField();
        Contador.setEditable(false);
        Contador.setFont(new Font("Tahoma", Font.PLAIN, 13));
        Contador.setHorizontalAlignment(SwingConstants.CENTER);
        Contador.setBackground(Color.RED);
        Contador.setForeground(Color.WHITE);
        Contador.setBounds(60, 255, 86, 20);
        Contador.setColumns(10);
        contentPane.add(Contador);
        Contador.setText("5");
        Contador.setText("10");


        contador2 = new JTextField();
        contador2.setHorizontalAlignment(SwingConstants.CENTER);
        contador2.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contador2.setEditable(false);
        contador2.setColumns(10);
        contador2.setBounds(197, 255, 86, 20);
        contador2.setBackground(Color.GREEN);
        contador2.setForeground(Color.WHITE);
        contentPane.add(contador2);
        contador2.setText("5");
        contador2.setText("10");


        contador3 = new JTextField();
        contador3.setHorizontalAlignment(SwingConstants.CENTER);
        contador3.setFont(new Font("Tahoma", Font.PLAIN, 13));
        contador3.setEditable(false);
        contador3.setColumns(10);
        contador3.setBounds(339, 255, 86, 20);
        contador3.setBackground(Color.BLUE);
        contador3.setForeground(Color.WHITE);
        contentPane.add(contador3);
        contador3.setText("5");
        contador3.setText("10");

        /********************** Ganador   ***********************************/

        lblGanador = new JLabel("");
        lblGanador.setHorizontalAlignment(SwingConstants.CENTER);
        lblGanador.setForeground(Color.RED);
        lblGanador.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblGanador.setBounds(104, 288, 279, 14);
        contentPane.add(lblGanador);
    }


    /********************** getters  &  setters   ***********************************/
    public static JTextField getContador() {
        return Contador;
    }

    public static JTextField getContador2() {
        return contador2;
    }

    public static JLabel getLblHilo1() {
        return lblHilo1;
    }

    public static JLabel getLblHilo2() {
        return lblHilo2;
    }

    public static JProgressBar getBarra1() {
        return barra1;
    }

    public static JProgressBar getBarra2() {
        return barra2;
    }

    public static JProgressBar getBarra3() {
        return barra3;
    }

    public static JTextField getContador3() {
        return contador3;
    }

    public static JLabel getLblGanador() {
        return lblGanador;
    }

    public static JButton getBtnComenzar() {
        return btnComenzar;
    }

    public static JSlider getPrioridad1() {
        return prioridad1;
    }

    public static JSlider getPrioridad2() {
        return prioridad2;
    }

    public static JSlider getPrioridad3() {
        return prioridad3;
    }

    public static JLabel getLblNewLabel() {
        return lblNewLabel;
    }

    public static JLabel getLabel() {
        return label;
    }

    public static JLabel getLabel_1() {
        return label_1;
    }

    public static JLabel getLp1() {
        return lp1;
    }

    public static JLabel getLp2() {
        return lp2;
    }

    public static JLabel getLp3() {
        return lp3;
    }





    public static void setContador(String contador) {
        System.out.println ("Entré en setContador");
        Contador.setForeground(Color.WHITE);
        Contador.setText("");
        Contador.setText("1000");
    }

    public static void setContador2(String contador) {
   //     System.out.println ("Contador 2: " +  contador + " ");
        contador2.setText(contador);

    }

    public static void setContador3(String contador) {
      //  System.out.println ("Contador 3: " +  contador + " ");
        contador3.setText(contador);
    }
    public static void setLblHilo1(JLabel lblHilo1) {
        VentanaPrincipal.lblHilo1 = lblHilo1;
    }

    public static void setLblHilo2(JLabel lblHilo2) {
        VentanaPrincipal.lblHilo2 = lblHilo2;
    }

    public static void setBarra1(int barra1) {
        VentanaPrincipal.barra1.setValue(barra1);
    }

    public static void setBarra2(JProgressBar barra2) {
        VentanaPrincipal.barra2 = barra2;
    }

    public static void setBarra3(JProgressBar barra3) {
        VentanaPrincipal.barra3 = barra3;
    }



    public static void setLblGanador(JLabel lblGanador) {
        VentanaPrincipal.lblGanador = lblGanador;
    }

    public static void setBtnComenzar(JButton btnComenzar) {
        VentanaPrincipal.btnComenzar = btnComenzar;
    }

    public static void setPrioridad1(JSlider prioridad1) {
        VentanaPrincipal.prioridad1 = prioridad1;
    }

    public static void setPrioridad2(JSlider prioridad2) {
        VentanaPrincipal.prioridad2 = prioridad2;
    }

    public static void setPrioridad3(JSlider prioridad3) {
        VentanaPrincipal.prioridad3 = prioridad3;
    }

    public  static void setLblNewLabel(JLabel lblNewLabel) {
        lblNewLabel = lblNewLabel;
    }

    public static void setLabel(JLabel label) {
        label = label;
    }

    public static void setLabel_1(JLabel label_1) {
        label_1 = label_1;
    }

    public static void setLp1(JLabel lp1) {
        lp1 = lp1;
    }

    public static void setLp2(JLabel lp2) {
        lp2 = lp2;
    }

    public static void setLp3(JLabel lp3) {
        lp3 = lp3;
    }

}