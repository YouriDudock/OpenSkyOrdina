package main.statevector.domain

/**
 * Doc: https://openskynetwork.github.io/opensky-api/rest.html
 */
data class StateVector(
    val icao24: String,

    val callsign: String?,

    val orginCountry: String,

    val timePosition: Int?,

    val lastContact: Int,

    val longitude: Float?,

    val latitude: Float?,

    val baroAltitude: Float?,

    val onGround: Boolean,

    val velocity: Float?,

    val trueTrack: Float?,

    val verticalRate: Float?,

    val sensors: Float?,

    val geoAltitude: Float?,

    val squawk: String?,

    val spi: Boolean,

    //val positionSource: Int,

    val category: Int
)