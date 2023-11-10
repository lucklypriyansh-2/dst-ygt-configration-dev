package com.yara.quarkus.starter.exception

import com.yara.quarkus.starter.model.DppError
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ApiExceptionTest {

    @Test
    fun `When throwing an ApiException then a populated exception should be returned`() {
        val dppError = DppError(
            detail = "Internal server error",
            param = "Something went wrong on our end. Please try again later.",
            status = 500
        )
        val requestId = "req_123"
        val statusCode = 500
        val message = "Query parameter tag_name is required."
        val cause = RuntimeException()

        val apiException = ApiException(dppError, requestId, statusCode, message, cause)

        Assertions.assertEquals(dppError, apiException.dppError)
        Assertions.assertEquals(requestId, apiException.requestId)
        Assertions.assertEquals(statusCode, apiException.statusCode)
        Assertions.assertEquals(message, apiException.message)
        Assertions.assertEquals(cause, apiException.cause)
    }

    @Test
    fun `When calling toString() then a custom message should be returned`() {
        val dppError = DppError(
            detail = "Invalid request",
            param = "The request was invalid.",
            status = 400
        )
        val requestId = "req_123"
        val statusCode = 400
        val message = ""
        val cause = RuntimeException()

        val expected =
            """
            Error: DppError(category=API_ERROR, code=INTERNAL_SERVER_ERROR, detail=Invalid request, param=The request was invalid., status=400)
            Request ID: req_123
            Status Code: 400
            com.yara.quarkus.starter.exception.ApiException:
            """.trimIndent()
        val apiException = ApiException(dppError, requestId, statusCode, message, cause)

        Assertions.assertEquals(expected, apiException.toString())
    }
}
