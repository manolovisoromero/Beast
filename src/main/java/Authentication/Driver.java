package Authentication;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Driver {


    public static void main(String[] args){
        final String authFile =

        System.setProperty("java.security.auth.login.config", "C:/Users/Manol/IdeaProjects/Beast/src/main/java/Authentication/auth.config");
        LoginContext loginContext = null;
        try{
            loginContext = new LoginContext("authconfig", new CallbackHandler());
        } catch (LoginException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        while(true){
            try {
                loginContext.login();
                System.exit(0);
            } catch (LoginException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
