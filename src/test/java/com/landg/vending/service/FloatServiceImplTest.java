package com.landg.vending.service;

import com.landg.vending.VendingMachine;
import com.landg.vending.service.impl.FloatServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class FloatServiceImplTest {

    private FloatServiceImpl toTest;

    @Before
    public void setup() {
        toTest = new FloatServiceImpl();
    }

    @Test
    public void removeCoinsTest() {

        VendingMachine vendingMachine = new VendingMachine();
        TreeMap vendingFloat = new TreeMap(Collections.reverseOrder()) {{
            put(20, 4);
            put(10, 1);
            put(5, 2);
            put(2, 3);
        }};

        int totalFLoat = 106;

        vendingMachine.setVendingFloat(vendingFloat);
        vendingMachine.setTotalFloat(totalFLoat);

        List<Integer> coinsToRemove = Arrays.asList(20,10,5);

        int result = toTest.removeCoins(vendingMachine, coinsToRemove);

        assertEquals(71, result);

    }

    @Test
    public void removeCoinsEmptyListTest() {

        VendingMachine vendingMachine = new VendingMachine();
        TreeMap vendingFloat = new TreeMap(Collections.reverseOrder()) {{
            put(20, 4);
            put(10, 1);
            put(5, 2);
            put(2, 3);
        }};

        int totalFLoat = 106;

        vendingMachine.setVendingFloat(vendingFloat);
        vendingMachine.setTotalFloat(totalFLoat);

        List<Integer> coinsToRemove = new ArrayList<>();

        int result = toTest.removeCoins(vendingMachine, coinsToRemove);

        assertEquals(106, result);

    }

    @Test
    public void registerCoinsTest() {

        VendingMachine vendingMachine = new VendingMachine();
        TreeMap vendingFloat = new TreeMap(Collections.reverseOrder()) {{
            put(20, 4);
            put(10, 0);
            put(5, 2);
            put(2, 3);
        }};

        int totalFLoat = 96;

        vendingMachine.setVendingFloat(vendingFloat);
        vendingMachine.setTotalFloat(totalFLoat);

        List<Integer> coinsToRemove = Arrays.asList(20,10,5);

        int result = toTest.registerCoins(vendingMachine, coinsToRemove);

        assertEquals(131, result);

    }

    @Test
    public void registerCoinsEMptyListTest() {

        VendingMachine vendingMachine = new VendingMachine();
        TreeMap vendingFloat = new TreeMap(Collections.reverseOrder()) {{
            put(20, 4);
            put(10, 0);
            put(5, 2);
            put(2, 3);
        }};

        int totalFLoat = 96;

        vendingMachine.setVendingFloat(vendingFloat);
        vendingMachine.setTotalFloat(totalFLoat);

        List<Integer> coinsToRemove = new ArrayList<>();

        int result = toTest.registerCoins(vendingMachine, coinsToRemove);

        assertEquals(96, result);

    }
}