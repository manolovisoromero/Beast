package Authentication;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Map;

public class LoginModule implements javax.security.auth.spi.LoginModule {
    public static final String TEST_USERNAME = "manolo";
    public static final String TEST_PASSWORD = "romero";
    private CallbackHandler  callbackHandler = null;
    private boolean authenticationSuccesFlag = false;




    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String,
            ?> sharedState, Map<String, ?> options) {

        this.callbackHandler = callbackHandler;


    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbackArray = new Callback[2];
        callbackArray[0] = new NameCallback("User Name");
        callbackArray[1] = new PasswordCallback("Password: ",false);
        try {
            callbackHandler.handle(callbackArray);
        } catch (IOException | UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        String name = ((NameCallback)  callbackArray[0]).getName();
        String password = new String(((PasswordCallback) callbackArray[1]).getPassword());
        if(TEST_USERNAME.equals(name)&&TEST_PASSWORD.equals(password)){
            System.out.println("Authentication success.");
            authenticationSuccesFlag = true;
        }else{
            authenticationSuccesFlag = false;
            throw new FailedLoginException("Authentication failed.");
        }
        return authenticationSuccesFlag;
    }

    @Override
    public boolean commit() throws LoginException {
        return authenticationSuccesFlag;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
