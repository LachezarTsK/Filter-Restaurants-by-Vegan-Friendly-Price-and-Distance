
/**
 * @param {number[][]} restaurants
 * @param {number} veganFriendly
 * @param {number} maxPrice
 * @param {number} maxDistance
 * @return {number[]}
 */
var filterRestaurants = function (restaurants, veganFriendly, maxPrice, maxDistance) {
    const filteredRestaurants = createFilteredRestaurants(restaurants, veganFriendly, maxPrice, maxDistance);
    const idFilteredRestaurants = new Array();
    for (let restaurant of filteredRestaurants) {
        const id = restaurant[0];
        idFilteredRestaurants.push(id);
    }
    return idFilteredRestaurants;
};

/**
 * @param {number[]} first
 * @param {number[]} second
 * @return {number}
 */
function comparator(first, second) {
    const idFirst = first[0];
    const idSecond = second[0];
    const ratingFirst = first[1];
    const ratingSecond = second[1];

    if (ratingFirst === ratingSecond) {
        return idSecond - idFirst;
    }
    return ratingSecond - ratingFirst;
}

/**
 * @param {number[][]} restaurants
 * @param {number} veganFriendly
 * @param {number} maxPrice
 * @param {number} maxDistance
 * @return {number[][]}
 */
function createFilteredRestaurants(restaurants, veganFriendly, maxPrice, maxDistance) {
    const VEGAN_FRIENDLY_INDICATOR = 1;
    const NOT_VEGAN_FRIENDLY_INDICATOR = 0;
    const filteredRestaurants = new Array();

    for (let restaurant of restaurants) {
        const id = restaurant[0];
        const rating = restaurant[1];
        const veganFriendlyIndicator = restaurant[2];

        const meetsVeganCrieria = ((veganFriendly === VEGAN_FRIENDLY_INDICATOR)
                                  && (veganFriendlyIndicator === VEGAN_FRIENDLY_INDICATOR))
                                  || (veganFriendly === NOT_VEGAN_FRIENDLY_INDICATOR);
        const isNotAboveMaxPrice = restaurant[3] <= maxPrice;
        const isNotAboveMaxDistance = restaurant[4] <= maxDistance;

        if (meetsVeganCrieria && isNotAboveMaxPrice && isNotAboveMaxDistance) {
            filteredRestaurants.push([id, rating]);
        }
    }
    filteredRestaurants.sort((first, second) => comparator(first, second));

    return filteredRestaurants;
}
