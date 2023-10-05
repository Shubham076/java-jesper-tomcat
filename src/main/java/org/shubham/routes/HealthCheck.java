package org.shubham.routes;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

@Path("/health")
public class HealthCheck {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response sayHello() {
        JSONObject resp = new JSONObject();
        resp.put("message", "Healthy");
        resp.put("status", 200);
        return Response.status(200).entity(resp.toString()).build();
    }
}
