package com.yara.quarkus.starter.common

import arrow.core.Validated
import arrow.core.invalid
import arrow.core.valid
import com.yara.quarkus.starter.mapper.handleError
import com.yara.quarkus.starter.model.ErrorCategory.INVALID_REQUEST_ERROR
import com.yara.quarkus.starter.model.ErrorCode
import com.yara.quarkus.starter.model.ErrorCode.INVALID_PARAMETER
import software.amazon.awssdk.services.iotsitewise.model.ErrorDetails
import javax.ws.rs.core.Response

data class ValidationSuccess<T>(val parameterName: String? = null, val value: T)
data class ValidationError(
    val errorCode: ErrorCode = INVALID_PARAMETER,
    val parameterName: String? = null,
    val description: String = "cannot be empty"
)

fun validateIsRequired(error: ValidationError, value: String?): Validated<ValidationError, ValidationSuccess<String>> =
    if (value.isNullOrBlank()) {
        error.invalid()
    } else {
        ValidationSuccess(error.parameterName, value).valid()
    }

class Validator<out E, out F>(vararg vs: Validated<E, F>?) {

    private val successValues: List<F> = vs.filterNotNull().filter(Validated<E, F>::isValid).map { it.getValidData() }
    private val failuresList: List<E> =
        vs.filterNotNull().filter(Validated<E, F>::isInvalid).map { it.swap().getValidData() }
    private val hasFailures = failuresList.isNotEmpty()

    /**
     * Folds input validated to return error in left side and success in right side
     **/
    fun <T> fold(failure: (List<E>) -> T, success: (List<F>) -> T): T =
        if (hasFailures) failure(failuresList) else success(successValues)
}

fun validationErrorsToResponse(vararg errorsList: ValidationError): Response {
    val sErr = errorsList.first() // since current api supports sending only single error and not entire list
    return handleError(
        com.yara.quarkus.starter.model.ErrorDetails(
            category = INVALID_REQUEST_ERROR,
            code = sErr.errorCode,
            detail = sErr.description,
            status = Response.Status.BAD_REQUEST.statusCode,
            param = sErr.parameterName
        ),
        status = Response.Status.BAD_REQUEST
    )
}
