package main.statevector.service

import main.statevector.repository.StateVectorRepository
import main.statevector.mapping.StateVectorMapper
import main.statevector.web.rest.OpenSkyClient
import kotlinx.coroutines.delay

/**
 * Responsible for polling new vector states
 */
class StateVectorPollingService {

    private val openSkyClient = OpenSkyClient()

    /**
     * Starts the polling process
     */
    suspend fun startPolling() {
        while (true) {
            println("polling..")

            // get all states from API
            val stateVectorResponse = openSkyClient.getAllStates()

            // map response and combine in one list
            val stateVectors = stateVectorResponse.states.map {
                StateVectorMapper.toVectorState(it)
            }

            // update repository
            StateVectorRepository.update(stateVectors)

            delay(POLLING_INTERVAL)
        }

    }

    companion object {
        /**
         * Polling interval in ms
         */
        const val POLLING_INTERVAL = 10000L

        /**
         * Polling interval as seconds
         */
        const val POLLING_INTERVAL_IN_SECONDS = POLLING_INTERVAL / 1000
    }
}