package com.yara.quarkus.starter.repository

import arrow.core.Either
import com.yara.quarkus.starter.config.ApplicationConfig
import com.yara.quarkus.starter.exception.ApiException
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import javax.enterprise.context.ApplicationScoped

/**
 * Sample code to fetch data from dynamo db
 * */
@ApplicationScoped
class RepositorySample(private val client: DynamoDbClient, private val applicationConfig: ApplicationConfig) {

    private fun getTableName(): Either<ApiException, String> =
        applicationConfig.forParameterName("<your-parameter-name-here>")

    fun getData() {
        getTableName().fold({
            TODO("handle exception here")
        }, {
            TODO("fetch data from dynamoDb client")
        })
    }
}
