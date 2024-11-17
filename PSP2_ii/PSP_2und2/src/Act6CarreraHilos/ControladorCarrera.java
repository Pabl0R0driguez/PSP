package UT2.actividades.MisSoluciones.Act6CarreraHilos;


public class ControladorCarrera {
    private static HILOCARRERA hilo=null;
    private static HILOCARRERA hilo2=null;
    private static HILOCARRERA hilo3=null;

    public static void iniciarHilos() {
        double tiempo = Math.random() * 1000 + 1;
        hilo = new HILOCARRERA("UNO", tiempo);
        hilo2 = new HILOCARRERA("DOS", tiempo);
        hilo3 = new HILOCARRERA("TRES",tiempo);
    }

    public static void comenzarCarrera ()
        {
            System.out.println("Carrera comenzada");
            VentanaPrincipal.getBtnComenzar().setEnabled(false);

         if (!hilo.isAlive()) {
            int prioridad1 = VentanaPrincipal.getPrioridad1().getValue();
            System.out.println("prioridad hilo 1:" + prioridad1);
            hilo.setPriority(prioridad1);
            VentanaPrincipal.getLp1().setText("Prioridad: " + Integer.toString(prioridad1));
            hilo.start();
        }

        if (!hilo2.isAlive()){
          int prioridad2 = VentanaPrincipal.getPrioridad2().getValue();
            hilo2.setPriority(prioridad2);
            VentanaPrincipal.getLp2().setText("Prioridad: " + Integer.toString(prioridad2));
            hilo2.start();
        }

        if (!hilo3.isAlive()){
          int prioridad3 = VentanaPrincipal.getPrioridad3().getValue();
            hilo3.setPriority(prioridad3);
            VentanaPrincipal.getLp3().setText("Prioridad: " + Integer.toString(prioridad3));
            hilo3.start();
        }



        if (hilo.isAlive())
            System.out.println(hilo.getPriority());
        if (hilo2.isAlive())
            System.out.println(hilo2.getPriority());
        if (hilo3.isAlive())
            System.out.println(hilo3.getPriority());

    }


    public static void pararCarrera()
    {
        System.out.println("Controlando fin de carrera");
        while (true) {
            if (hilo.isAlive()) {
                int con1=hilo.getContador();
                VentanaPrincipal.setContador(String.valueOf(con1));
                VentanaPrincipal.setBarra1(con1);
                if (con1 == VentanaPrincipal.getBarra1().getMaximum()){
                    FinalizarProceso();
                    VentanaPrincipal.getLblGanador().setText("GANA HILO 1");
                }
            }

            if (hilo2.isAlive()) {
                int con2=hilo2.getContador();
                VentanaPrincipal.setContador2(String.valueOf(con2));
                VentanaPrincipal.setBarra1(con2);
                if (con2 == VentanaPrincipal.getBarra2().getMaximum()){
                    FinalizarProceso();
                    VentanaPrincipal.getLblGanador().setText("GANA HILO 2");
                }
            }

            if (hilo3.isAlive()) {
                int con3=hilo3.getContador();
                VentanaPrincipal.setContador3(String.valueOf(con3));
                VentanaPrincipal.setBarra1(con3);
                if (con3 == VentanaPrincipal.getBarra3().getMaximum()){
                    FinalizarProceso();
                    VentanaPrincipal.getLblGanador().setText("GANA HILO 3");
                }
                }
            }
        }




    private static void FinalizarProceso() {

        hilo.pararHilo();
        hilo2.pararHilo();
        hilo3.pararHilo();

        iniciarHilos() ;

        VentanaPrincipal.getBtnComenzar().setEnabled(true);
        VentanaPrincipal.getLblGanador().setText("                               ");

    }
}