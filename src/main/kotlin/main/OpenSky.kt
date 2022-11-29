package main

import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import main.statevector.event.StateVectorRepositoryUpdateObserver
import main.statevector.repository.StateVectorRepository
import main.statevector.service.StateVectorPollingService

class OpenSky {
    companion object {
        /**
         * Debug mode
         */
        const val IS_DEBUG = false
    }
}

fun main() {
    StateVectorRepository.getUpdateEvent()
        .observeOn(Schedulers.io())
        .subscribe(StateVectorRepositoryUpdateObserver())

    runBlocking {
        launch {
            // start polling
            StateVectorPollingService().startPolling()
        }

    }
}
