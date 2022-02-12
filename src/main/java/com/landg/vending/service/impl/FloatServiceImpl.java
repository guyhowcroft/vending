package com.landg.vending.service.impl;

import com.landg.vending.VendingMachine;
import com.landg.vending.service.FloatService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FloatServiceImpl implements FloatService {

    public int removeCoins(VendingMachine vendingMachine, List<Integer> coins) {

        int totalFloat = vendingMachine.getTotalFloat();
        Map<Integer, Integer> vendingFloat = vendingMachine.getVendingFloat();

        for (Integer coin : coins) {

            totalFloat = totalFloat - coin;

            vendingFloat.merge(coin, -1, Integer::sum);
        }

        vendingMachine.setVendingFloat(vendingFloat);
        vendingMachine.setTotalFloat(totalFloat);

        return totalFloat;
    }

    public int registerCoins(VendingMachine vendingMachine, List<Integer> coins) {

        int totalFloat = vendingMachine.getTotalFloat();
        Map<Integer, Integer> vendingFloat = vendingMachine.getVendingFloat();

        for (Integer coin : coins) {

            totalFloat = totalFloat + coin;

            vendingFloat.merge(coin, 1, Integer::sum);
        }

        vendingMachine.setVendingFloat(vendingFloat);
        vendingMachine.setTotalFloat(totalFloat);

        return totalFloat;

    }

}