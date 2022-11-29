package main

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import main.statevector.domain.Country
import main.statevector.domain.StateVector
import main.statevector.repository.StateVectorRepository
import main.statevector.service.StateVectorPollingService
import main.statevector.service.StateVectorService

class OpenSky {
    companion object {
        /**
         * Debug mode
         */
        const val IS_DEBUG = false
    }

    fun t() {

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

class StateVectorRepositoryUpdateObserver : Observer<List<StateVector>> {

    private val stateVectorService = StateVectorService()

    override fun onSubscribe(d: Disposable) {}

    override fun onNext(t: List<StateVector>) {
        stateVectorService.showAmountOfVectorsAboveCountry(Country.NETHERLANDS)
        stateVectorService.showVectorsByOrginCountrySorted(3)
        stateVectorService.showAltitudeSlicesOfVectors()
    }

    override fun onError(e: Throwable) {}

    override fun onComplete() {}

}
