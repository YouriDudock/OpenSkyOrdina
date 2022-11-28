package resource.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive

/**
 * A response from Open Sky API
 */
@Serializable
data class OpenSkyStateVectorResponse(
        val time: Long,
        val states: List<List<JsonPrimitive>>
)