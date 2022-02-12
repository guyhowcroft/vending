package com.landg.vending.service.impl;

import com.landg.vending.VendingMachine;
import com.landg.vending.service.ChangeService;
import com.landg.vending.service.FloatService;
import com.landg.vending.service.VendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class VendingServiceImpl implements VendingService {

    private VendingMachine vendingMachine;

    @Value("${vending.accepted.coins}")
    private List<Integer> acceptedCoins;

    @Autowired
    private ChangeService changeService;

    @Autowired
    private FloatService floatService;

    public VendingServiceImpl() {
        vendingMachine = new VendingMachine();
    }

    public void initialise(List<Integer> coins) {

        log.info("Intialising Vending Machine");

        //validate coin input
        if (!validCoins(coins)) {
            log.info("Coin input error. Please insert valid coins");
            return;
        }

        vendingMachine = new VendingMachine();

        floatService.registerCoins(vendingMachine, coins);

        log.info(vendingMachine.toString());
    }

    public void register(List<Integer> coins) {

        log.info("Registering Coins: {}", coins);

        //validate coin input
        if (!validCoins(coins)) {
            log.info("Coin input error. Please insert valid coins");
            return;
        }

        floatService.registerCoins(vendingMachine, coins);

        log.info(vendingMachine.toString());

    }

    public Optional<List<Integer>> change(Integer value) {

        if (vendingMachine.getTotalFloat() < value) {
            log.info("Not enough change in machine to return");
            return Optional.empty();
        }

        List<Integer> coinList = new ArrayList<>();

        log.info("Calculating coins for change {}", value);

        Optional<List<Integer>> changeList = changeService.calculateChange(coinList, vendingMachine.getVendingFloat(), value);

        if (changeList.isPresent()) {

            floatService.removeCoins(vendingMachine, changeList.get());

            log.info("Returning coins: {}", changeList.get());
        } else {

            log.info("Could not create change from float");
        }

        log.info(vendingMachine.toString());

        return changeList;
    }


    private boolean validCoins(List<Integer> coins) {

        for (Integer coin : coins) {

            if (!acceptedCoins.contains(coin)) {
                return false;
            }
        }
        return true;
    }


}