import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.w3c.dom.ls.LSOutput;


@Path("/login")
public class LoginEndpoint {
    Gson g = new Gson();
    Machine machine = Machine.getInstance();



    // region get
    @Path("/hello/{login}")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public Response getLogin(
            @PathParam("login") String name) {
        Message answer = new Message(name);
        return Response.ok() //200
                .entity(g.toJson(answer))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @Path("/hello/all")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public Response getPersons( ){
        System.out.println(g.toJson(machine.persons));
        return Response.ok() //200
                .entity(g.toJson(machine.persons))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }


    @POST
    @Path("/registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(
            RegisterRequest registerRequest){
        System.out.println(registerRequest);
        return Response.status(200).entity(machine.registerUser(registerRequest)).build();
    }

    @PUT
    @Path("/changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response changePassword(
            ChangePassRequest changePassRequest){
        System.out.println(changePassRequest);
        return Response.status(200).entity(machine.changePassword(changePassRequest)).build();
    }

}
