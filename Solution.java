
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private static final int VEGAN_FRIENDLY_INDICATOR = 1;
    private static final int NOT_VEGAN_FRIENDLY_INDICATOR = 0;

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<int[]> filteredRestaurants = createFilteredRestaurants(restaurants, veganFriendly, maxPrice, maxDistance);

        List<Integer> idFilteredRestaurants = new ArrayList<>();
        for (int[] restaurant : filteredRestaurants) {
            int id = restaurant[0];
            idFilteredRestaurants.add(id);
        }
        return idFilteredRestaurants;
    }

    private int comparator(int[] first, int[] second) {
        int idFirst = first[0];
        int idSecond = second[0];
        int ratingFirst = first[1];
        int ratingSecond = second[1];

        if (ratingFirst == ratingSecond) {
            return idSecond - idFirst;
        }
        return ratingSecond - ratingFirst;
    }

    private List<int[]> createFilteredRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<int[]> filteredRestaurants = new ArrayList<>();

        for (int[] restaurant : restaurants) {
            int id = restaurant[0];
            int rating = restaurant[1];
            int veganFriendlyIndicator = restaurant[2];

            boolean meetsVeganCrieria = ((veganFriendly == VEGAN_FRIENDLY_INDICATOR)
                                        && (veganFriendlyIndicator == VEGAN_FRIENDLY_INDICATOR))
                                        || (veganFriendly == NOT_VEGAN_FRIENDLY_INDICATOR);
            boolean isNotAboveMaxPrice = restaurant[3] <= maxPrice;
            boolean isNotAboveMaxDistance = restaurant[4] <= maxDistance;

            if (meetsVeganCrieria && isNotAboveMaxPrice && isNotAboveMaxDistance) {
                filteredRestaurants.add(new int[]{id, rating});
            }
        }
        Collections.sort(filteredRestaurants, (first, second) -> comparator(first, second));

        return filteredRestaurants;
    }
}
