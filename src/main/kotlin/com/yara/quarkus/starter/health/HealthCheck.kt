package com.yara.quarkus.starter.health

import org.eclipse.microprofile.health.HealthCheckResponse
import org.eclipse.microprofile.health.Liveness
import org.eclipse.microprofile.health.Readiness
import javax.enterprise.context.ApplicationScoped

@Readiness
@Liveness
@ApplicationScoped
class HealthCheck {
    fun checkHealth(): HealthCheckResponse = HealthCheckResponse.up("Quarkus starter microservice ready")
}
