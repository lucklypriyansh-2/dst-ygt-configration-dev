package com.yara.quarkus.starter.config

import arrow.core.Either
import arrow.core.Either.Companion.catch
import com.yara.quarkus.starter.exception.ApiException
import com.yara.quarkus.starter.model.DppError
import org.jboss.logging.Logger.getLogger
import software.amazon.awssdk.services.ssm.SsmClient
import software.amazon.awssdk.services.ssm.model.GetParameterRequest
import javax.enterprise.context.ApplicationScoped

/**
 * Provides application configuration stored in the environment using SSM Parameter Store.
 */

@ApplicationScoped
class ApplicationConfig(private val ssmClient: SsmClient) {

    private val logger = getLogger(this.javaClass)

    fun forParameterName(paramName: String): Either<ApiException, String> = catch {
        if (paramName.isEmpty()) {
            ""
        } else {
            getParameterValue(paramName)
        }
    }.mapLeft { throwable ->

        val message =
            "Unable to retrieve configuration for parameter name: $paramName from AWS SSM Parameter Store."
        logger.error(message, throwable)

        val dppError = DppError(
            detail = "Internal server error",
            param = message,
            status = 500
        )

        ApiException(dppError, "N/A", 500, message, throwable)
    }

    private val getParameterValue: (String) -> String = { paramName ->
        val getParamResponse = ssmClient.getParameter(
            GetParameterRequest.builder().name(paramName).build()
        )
        getParamResponse.parameter().value()
    }
}
