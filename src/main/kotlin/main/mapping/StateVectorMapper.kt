package main.mapping

import kotlinx.serialization.json.*
import main.domain.StateVector

class StateVectorMapper {

    companion object {

        /**
         * Maps VectorStateResponse to VectorState
         */
        fun toVectorState(stateVectorValues: List<JsonPrimitive>) = StateVector(
            icao24 = stateVectorValues[StateVectorField.ICAEO24.ordinal].toString(),
            callsign = stateVectorValues[StateVectorField.CALLSIGN.ordinal].toString(),
            orginCountry = stateVectorValues[StateVectorField.ORGIN_COUNTRY.ordinal].toString(),
            timePosition = stateVectorValues[StateVectorField.TIME_POSITION.ordinal].intOrNull,
            lastContact = stateVectorValues[StateVectorField.LAST_CONTACT.ordinal].int,
            longitude = stateVectorValues[StateVectorField.LONGITUDE.ordinal].floatOrNull,
            latitude = stateVectorValues[StateVectorField.LATITUDE.ordinal].floatOrNull,
            baroAltitude = stateVectorValues[StateVectorField.BARO_ALTIUDE.ordinal].floatOrNull,
            onGround = stateVectorValues[StateVectorField.ON_GROUND.ordinal].boolean,
            velocity = stateVectorValues[StateVectorField.VELOCITY.ordinal].floatOrNull,
            trueTrack = stateVectorValues[StateVectorField.TRUE_TRACKER.ordinal].floatOrNull,
            verticalRate = stateVectorValues[StateVectorField.VERTICAL_RATE.ordinal].floatOrNull,
            sensors = stateVectorValues[StateVectorField.SENSORS.ordinal].floatOrNull,
            geoAltitude = stateVectorValues[StateVectorField.GEO_ALTITUDE.ordinal].floatOrNull,
            squawk = stateVectorValues[StateVectorField.SQUAWK.ordinal].toString(),
            spi = stateVectorValues[StateVectorField.SPI.ordinal].boolean,
            //positionSource = stateVectorValues[StateVectorField.POSITION_SOURCE.ordinal].int,
            category = stateVectorValues[StateVectorField.CATEGORY.ordinal].int,
        )
    }

    /**
     * All vector state fields, see: https://openskynetwork.github.io/opensky-api/rest.html
     */
    enum class StateVectorField {
        ICAEO24,
        CALLSIGN,
        ORGIN_COUNTRY,
        TIME_POSITION,
        LAST_CONTACT,
        LONGITUDE,
        LATITUDE,
        BARO_ALTIUDE,
        ON_GROUND,
        VELOCITY,
        TRUE_TRACKER,
        VERTICAL_RATE,
        SENSORS,
        GEO_ALTITUDE,
        SQUAWK,
        SPI,
        //POSITION_SOURCE,
        CATEGORY
    }
}