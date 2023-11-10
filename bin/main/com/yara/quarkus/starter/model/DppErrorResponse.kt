package com.yara.quarkus.starter.model

import com.fasterxml.jackson.annotation.JsonProperty

data class DppErrorResponse(

    @field:JsonProperty("error")
    val error: DppError
)
