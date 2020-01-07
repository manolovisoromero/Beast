package endpoints;

import logic.GameField;
import logic.Machine;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/resources")
public class ResourceEndpoint {

    Machine machine = Machine.getInstance();




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
    //@AuthenticationEndpoint.Secured
    @Path("/game/{userID}")
    public Response getNewGame(@PathParam("userID") int userID) {

        String game = machine.getUnplayedGame(userID);

        if (game == null) {
            return Response.status(400).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .entity("testGames")
                    .allow("OPTIONS").build();
        } else {
            return Response.status(200).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .entity(game)
                    .allow("OPTIONS").build();
        }
    }








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
