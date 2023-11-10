package com.yara.quarkus.starter.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class ErrorDetails(

    @field:JsonProperty
    val category: ErrorCategory,

    @field:JsonProperty
    val code: ErrorCode,

    @field:JsonProperty
    val detail: String?,

    @field:JsonProperty
    val status: Int,

    @field:JsonProperty
    val param: String? = null
)
