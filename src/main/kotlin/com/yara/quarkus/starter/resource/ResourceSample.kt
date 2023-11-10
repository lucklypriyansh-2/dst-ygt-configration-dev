package com.yara.quarkus.starter.resource

import com.yara.quarkus.starter.model.ResponseSample
import com.yara.quarkus.starter.service.ServiceSample
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

@Path("/assets")
@Produces(APPLICATION_JSON)
class ResourceSample(private val serviceSample: ServiceSample) {

    @GET
    @Path("/{site_ids}")
    fun getSites(@QueryParam("site_ids") siteIds: String): ResponseSample = ResponseSample("Hello $siteIds")

    @GET
    @Path("/error/{site_id}")
    fun getError(@QueryParam("site_ids") siteIds: String): Response = serviceSample.siteError(siteIds)
}
