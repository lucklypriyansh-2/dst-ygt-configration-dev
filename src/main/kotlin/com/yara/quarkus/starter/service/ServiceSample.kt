package com.yara.quarkus.starter.service

import arrow.core.Validated
import arrow.core.invalid
import arrow.core.valid
import com.yara.quarkus.starter.common.ValidationError
import com.yara.quarkus.starter.common.ValidationSuccess
import com.yara.quarkus.starter.common.Validator
import com.yara.quarkus.starter.common.validateIsRequired
import com.yara.quarkus.starter.common.validationErrorsToResponse
import com.yara.quarkus.starter.mapper.handleSuccess
import com.yara.quarkus.starter.model.ErrorCode.INVALID_PARAMETER
import com.yara.quarkus.starter.model.ResponseSample
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response

const val SITE_ID: String = "SiteId"

@ApplicationScoped
class ServiceSample {

    fun siteError(siteId: String): Response {
        val validatedSiteIdLength = validateSiteIdLength(siteId)
        val validatedSiteId = validateIsRequired(ValidationError(INVALID_PARAMETER, SITE_ID), siteId)
        return Validator(validatedSiteId, validatedSiteIdLength).fold(
            { failure ->
                validationErrorsToResponse(*failure.toTypedArray())
            },
            { success ->
                val siteIds = success.first { it.parameterName == SITE_ID }
                handleSuccess(ResponseSample("Hello ${siteIds.value}"))
            }
        )
    }

    private fun validateSiteIdLength(siteId: String): Validated<ValidationError, ValidationSuccess<String>> =
        if (siteId.length != 3) {
            ValidationError(
                INVALID_PARAMETER,
                SITE_ID,
                "$siteId is not a valid production site ID."
            ).invalid()
        } else {
            ValidationSuccess(SITE_ID, siteId).valid()
        }
}
