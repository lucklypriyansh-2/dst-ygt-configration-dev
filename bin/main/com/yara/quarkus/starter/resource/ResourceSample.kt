package com.yara.quarkus.starter.resource

import com.yara.quarkus.starter.exception.InvalidRequestException
import com.yara.quarkus.starter.model.DppError
import com.yara.quarkus.starter.model.ErrorCategory
import com.yara.quarkus.starter.model.ErrorCode
import com.yara.quarkus.starter.model.ResponseSample
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType

@Path("/assets")
@Produces(MediaType.APPLICATION_JSON)
class ResourceSample {

    @GET
    @Path("/{site_ids}")
    fun getSites(@QueryParam("site_ids") siteIds: String): ResponseSample {
        return ResponseSample("Hello $siteIds")
    }

    @GET
    @Path("/error/{site_id}")
    fun getError(@QueryParam("site_ids") siteIds: String): ResponseSample {
        if (siteIds == null || siteIds.length != 3) {
            throw InvalidRequestException(
                DppError(
                    ErrorCategory.INVALID_REQUEST_ERROR,
                    ErrorCode.INVALID_SITE_ID,
                    "Site ID $siteIds is not a valid production site ID.",
                    "Invalid path param: site_id",
                    400
                ),
                "Req ID",
                400,
                "Invalid request parameter 'site_id' with value $siteIds"
            )
        }

        return ResponseSample("Hello $siteIds")
    }
}
