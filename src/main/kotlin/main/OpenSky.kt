package main

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import main.statevector.domain.Country
import main.statevector.service.StateVectorPollingService
import main.statevector.service.StateVectorService

class OpenSky {
    companion object {
        /**
         * Debug mode
         */
        const val IS_DEBUG = true
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