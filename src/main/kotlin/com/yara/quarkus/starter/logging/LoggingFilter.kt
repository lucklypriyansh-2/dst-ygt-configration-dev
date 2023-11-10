package com.yara.quarkus.starter.logging

import io.vertx.core.http.HttpServerRequest
import org.jboss.logging.Logger
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.core.Context
import javax.ws.rs.core.UriInfo
import javax.ws.rs.ext.Provider

@Provider
class LoggingFilter : ContainerRequestFilter {

    private val logger = Logger.getLogger(this.javaClass)

    @Context
    lateinit var info: UriInfo

    @Context
    lateinit var request: HttpServerRequest

    override fun filter(context: ContainerRequestContext?) {
        val method = context?.method
        val path = info.path
        val address = request.absoluteURI()

//        logger.info("Request $method $path from IP $address")
        logger.info("HTTP $method $address")
    }
}
