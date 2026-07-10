
class Solution {

    private companion object {
        const val VEGAN_FRIENDLY_INDICATOR = 1
        const val NOT_VEGAN_FRIENDLY_INDICATOR = 0
    }

    fun filterRestaurants(restaurants: Array<IntArray>, veganFriendly: Int, maxPrice: Int, maxDistance: Int): List<Int> {
        val filteredRestaurants = createFilteredRestaurants(restaurants, veganFriendly, maxPrice, maxDistance)

        val idFilteredRestaurants = mutableListOf<Int>()
        for (restaurant in filteredRestaurants) {
            val id = restaurant[0]
            idFilteredRestaurants.add(id)
        }
        return idFilteredRestaurants
    }

    private fun comparator(first: IntArray, second: IntArray): Int {
        val idFirst = first[0]
        val idSecond = second[0]
        val ratingFirst = first[1]
        val ratingSecond = second[1]

        if (ratingFirst == ratingSecond) {
            return idSecond - idFirst
        }
        return ratingSecond - ratingFirst
    }

    private fun createFilteredRestaurants(restaurants: Array<IntArray>, veganFriendly: Int, maxPrice: Int, maxDistance: Int): MutableList<IntArray> {
        val filteredRestaurants = mutableListOf<IntArray>()

        for (restaurant in restaurants) {
            val id = restaurant[0]
            val rating = restaurant[1]
            val veganFriendlyIndicator = restaurant[2]

            val meetsVeganCrieria = ((veganFriendly == VEGAN_FRIENDLY_INDICATOR)
                                    && (veganFriendlyIndicator == VEGAN_FRIENDLY_INDICATOR))
                                    || (veganFriendly == NOT_VEGAN_FRIENDLY_INDICATOR)
            val isNotAboveMaxPrice = restaurant[3] <= maxPrice
            val isNotAboveMaxDistance = restaurant[4] <= maxDistance

            if (meetsVeganCrieria && isNotAboveMaxPrice && isNotAboveMaxDistance) {
                filteredRestaurants.add(intArrayOf(id, rating))
            }
        }
        filteredRestaurants.sortWith() { first, second -> comparator(first, second) }

        return filteredRestaurants
    }
}
