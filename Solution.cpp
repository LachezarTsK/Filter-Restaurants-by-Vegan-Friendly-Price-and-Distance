
#include <span>
#include <vector>
#include <ranges>
using namespace std;

class Solution {

    static const int VEGAN_FRIENDLY_INDICATOR = 1;
    static const int NOT_VEGAN_FRIENDLY_INDICATOR = 0;

public:
    vector<int> filterRestaurants(const vector<vector<int>>& restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        vector<vector<int>> filteredRestaurants = createFilteredRestaurants(restaurants, veganFriendly, maxPrice, maxDistance);

        vector<int> idFilteredRestaurants;
        idFilteredRestaurants.reserve(filteredRestaurants.size());
        for (const auto& restaurant : filteredRestaurants) {
            int id = restaurant[0];
            idFilteredRestaurants.push_back(id);
        }
        return idFilteredRestaurants;
    }

private:
    const inline auto static comparator = [](const vector<int>& first, const vector<int>& second) {
        int idFirst = first[0];
        int idSecond = second[0];
        int ratingFirst = first[1];
        int ratingSecond = second[1];

        if (ratingFirst == ratingSecond) {
            return idSecond < idFirst;
        }
        return ratingSecond < ratingFirst;
    };

    vector<vector<int>> createFilteredRestaurants(span<const vector<int>> restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        vector<vector<int>> filteredRestaurants;

        for (const auto& restaurant : restaurants) {
            int id = restaurant[0];
            int rating = restaurant[1];
            int veganFriendlyIndicator = restaurant[2];

            bool meetsVeganCrieria = ((veganFriendly == VEGAN_FRIENDLY_INDICATOR)
                                     && (veganFriendlyIndicator == VEGAN_FRIENDLY_INDICATOR))
                                     || (veganFriendly == NOT_VEGAN_FRIENDLY_INDICATOR);
            bool isNotAboveMaxPrice = restaurant[3] <= maxPrice;
            bool isNotAboveMaxDistance = restaurant[4] <= maxDistance;

            if (meetsVeganCrieria && isNotAboveMaxPrice && isNotAboveMaxDistance) {
                    filteredRestaurants.push_back({ id, rating });
            }
        }
        ranges::sort(filteredRestaurants, comparator);

        return filteredRestaurants;
    }
};
