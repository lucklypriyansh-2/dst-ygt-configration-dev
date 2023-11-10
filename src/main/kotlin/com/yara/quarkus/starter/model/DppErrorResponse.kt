package com.yara.quarkus.starter.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class DppErrorResponse(

    @field:JsonProperty("error")
    val error: DppError
)
