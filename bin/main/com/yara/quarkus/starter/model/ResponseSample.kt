package com.yara.quarkus.starter.model

import com.fasterxml.jackson.annotation.JsonProperty

data class ResponseSample(

    @field: JsonProperty("message")
    val message: String
)
