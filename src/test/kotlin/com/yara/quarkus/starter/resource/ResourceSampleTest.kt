package com.yara.quarkus.starter.resource

import io.restassured.RestAssured.given
import org.hamcrest.core.IsEqual.equalTo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
// @QuarkusTest
class ResourceSampleTest {

//    @Test
//    fun testHelloEndpoint() {
//
//        given()
//            .pathParam("name", "DPP")
//            .`when`().get("/greeting/hello/{name}")
//            .then()
//            .statusCode(69)
//            .body("message", equalTo("hello"))
//
//        given().`when`().get("/hello").then().statusCode(200).body("message", equalTo("hello"))
//    }
    @Test
    fun testHelloEndpoint() {
        given().get("/hello").then().statusCode(200).body("message", equalTo("Hello world"))
    }
}
