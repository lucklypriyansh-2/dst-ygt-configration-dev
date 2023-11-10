package com.yara.quarkus.starter.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.yara.quarkus.starter.model.ErrorCategory.API_ERROR
import com.yara.quarkus.starter.model.ErrorCode.INTERNAL_SERVER_ERROR
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class DppError(

    /**
     * The high-level category of error.
     */
    @field:JsonProperty("category")
    val category: ErrorCategory = API_ERROR,

    /**
     * For errors that can be handled programmatically, a short string indicating the error code.
     */
    @field:JsonProperty("code")
    val code: ErrorCode = INTERNAL_SERVER_ERROR,

    /**
     * A human-readable description of the error for debugging purposes.
     */
    @field:JsonProperty("detail")
    val detail: String,

    /**
     * If the error is parameter-specific, the parameter related to the error.
     */
    @field:JsonProperty("param")
    val param: String,

    /**
     * The HTTP status.
     */
    @field:JsonProperty("status")
    val status: Int
)
