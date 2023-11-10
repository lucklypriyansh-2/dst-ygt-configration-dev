package com.yara.quarkus.starter.exception

import com.yara.quarkus.starter.model.DppError
import io.quarkus.runtime.annotations.RegisterForReflection

/**
 * An exception indicating that invalid parameters were used in the request.
 */

@RegisterForReflection
class InvalidRequestException(
    dppError: DppError,
    requestId: String = "",
    statusCode: Int = 0,
    message: String = dppError.detail,
    cause: Throwable? = null
) : DppException(
    dppError = dppError,
    requestId = requestId,
    statusCode = statusCode,
    message = message,
    cause = cause
)
