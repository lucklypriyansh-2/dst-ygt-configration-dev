package com.yara.quarkus.starter.exception

import com.yara.quarkus.starter.model.DppError

/**
 * A base class for DPP-related exceptions.
 */

abstract class DppException(
    val dppError: DppError,
    val requestId: String = "",
    val statusCode: Int = 0,
    message: String = dppError.detail,
    cause: Throwable? = null
) : Exception(message, cause) {

    override fun toString(): String {
        return listOfNotNull(
            dppError.let { "Error: $it" },
            requestId.let { "Request ID: $it" },
            statusCode.let { "Status Code: $it" },
            super.toString()
        ).joinToString(separator = "\n").trim()
    }
}
