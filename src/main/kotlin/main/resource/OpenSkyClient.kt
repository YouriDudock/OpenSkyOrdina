package main.resource

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import main.OpenSky
import resource.response.OpenSkyStateVectorResponse
import java.io.File

class OpenSkyClient {

    private val client = HttpClient()

    /**
     * Fetches all the VectorStates
     */
    suspend fun getAllStates(): OpenSkyStateVectorResponse {
        return if (OpenSky.IS_DEBUG) {
            // get from local file
            Json.decodeFromString(File("src/main/resources/payload.json").readText())
        } else {
            // get from API
            val jsonBody = client.get(OPEN_SKY_API_STATE_ALL_URL).body<String>()
            Json.decodeFromString(jsonBody)
        }
    }

    companion object {
        const val OPEN_SKY_API_STATE_ALL_URL = "https://opensky-network.org/api/states/all"
    }
}