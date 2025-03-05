package Ejercicio5_6;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.util.Map;

public class ACT5_6 implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;

    public boolean commit() throws LoginException { return true; }
    public boolean logout() throws LoginException { return true; }
    public boolean abort() throws LoginException { return true; }

    public void initialize(Subject subject, CallbackHandler handler,
                           Map state, Map options) {
        this.subject = subject;
        this.callbackHandler = handler;
    }

    // Metodo de login para la autentificación del usuario
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("Se necesita CallbackHandler");
        }

        // Se crea el array de Callbacks
        Callback[] callbacks = new Callback[2];

        // Constructor de NameCallback y PasswordCallback con prompt
        callbacks[0] = new NameCallback("Nombre de usuario: ");
        callbacks[1] = new PasswordCallback("Clave: ", false);

        try {
            // Se invoca al método handle del CallbackHandler
            // para solicitar el usuario y la contraseña
            callbackHandler.handle(callbacks);

            String usuario = ((NameCallback) callbacks[0]).getName();
            char[] passw = ((PasswordCallback) callbacks[1]).getPassword();
            String clave = new String(passw);

            // Limpiar el array de contraseña por seguridad
            java.util.Arrays.fill(passw, '\0');

            // La autenticación se realiza aquí
            // El nombre de usuario: pedro, su clave: ABCD
            return "pedro".equalsIgnoreCase(usuario) && "ABCD".equals(clave);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
