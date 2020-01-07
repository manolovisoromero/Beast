package endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import REST_calls.LoginRequest;
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
import java.util.ArrayList;



import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;



@Path("/authenticate")
public class AuthenticationEndpoint {

    Machine machine = Machine.getInstance();


    @NameBinding
    @Retention(RUNTIME)
    @Target({TYPE, METHOD})
    public @interface Secured { }



    @POST
    @Path("/authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(
            AuthenticationRequest authenticationRequest){
        try{
            User user = match(authenticationRequest);
            return Response.ok() //200
                    .entity(user.getToken())
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN) //200
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)

    public Response loginUser(
            LoginRequest loginRequest){
        return Response.status(200).entity(machine.loginUser(loginRequest.getUsername(),loginRequest.getPassword()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .header("Content-Type", "application/json")
                .allow("OPTIONS")
                .build();


    }


    @GET
    @Secured
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test(){
        return Response.ok() //200
                .entity("test")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }





    private User match(AuthenticationRequest authenticationRequest) throws Exception {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//        Criteria criteria = session.createCriteria(User.class);
//        criteria.add(Restrictions.eq("username",authenticationRequest.getUsername()));
//        ArrayList<User> users = (ArrayList<User>) criteria.list();
//
//
//        for(int i = 0; i<users.size();i++){
//            if(users.get(i).getPassword().equals(authenticationRequest.getPassword())){
//                System.out.println("userID: "+users.get(i).getUserID()+ " authenticated.");
//                User user = users.get(i);
//                user.setToken(issueToken());
//                session.getTransaction().commit();
//                HibernateUtil.shutdown();
//                return user;
//            }
//        }
//        session.getTransaction().commit();
//        HibernateUtil.shutdown();


        User user = new User();
        user.setToken("111");
        if(authenticationRequest.getUsername().equals("user")){
            return user;
        }


        return null;
    }


    private String issueToken()
    {
        int length = 50;
        boolean useLetters = true;
        boolean useNumbers = false;
        String token = RandomStringUtils.random(length, useLetters, useNumbers);

        System.out.println(token);

        return token;

    }
}
