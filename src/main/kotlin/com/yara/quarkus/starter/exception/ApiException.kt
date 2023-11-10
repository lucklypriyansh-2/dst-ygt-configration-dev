package com.yara.quarkus.starter.exception

import com.yara.quarkus.starter.model.DppError
import io.quarkus.runtime.annotations.RegisterForReflection

/**
 * An exception that represents an internal problem with DPP API servers.
 */

@RegisterForReflection
class ApiException(
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
