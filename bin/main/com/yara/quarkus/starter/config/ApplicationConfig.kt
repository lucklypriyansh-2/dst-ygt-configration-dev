package com.yara.quarkus.starter.config

import com.yara.quarkus.starter.exception.ApiException
import com.yara.quarkus.starter.model.DppError
import com.yara.quarkus.starter.model.ErrorCategory
import com.yara.quarkus.starter.model.ErrorCode
import org.jboss.logging.Logger
import software.amazon.awssdk.services.ssm.SsmClient
import software.amazon.awssdk.services.ssm.model.GetParameterRequest
import software.amazon.awssdk.services.ssm.model.SsmException
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.inject.Default
import javax.inject.Inject

/**
 * Provides application configuration stored in the environment using SSM Parameter Store.
 */

@ApplicationScoped
class ApplicationConfig {

    @field:Default
    @field:Inject
    lateinit var ssmClient: SsmClient

    @field:Default
    @field:Inject
    lateinit var logger: Logger

    fun forParameterName(paramName: String): String {
        try {
            return if (paramName.isEmpty()) {
                ""
            } else {
                val getParamResponse = ssmClient.getParameter(
                    GetParameterRequest.builder().name(paramName).build()
                )

                getParamResponse.parameter().value()
            }
        } catch (exception: SsmException) {
            val message =
                "Unable to retrieve configuration for parameter name: $paramName from AWS SSM Parameter Store."
            logger.error(message, exception)

            val dppError = DppError(
                ErrorCategory.API_ERROR,
                ErrorCode.INTERNAL_SERVER_ERROR,
                "Internal server error",
                message,
                500
            )

            throw ApiException(dppError, "N/A", 500, message, exception)
        }
    }
}
