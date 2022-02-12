package com.landg.vending.service;

import java.util.List;
import java.util.Optional;

public interface VendingService {

    /**
     * Initialise the vending machine with a given float
     *
     * @param coins to add to the vending machine
     */
    void initialise(List<Integer> coins);

    /**
     * Add more coins to the vending machine
     *
     * @param coins to add to the vending machine
     */
    void register(List<Integer> coins);

    /**
     * Get change in coins from a given value
     *
     * @param value to add to the vending machine
     * @return list of change if it can be made from the float
     */
    Optional<List<Integer>> change(Integer value);
}
