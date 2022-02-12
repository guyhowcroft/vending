package com.landg.vending.service;

import com.landg.vending.VendingMachine;

import java.util.List;

public interface FloatService {

    int removeCoins(VendingMachine vendingMachine, List<Integer> coins);

    int registerCoins(VendingMachine vendingMachine, List<Integer> coins);


}
