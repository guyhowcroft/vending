package com.landg.vending;

import lombok.Data;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Data
public class VendingMachine {

    Map<Integer, Integer> vendingFloat = new TreeMap<>(Collections.reverseOrder());

    int totalFloat = 0;

}