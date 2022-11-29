package main.statevector.event

import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import main.statevector.domain.Country
import main.statevector.domain.StateVector
import main.statevector.service.StateVectorService

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