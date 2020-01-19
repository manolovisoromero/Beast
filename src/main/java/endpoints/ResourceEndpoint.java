package endpoints;

import REST_calls.PostUsergame;
import com.google.gson.Gson;
import entities.User;
import logic.GameField;
import logic.Machine;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/resources")
public class ResourceEndpoint {

    Machine machine = Machine.getInstance();
    Gson gson = new Gson();





    @GET
    @AuthenticationEndpoint.Secured
    @Path("/games/{id}")
    public Response getGameById(){
        //Get game
        return Response.ok().header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity("testGames")
                .allow("OPTIONS").build();
    }

    @GET
    @Path("/cors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response corstest(){
        User user = new User();
        user.setUsername("tester");
        return Response.ok() //200
                .entity(gson.toJson(user))
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT")
                .allow("OPTIONS").build();
    }

    @GET
    @Path("/game/{userid}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response corstest2(@PathParam("userid") int userID){
        return Response.ok() //200
                .entity(machine.getUnplayedGame(userID  ))
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT")
                .allow("OPTIONS").build();
    }

    @POST
    @Path("/usergame")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUsername(PostUsergame postUsergame){
        return machine.winCheck(postUsergame);

    }


//    @GET
//    @Path("/game/{userID}")
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getUnplayedGame(@PathParam("userID") String userID){
//        String game = machine.getUnplayedGame(Integer.parseInt(userID));
//        return Response.ok() //200
//                .entity(game)
//                .header("Access-Control-Allow-Origin", "http://localhost:3000")
//                .header("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT")
//                .allow("OPTIONS").build();
//    }










    @GET
    @AuthenticationEndpoint.Secured
    @Path("/games")
    public Response getAllGames(){
        //Get games
        return Response.ok() //200
                .entity("testGames")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }
    @DELETE
    @AuthenticationEndpoint.Secured
    @Path("/games/{id}")
    public Response deleteGame(){
        //Delete game(not necessary
        return Response.ok() //200
                .entity("test")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @POST
    @AuthenticationEndpoint.Secured
    @Path("/games")
    public Response postGame(){
        //Add game
        return Response.ok() //200
                .entity("test")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }


    @GET
    @AuthenticationEndpoint.Secured
    @Path("/users/{id}")
    public Response getUserById(){
        //Get game
        return Response.ok() //200
                .entity("testUsers")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }
    @GET
    @AuthenticationEndpoint.Secured
    @Path("/users")
    public Response getAllUsers(){
        //Get games
        return Response.ok() //200
                .entity("testUser")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }
    @DELETE
    @AuthenticationEndpoint.Secured
    @Path("/users/{id}")
    public Response deleteUser(){
        //Delete game(not necessary)
        return Response.ok() //200
                .entity("test")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @POST
    @AuthenticationEndpoint.Secured
    @Path("/users")
    public Response postUser(){
        //Add game
        return Response.ok() //200
                .entity("test")
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }



}
