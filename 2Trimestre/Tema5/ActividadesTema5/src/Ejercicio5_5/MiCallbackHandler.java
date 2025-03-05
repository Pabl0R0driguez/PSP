package Ejercicio5_5;

import javax.security.auth.callback.*;

public class MiCallbackHandler implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName("pedro"); // Simula entrada de usuario
            } else if (callback instanceof PasswordCallback) {
                ((PasswordCallback) callback).setPassword("ABCD".toCharArray()); // Simula entrada de contraseña
            } else {
                throw new UnsupportedCallbackException(callback, "Tipo de Callback no soportado");
            }
        }
    }
}
