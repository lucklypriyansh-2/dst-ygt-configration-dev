package com.yara.quarkus.starter.health

import org.eclipse.microprofile.health.HealthCheckResponse
import org.eclipse.microprofile.health.Readiness
import javax.enterprise.context.ApplicationScoped

@Readiness
@ApplicationScoped
class HealthCheck {

    fun checkHealth(): HealthCheckResponse {
        return HealthCheckResponse.up("Quarkus starter microservice ready")
    }
}
