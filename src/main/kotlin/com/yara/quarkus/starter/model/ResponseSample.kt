package com.yara.quarkus.starter.model

import com.fasterxml.jackson.annotation.JsonProperty
import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
data class ResponseSample(

    @field: JsonProperty("message")
    val message: String
)
