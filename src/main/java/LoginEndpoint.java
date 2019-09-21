import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/login")
public class LoginEndpoint {



    // region get
    @Path("/{login}")
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public Response getLogin(@PathParam("login") String name) {
        return Response.status(200).entity("Hello").build();
    }
}
