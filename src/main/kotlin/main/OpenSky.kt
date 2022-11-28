package main

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import main.data.Country

class OpenSky {
    companion object {
        /**
         * Debug mode
         */
        const val IS_DEBUG = false
    }
}

fun main() {
    val stateVectorService = StateVectorService()

    runBlocking {
        launch {
            // start polling
            StateVectorPollingService().startPolling()
        }
        launch {
            while (true) {
                delay(StateVectorPollingService.POLLING_INTERVAL)

                 stateVectorService.showVectorsByOrginCountrySorted(3)
                 stateVectorService.showAmountOfVectorsAboveCountry(Country.NETHERLANDS)
                 stateVectorService.showAltitudeSlicesOfVectors()
            }
        }
    }

}