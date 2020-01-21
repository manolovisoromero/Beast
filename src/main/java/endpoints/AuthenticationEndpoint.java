package endpoints;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import REST_calls.LoginRequest;
import com.google.gson.Gson;
import entities.User;
import REST_calls.AuthenticationRequest;
import logic.HibernateUtil;
import logic.Machine;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;



import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;



@Path("/authenticate")
public class AuthenticationEndpoint {

    Machine machine = Machine.getInstance();
    Gson gson = new Gson();



    @NameBinding
    @Retention(RUNTIME)
    @Target({TYPE, METHOD})
    public @interface Secured { }


    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(
            LoginRequest loginRequest) throws NoSuchAlgorithmException {
        return machine.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }






}
