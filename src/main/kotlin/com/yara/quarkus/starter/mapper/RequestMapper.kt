package com.yara.quarkus.starter.mapper

/**
 * Request Mappers are a specialization of the Mapper pattern.
 * They are used to isolate request structures from domain layer entities and enable each to evolve independently.
 * Request Mappers are responsible for parsing data from requests and moving this data into domain entities/models.
 *
 * Request mapper pattern: http://www.servicedesignpatterns.com/RequestAndResponseManagement/RequestMapper
 *
 */

class RequestMapper {

    fun toRequest(objectToMap: Any): Any = TODO("implement request mapping logic")
}
