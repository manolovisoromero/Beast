import javax.annotation.Priority;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import Entities.User;
import REST_calls.AuthenticationRequest;
import REST_calls.ChangePassRequest;
import REST_calls.RegisterRequest;
import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.nio.charset.Charset;
import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Path("/authenticate")
public class AuthenticationEndpoint {

    Machine machine = Machine.getInstance();
    String tokenn;

    @NameBinding
    @Retention(RUNTIME)
    @Target({TYPE, METHOD})
    public @interface Secured { }

    @GET
    @Path("/test")
    @Secured
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response test(

            ){
        if(true){
            return Response.status(Response.Status.OK) //200
                    .entity("Hoi")
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
    }
        else{
            return Response.status(Response.Status.FORBIDDEN) //200
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }


    @GET
    @Path("/tryy")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response tryy(
    ){

            return Response.status(Response.Status.OK) //200
                    .entity("Hoi")
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();

    }




    @POST
    @Path("/authenticate")
//    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(
            AuthenticationRequest authenticationRequest){
        System.out.println(authenticationRequest);


        if(authenticationRequest.getUsername().equals("Test")) {


            return Response.ok() //200
                    .entity(issueToken())
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }else{
            return Response.status(Response.Status.FORBIDDEN) //200
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }





    }

    @GET
    public Response Default(){
        return Response.ok().entity("ok").build();
    }






    private User match(AuthenticationRequest authenticationRequest) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username",authenticationRequest.getUsername()));
        ArrayList<User> users = (ArrayList<User>) criteria.list();


        for(int i = 0; i<users.size();i++){
            if(users.get(i).getPassword().equals(authenticationRequest.getPassword())){
                System.out.println("userID: "+users.get(i).getUserID()+ " authenticated.");
                User user = users.get(i);
                user.setToken(issueToken());
                session.getTransaction().commit();
                HibernateUtil.shutdown();
                return user;
            }
        }
        session.getTransaction().commit();
        HibernateUtil.shutdown();






        return null;
    }


    private String issueToken()
    {
        int length = 50;
        boolean useLetters = true;
        boolean useNumbers = false;
        String token = RandomStringUtils.random(length, useLetters, useNumbers);
        tokenn = token;
        System.out.println(token);

        return token;

    }
}
