package main.statevector.repository

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import main.statevector.domain.StateVector

object StateVectorRepository {

    private val stateVectors = mutableListOf<StateVector>()

    private val updatePublishSubject: PublishSubject<List<StateVector>> = PublishSubject.create()

    /**
     * Updates the state vector repository with new or updated entries
     */
    fun update(updatedStateVectors: List<StateVector>) {
        // iterate updated state vectors
        updatedStateVectors.forEach { updatedStateVector ->

            // check if state vector already exists based on id
            val vector = stateVectors.toList().find {
                it.icao24 == updatedStateVector.icao24
            }

            if (vector != null) {
                // update known state vector with updated one
                stateVectors[stateVectors.indexOf(vector)] = updatedStateVector
            } else {
                // add new vector to the list
                stateVectors.add(updatedStateVector)
            }
        }

        // publish update event
        updatePublishSubject.onNext(stateVectors)
    }

    /**
     * Returns all state vectors without reference
     */
    fun getAll() = stateVectors.toList()

    /**
     * Returns the Observable when a repository update has happened
     */
    fun getUpdateEvent(): Observable<List<StateVector>> {
        return updatePublishSubject.hide()
    }
}