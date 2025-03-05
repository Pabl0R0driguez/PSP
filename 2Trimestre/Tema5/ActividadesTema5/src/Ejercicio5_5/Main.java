package Ejercicio5_5;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {

        ACT5_5 login = new ACT5_5();

        // Creamos Subject que representa al usuario
        Subject subject = new Subject();
        // Creamos el CallbackHandler para obtener nombre y contraseña
        CallbackHandler callbackHandler = new MyCallbackHandler();
        // Inicializar la clase de login
        login.initialize(subject, callbackHandler, null, null);

            // Hacemos login
            if (login.login()) {
                System.out.println("Autenticación exitosa");
            } else {
                System.out.println("Autenticación fallida");
            }

    }
}
