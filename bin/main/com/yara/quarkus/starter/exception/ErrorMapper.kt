package com.yara.quarkus.starter.exception

import com.yara.quarkus.starter.model.DppError
import com.yara.quarkus.starter.model.DppErrorResponse
import com.yara.quarkus.starter.model.ErrorCategory
import com.yara.quarkus.starter.model.ErrorCode
import org.jboss.logging.Logger
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ErrorMapper : ExceptionMapper<Exception> {

    @field:Default
    @field:Inject
    lateinit var logger: Logger

    private val errorMessageInternalServerError = "Internal server error."

    override fun toResponse(exception: Exception?): Response {
        val statusCode = 500
        val dppError = DppError(
            ErrorCategory.API_ERROR,
            ErrorCode.INTERNAL_SERVER_ERROR,
            "Internal DPP API Error.",
            "Something went wrong on our end. Please try again later.",
            statusCode
        )
        when (exception) {
            is WebApplicationException -> {
                val code = exception.response.status
                val statusInfo = exception.response.statusInfo.reasonPhrase
                val message = "$errorMessageInternalServerError Status code: $code: Detail: $statusInfo"
                logger.error(message, exception)

                return Response.status(statusCode).entity(dppError).build()
            }
            is ApiException -> {
                val message =
                    "$errorMessageInternalServerError Status code: ${exception.statusCode}: Detail: ${exception.message}"
                logger.error(message, exception)

                return Response.status(statusCode).entity(dppError).build()
            }
            is InvalidRequestException -> {
                val errorResponse = DppErrorResponse(exception.dppError)

                return Response.status(400).entity(errorResponse).build()
            }
            else -> {
                val message = "$errorMessageInternalServerError Detail: ${exception?.message}"
                logger.error(message, exception)

                return Response.status(statusCode).entity(dppError).build()
            }
        }
    }
}
