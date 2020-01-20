package endpoints;

import REST_calls.PostUsergame;
import com.google.gson.Gson;
import entities.Game;
import entities.Note;
import entities.User;
import logic.GameField;
import logic.Machine;

import javax.print.attribute.standard.Media;
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



    @POST
    @Path("/usergame")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newUsername(PostUsergame postUsergame){
        return machine.winCheck(postUsergame);

    }

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
            @PathParam("gameid") int gameid) {
        String msg = machine.deleteGame(gameid);
        if (msg.equals("Succesfully deleted")) {
            return Response.ok() //200
                    .entity(msg)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        } else {
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
    public Response postGame(boolean [][] field) {
        ArrayList<Game> games = machine.postGame(field);

        if (games != null) {
            return Response.ok() //200
                    .entity(gson.toJson(games))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        } else {
            return Response.status(400) //200
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS").build();
        }
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


    @GET
    @Path("/note/{userid}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes( @PathParam("userid") int userID){

        ArrayList<Note> notes = machine.getNotesByUser(userID);

        return Response.ok() //200
                .entity(gson.toJson(notes))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();

    }





    @DELETE
    @Path("/note/{noteid}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteNote( @PathParam("noteid") int noteID){

        machine.deleteNote(noteID);

        return Response.ok() //200
                .entity("hoi")
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT")
                .allow("OPTIONS").build();

    }


}
