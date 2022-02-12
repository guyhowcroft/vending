package com.landg.vending.service.impl;

import com.landg.vending.VendingMachine;
import com.landg.vending.service.ChangeService;
import com.landg.vending.service.VendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class VendingServiceImpl implements VendingService {

    private static VendingMachine vendingMachine;

    @Value("${vending.accepted.coins}")
    private List<Integer> acceptedCoins;

    @Autowired
    private ChangeService changeService;

    public void intialise(List<Integer> coins) {

        log.info("Intialising Vending Machine");

        //validate coin input
        if (!validCoins(coins)) {
            log.info("Coin input error. Please insert valid coins");
            return;
        }

        vendingMachine = new VendingMachine();

        registerCoins(coins);

        printVendingMachineFloat();
    }

    public void register(List<Integer> coins) {

        if (vendingMachine == null) {
            log.info("Please intialise vending machine first");
            return;
        }

        log.info("Starting register");

        //validate coin input
        if (!validCoins(coins)) {
            log.info("Coin input error. Please insert valid coins");
            return;
        }

        registerCoins(coins);

        printVendingMachineFloat();

        return;
    }

    public Optional<List<Integer>> change(Integer value) {

        if (vendingMachine == null) {
            log.info("Please intialise vending machine first");
            return Optional.empty();
        }

        if (vendingMachine.getTotalFloat() < value) {
            log.info("Not enough change in machine to return");
            return Optional.empty();
        }

        List<Integer> coinList = new ArrayList<>();

        Optional<List<Integer>> changeList = changeService.calculateChange(coinList, vendingMachine.getVendingFloat(), value);

        if (changeList.isPresent()) {
            removeCoins(changeList.get());
        }

        printVendingMachineFloat();

        return changeList;
    }

    private void removeCoins(List<Integer> coins) {

        int totalFloat = vendingMachine.getTotalFloat();
        Map<Integer, Integer> vendingFloat = vendingMachine.getVendingFloat();

        for (Integer coin : coins) {

            totalFloat = totalFloat - coin;

            vendingFloat.merge(coin, -1, Integer::sum);
        }

        vendingMachine.setVendingFloat(vendingFloat);
        vendingMachine.setTotalFloat(totalFloat);

    }

    private void registerCoins(List<Integer> coins) {

        int totalFloat = vendingMachine.getTotalFloat();
        Map<Integer, Integer> vendingFloat = vendingMachine.getVendingFloat();

        for (Integer coin : coins) {

            totalFloat = totalFloat + coin;

            vendingFloat.merge(coin, 1, Integer::sum);
        }

        vendingMachine.setVendingFloat(vendingFloat);
        vendingMachine.setTotalFloat(totalFloat);

    }

    private boolean validCoins(List<Integer> coins) {

        for (Integer coin : coins) {

            if (!acceptedCoins.contains(coin)) {
                return false;
            }
        }
        return true;
    }

    private void printVendingMachineFloat() {

        log.info("Vending Machine Float: {}", vendingMachine.getTotalFloat());

        Iterator<Map.Entry<Integer, Integer>> it = vendingMachine.getVendingFloat().entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry<Integer, Integer> me = it.next();

            log.info("coin: {} Amount {}", me.getKey(), me.getValue());
        }
    }

}