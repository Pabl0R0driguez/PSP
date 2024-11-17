package UT2.actividades.MisSoluciones.Act6CarreraHilos;


class HILOCARRERA extends Thread {

    private boolean stopHilo = false;
    int c = 0;
    double tiempo;

    HILOCARRERA(String nombre, double d) {
        this.setName(nombre);
        this.tiempo = d;
    }

    public void pararHilo() {
        stopHilo = true;
    }

    int getContador() {
        return c;
    }



    public void run() {
        try {
            while (!stopHilo) {
                c++;
                sleep((long) tiempo);

            }
        } catch (InterruptedException exception) {
        }
        System.out.println("Fin hilo: " + getName());
    }
}// HILO