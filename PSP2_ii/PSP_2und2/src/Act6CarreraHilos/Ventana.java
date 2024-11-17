package UT2.actividades.MisSoluciones.Act6CarreraHilos;

import javax.swing.*;

public class Ventana {
    private JButton BotonComenzar;
    private JPanel PanelPrincipal;
    private JSlider PrioridadHilo1;
    private JProgressBar ProgresoHilo1;

    /*public Ventana() {
        BotonComenzar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null,"Hola");
            }
        });
    }
*/
    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new Ventana().PanelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
