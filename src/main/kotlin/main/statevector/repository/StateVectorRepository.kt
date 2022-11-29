package main.statevector.repository

import main.statevector.domain.StateVector

object StateVectorRepository {

    private val stateVectors = mutableListOf<StateVector>()

    /**
     * Updates the state vector repository with new or updated entries
     */
    fun update(updatedStateVectors: List<StateVector>) {
        // iterate updated state vectors
        updatedStateVectors.forEach { updatedStateVector ->

            // check if state vector already exists based on id
            val vector = stateVectors.find {
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
    }

    /**
     * Returns all state vectors without reference
     */
    fun getAll() = stateVectors.toList()

}