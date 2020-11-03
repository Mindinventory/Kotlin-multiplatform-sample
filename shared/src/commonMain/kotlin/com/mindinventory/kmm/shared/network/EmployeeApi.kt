package com.mindinventory.kmm.shared.network

import com.mindinventory.kmm.shared.entity.Employee
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

object Api {
    // API url, you can even define with separation like base url, api path and api version
    const val API_ENDPOINT = "https://run.mocky.io/v3/4e809584-adb3-4b70-bfbb-b8843d3764e1"
}

class EmployeeApi {

    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getAllEmployee(): Employee {
        // simple get method API call, you can use post, delete etc. as per needs
        return httpClient.get(Api.API_ENDPOINT)
    }
}