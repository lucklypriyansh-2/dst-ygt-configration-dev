package com.yara.quarkus.starter.mapper

import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR
import javax.ws.rs.core.Response.Status.OK

/**
 *
 * Response Mappers are a specialization of the Mapper pattern.
 * They consolidate the data retrieval, transformation, mapping, and serialization logic used to create a specific response or family of responses.
 * Response Mappers are often selected and instantiated directly by services, but other techniques (e.g. Inversion of Control) may be used as well.
 * Once a mapper has been instantiated, the service calls methods on the mapper to pass domain objects, Record Sets, structs, or primitive data
 *
 * Response mapper pattern: http://www.servicedesignpatterns.com/RequestAndResponseManagement/ResponseMapper
 *
 */

class ResponseMapper {

    fun fromResponse(response: Any): Any = TODO("implement response mapping logic.")
}

fun <T> handleError(entity: T, status: Response.Status = INTERNAL_SERVER_ERROR): Response = Response.status(
    status
).entity(entity).build()

fun <T> handleSuccess(entity: T, status: Response.Status = OK): Response = Response.status(status).entity(entity).build()
