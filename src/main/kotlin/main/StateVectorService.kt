package main

import main.data.Country
import main.data.repository.StateVectorRepository
import main.domain.StateVector
import kotlin.math.ceil

/**
 * Service providing func for StateVector
 */
class StateVectorService {

    /**
     * Sort StateVectors by Orgin Country
     */
    fun showVectorsByOrginCountrySorted(limit: Int) {
        val stateVectors = StateVectorRepository.getAllStateVectors()

        // sort to frequency of orgin country in vectors
        val frequencyCountryVectors = stateVectors.groupingBy { it.orginCountry }
            .eachCount()
            .toList()
            .sortedByDescending { (_, value) -> value }
            .toMap()
            .toList()

        println("Top $limit orgin countries:")

        // iterate through sorted list from top
        repeat(limit) { index ->
            frequencyCountryVectors.getOrNull(index)?.let {
                println(it.toString())
            }
        }
    }

    /**
     * Show StateVectors above a specific country
     */
    fun showAmountOfVectorsAboveCountry(country: Country) {
        // filter all state vectors based on lat & long
        val stateVectors = StateVectorRepository.getAllStateVectors().filter {
            it.latitude != null && it.longitude != null &&
                    it.latitude >= country.lamin && it.latitude <= country.lamax &&
                    it.longitude >= country.lomin && it.longitude <= country.lomax
        }

        // show count
        println("State vectors currently above ${country.name}: ${stateVectors.count()}")
    }

    /**
     * Show StateVector altitude slices with automatic range creation
     */
    fun showAltitudeSlicesOfVectors() {
        val activeStateVectors = StateVectorRepository.getAllStateVectors()

        // filter and sort by altitude
        val altitudeSortedVectors = activeStateVectors.filter { it.geoAltitude != null }
            .sortedByDescending { it.geoAltitude }

        // create amount of slices based on the highest vectors altitude
        // we use division to calculate how many total slices we must create
        val slices = altitudeSortedVectors.firstOrNull()?.let {
            if (it.geoAltitude != null) ceil(it.geoAltitude.div(SLICE_RANGE)).toInt() else 0
        } ?: 0

        // create ranges based on the slices count
        val ranges = (0..slices).map { slice -> slice * SLICE_RANGE..((slice + 1) * SLICE_RANGE).minus(1) }

        // show vectors
        showStateVectorsBetweenAltitudeRanges(altitudeSortedVectors, *ranges.toTypedArray())
    }

    /**
     * Show state vectors between a certain slice(s)
     */
    private fun showStateVectorsBetweenAltitudeRanges(
        stateVectors: List<StateVector>,
        vararg altitudeRanges: IntRange
    ) {
        altitudeRanges.forEach { range ->
            println("Range ${range.first} to ${range.last} contains state vectors:")

            stateVectors.forEach { vector ->
                // check if vector is in range
                if (vector.geoAltitude?.toInt() in range) {

                    // check if vertical rate exists for predictions
                    if (vector.verticalRate != null) {
                        // predict if the vector will stay in this range based on vertical rate and polling frequency
                        val isPredictedToStayInRange = vector.geoAltitude?.plus(
                            vector.verticalRate.times(StateVectorPollingService.POLLING_INTERVAL_IN_SECONDS)
                        )?.toInt() in range

                        // check if in range
                        if (!isPredictedToStayInRange) {
                            print(" WARNING-")
                        }
                    }
                    // print id of vector
                    print(vector.icao24)
                }
            }

            println()
        }
    }

    companion object {
        /**
         * How large a range is
         */
        const val SLICE_RANGE = 1000
    }
}