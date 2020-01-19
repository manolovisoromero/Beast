package endpoints;

import com.google.gson.Gson;
import entities.Game;
import entities.User;
import logic.GameField;
import logic.Machine;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;

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
    @Path("/cors3/{userid}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response corstest2(@PathParam("userid") int userID){
        return Response.ok() //200
                .entity(machine.getUnplayedGame(userID  ))
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT")
                .allow("OPTIONS").build();
    }



    /*
    CRUD for Game
     */

    @GET
    @Path("/game/{userID}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUnplayedGame(@PathParam("userID") String userID){
        String game = machine.getUnplayedGame(Integer.parseInt(userID));
        return Response.ok() //200
                .entity(game)
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT")
                .allow("OPTIONS").build();
    }

    @GET
    @AuthenticationEndpoint.Secured
    @Path("/game")
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
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/game/{gameid}")
    public Response deleteGame(
            @PathParam("gameid") int gameid){
        String msg = machine.deleteGame(gameid);
        if(msg.equals("Succesfully deleted")){
            return Response.ok() //200
                    .entity(msg)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }else{
            return Response.status(500) //200
                    .entity(msg)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }

    }

    @POST
    @AuthenticationEndpoint.Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/game")
    public Response postGame(boolean [][] field){
        ArrayList<Game> games = machine.postGame(field);

        if(games != null){
            return Response.ok() //200
                    .entity(gson.toJson(games))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }else{
            return Response.status(400) //200
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }

    }





    /*
    CRUD for User
     */

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

    /*
    CRUD for Note
     */

}
