package com.yara.quarkus.starter

import com.yara.quarkus.starter.model.ResponseSample
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/hello")
@Produces(APPLICATION_JSON)
class HelloSample {

    @GET @Path("/")
    fun helloWorld(): String {
        return "Hello, world!"
    }
}
