package com.landg.vending;

import lombok.Data;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@Data
public class VendingMachine {

    Map<Integer, Integer> vendingFloat = new TreeMap<>(Collections.reverseOrder());

    int totalFloat = 0;

    public String toString() {

        String vendingMachine = "\n-Vending Machine-\nTotal Float: " + totalFloat + "\n";

        Iterator<Map.Entry<Integer, Integer>> it = vendingFloat.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry<Integer, Integer> me = it.next();

            vendingMachine = vendingMachine.concat("Coin: ")
            .concat(me.getKey().toString())
            .concat(" Amount: ")
            .concat(me.getValue().toString())
            .concat("\n");
        }

        return vendingMachine;
    }

}