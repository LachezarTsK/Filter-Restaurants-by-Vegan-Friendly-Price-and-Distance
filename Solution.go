
package main
import "slices"

const VEGAN_FRIENDLY_INDICATOR = 1
const NOT_VEGAN_FRIENDLY_INDICATOR = 0

func filterRestaurants(restaurants [][]int, veganFriendly int, maxPrice int, maxDistance int) []int {
    filteredRestaurants := createFilteredRestaurants(restaurants, veganFriendly, maxPrice, maxDistance)

    idFilteredRestaurants := []int{}
    for _, restaurant := range filteredRestaurants {
        id := restaurant[0]
        idFilteredRestaurants = append(idFilteredRestaurants, id)
    }
    return idFilteredRestaurants
}

func comparator(first []int, second []int) int {
    idFirst := first[0]
    idSecond := second[0]
    ratingFirst := first[1]
    ratingSecond := second[1]

    if ratingFirst == ratingSecond {
        return idSecond - idFirst
    }
    return ratingSecond - ratingFirst
}

func createFilteredRestaurants(restaurants [][]int, veganFriendly int, maxPrice int, maxDistance int) [][]int {
    filteredRestaurants := [][]int{}

    for _, restaurant := range restaurants {
        id := restaurant[0]
        rating := restaurant[1]
        veganFriendlyIndicator := restaurant[2]

        meetsVeganCrieria := ((veganFriendly == VEGAN_FRIENDLY_INDICATOR) &&
                             (veganFriendlyIndicator == VEGAN_FRIENDLY_INDICATOR)) ||
                             (veganFriendly == NOT_VEGAN_FRIENDLY_INDICATOR)
        isNotAboveMaxPrice := restaurant[3] <= maxPrice
        isNotAboveMaxDistance := restaurant[4] <= maxDistance

        if meetsVeganCrieria && isNotAboveMaxPrice && isNotAboveMaxDistance {
            filteredRestaurants = append(filteredRestaurants, []int{id, rating})
        }
    }
    slices.SortFunc(filteredRestaurants, func(first []int, second []int) int { return comparator(first, second) })

    return filteredRestaurants
}
