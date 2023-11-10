package com.yara.quarkus.starter.resource

import io.restassured.RestAssured.given
import org.hamcrest.core.IsEqual.equalTo

// @QuarkusTest
class ResourceSampleTest {

//    @Test
    fun testHelloEndpoint() {
        given()
            .pathParam("name", "DPP")
            .`when`().get("/greeting/hello/{name}")
            .then()
            .statusCode(200)
            .body("message", equalTo("hello"))
    }
}
