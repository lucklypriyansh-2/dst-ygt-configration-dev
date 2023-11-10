package com.yara.quarkus.starter.model

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
enum class ErrorCode {
    INTERNAL_SERVER_ERROR,
    INVALID_SITE_ID,
    INVALID_TAG_NAME,
    NOT_FOUND,
    INVALID_PARAMETER
}
