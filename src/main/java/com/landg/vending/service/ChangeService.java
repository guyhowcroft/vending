package com.landg.vending.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ChangeService {

    /**
     * Calculate change from a given change value, and the current vending float
     *
     * @param coinList pass in empty list as this will be used in a recursive function to add the selected coins
     * @param vendingFloat current vending float to find change
     * @param changeRequired change required to make from the float
     * @return a list of coins that make the change
     */
    Optional<List<Integer>> calculateChange(List<Integer> coinList, Map<Integer, Integer> vendingFloat, int changeRequired);


}
