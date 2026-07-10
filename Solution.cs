
using System;
using System.Collections.Generic;

public class Solution
{
    private static readonly int VEGAN_FRIENDLY_INDICATOR = 1;
    private static readonly int NOT_VEGAN_FRIENDLY_INDICATOR = 0;

    public IList<int> FilterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance)
    {
        List<int[]> filteredRestaurants = CreateFilteredRestaurants(restaurants, veganFriendly, maxPrice, maxDistance);

        List<int> idFilteredRestaurants = new List<int>();
        foreach (int[] restaurant in filteredRestaurants)
        {
            int id = restaurant[0];
            idFilteredRestaurants.Add(id);
        }
        return idFilteredRestaurants;
    }

    private int Comparator(int[] first, int[] second)
    {
        int idFirst = first[0];
        int idSecond = second[0];
        int ratingFirst = first[1];
        int ratingSecond = second[1];

        if (ratingFirst == ratingSecond)
        {
            return idSecond - idFirst;
        }
        return ratingSecond - ratingFirst;
    }

    private List<int[]> CreateFilteredRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance)
    {
        List<int[]> filteredRestaurants = new List<int[]>();

        foreach (int[] restaurant in restaurants)
        {
            int id = restaurant[0];
            int rating = restaurant[1];
            int veganFriendlyIndicator = restaurant[2];

            bool meetsVeganCrieria = ((veganFriendly == VEGAN_FRIENDLY_INDICATOR)
                                     && (veganFriendlyIndicator == VEGAN_FRIENDLY_INDICATOR))
                                     || (veganFriendly == NOT_VEGAN_FRIENDLY_INDICATOR);
            bool isNotAboveMaxPrice = restaurant[3] <= maxPrice;
            bool isNotAboveMaxDistance = restaurant[4] <= maxDistance;

            if (meetsVeganCrieria && isNotAboveMaxPrice && isNotAboveMaxDistance)
            {
                filteredRestaurants.Add([id, rating]);
            }
        }
        filteredRestaurants.Sort((first, second) => Comparator(first, second));

        return filteredRestaurants;
    }
}
