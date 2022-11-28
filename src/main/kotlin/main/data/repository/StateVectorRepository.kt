package main.data.repository

import main.domain.StateVector

object StateVectorRepository {

    private var stateVectors = listOf<StateVector>()

    fun update(stateVectors: List<StateVector>) {
        this.stateVectors = stateVectors
    }

    /**
     * Returns all state vectors without reference
     */
    fun getAllStateVectors() = stateVectors.toList()
}