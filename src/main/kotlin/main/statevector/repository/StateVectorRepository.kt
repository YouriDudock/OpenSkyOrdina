package main.statevector.repository

import main.statevector.domain.StateVector

object StateVectorRepository {

    private var stateVectors = listOf<StateVector>()

    fun update(stateVectors: List<StateVector>) {
        StateVectorRepository.stateVectors = stateVectors
    }

    /**
     * Returns all state vectors without reference
     */
    fun getAllStateVectors() = stateVectors.toList()
}