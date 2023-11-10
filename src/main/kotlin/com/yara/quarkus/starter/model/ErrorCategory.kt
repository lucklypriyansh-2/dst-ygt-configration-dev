package com.yara.quarkus.starter.model

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
enum class ErrorCategory {
    API_ERROR,
    INVALID_REQUEST_ERROR
}
