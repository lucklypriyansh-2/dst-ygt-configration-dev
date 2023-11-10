package com.yara.quarkus.starter.exception

import com.yara.quarkus.starter.model.DppError
import com.yara.quarkus.starter.model.DppErrorResponse
import org.jboss.logging.Logger.getLogger
import javax.ws.rs.WebApplicationException
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.status
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class ErrorMapper : ExceptionMapper<Exception> {

    private val logger = getLogger(this.javaClass)

    private val errorMessageInternalServerError = "Internal server error."

    override fun toResponse(exception: Exception?): Response {
        val statusCode = 500
        val dppError = DppError(
            detail = "Internal DPP API Error.",
            param = "Something went wrong on our end. Please try again later.",
            status = statusCode
        )
        return when (exception) {
            is WebApplicationException -> {
                val code = exception.response.status
                val statusInfo = exception.response.statusInfo.reasonPhrase
                val message = "$errorMessageInternalServerError Status code: $code: Detail: $statusInfo"
                logger.error(message, exception)

                status(statusCode).entity(dppError).build()
            }

            is ApiException -> {
                val message =
                    "$errorMessageInternalServerError Status code: ${exception.statusCode}: Detail: ${exception.message}"
                logger.error(message, exception)

                status(statusCode).entity(dppError).build()
            }

            is InvalidRequestException -> {
                val errorResponse = DppErrorResponse(exception.dppError)

                status(400).entity(errorResponse).build()
            }

            else -> {
                val message = "$errorMessageInternalServerError Detail: ${exception?.message}"
                logger.error(message, exception)

                status(statusCode).entity(dppError).build()
            }
        }
    }
}
