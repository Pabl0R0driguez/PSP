package Ejercicio5_5;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;

public class Main {
    public static void main(String[] args) {
        // Crear el módulo de login
        ACT5_5 loginModule = new ACT5_5();

        // Crear el Subject (aunque aquí no se usa mucho)
        Subject subject = new Subject();

        // Crear el CallbackHandler
        CallbackHandler handler = new MiCallbackHandler();

        // Inicializar el módulo de login
        loginModule.initialize(subject, handler, null, null);

        try {
            // Intentar el login
            if (loginModule.login()) {
                System.out.println("Autenticación exitosa");
            } else {
                System.out.println("Autenticación fallida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
