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


    @Path("/API/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @POST
    public Response loginUser(
            LoginRequest loginRequest){
        return Response.status(200).entity(machine.loginUser(loginRequest.getUsername(),loginRequest.getPassword()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS")
                .build();


    }



    @POST
    @Path("/registerUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerUser(
            RegisterRequest registerRequest){
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

    @GET
    @Path("/Game")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGame(
            ){
        return Response.ok() //200
                .entity(machine.getGame())
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @POST
    @Path("/Check-Auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authCheck(){

        return null;

    }




}
