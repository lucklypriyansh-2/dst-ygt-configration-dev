/*
 * Copyright 2022 Yara International ASA. All rights reserved.
 */

package com.yara.quarkus.starter.connector

/**
 * A Service Connector makes services easier to use by hiding the specifics of communications-related APIs.
 * Connectors encapsulate the generic communications-related logic required to use services, and also include logic that is quite specific to given services.
 * They are typically responsible for service location and connection management, request dispatch, response handling (i.e. receipt), and some error handling.
 */

class ConnectorSample {
    fun sendAndReceive(): Any = TODO("implement communication-related logic.")
}
