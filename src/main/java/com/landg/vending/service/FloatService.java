package com.landg.vending.service;

import com.landg.vending.VendingMachine;

import java.util.List;

public interface FloatService {

    /**
     * remove coins from the vending float
     *
     * @param vendingMachine pass in the vending machine
     * @param coins list of coins that should be removed from float
     * @return the total float after the removal
     */
    int removeCoins(VendingMachine vendingMachine, List<Integer> coins);

    /**
     * register coins to the vending float
     *
     * @param vendingMachine pass in the vending machine
     * @param coins list of coins that should be added to the float
     * @return the total float after the addition
     */
    int registerCoins(VendingMachine vendingMachine, List<Integer> coins);


}
