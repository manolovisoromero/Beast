package endpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import REST_calls.ChangePassRequest;
import REST_calls.LoginRequest;
import REST_calls.RegisterRequest;
import com.google.gson.Gson;
import logic.Machine;
import logic.Message;

import java.security.NoSuchAlgorithmException;


@Path("/login")
public class LoginEndpoint {
    Gson g = new Gson();
    Machine machine = Machine.getInstance();



    // region get




    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(
            RegisterRequest registerRequest) throws NoSuchAlgorithmException {
        return Response.status(200).entity(machine.registerUser(registerRequest))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }

    @PUT
    @Path("/changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response changePassword(
            ChangePassRequest changePassRequest){
        return Response.status(200).entity(machine.changePassword(changePassRequest))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();
    }


    @POST
    @Path("/Check-Auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authCheck(){

        return null;

    }




}
