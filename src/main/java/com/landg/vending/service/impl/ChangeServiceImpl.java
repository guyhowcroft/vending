package com.landg.vending.service.impl;

import com.landg.vending.service.ChangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
@Slf4j
public class ChangeServiceImpl implements ChangeService {


    public Optional<List<Integer>> calculateChange(List<Integer> coinList,  Map<Integer, Integer> vendingFloat, int changeRequired){

        Iterator<Map.Entry<Integer, Integer>> it = vendingFloat.entrySet().iterator();

        //for each coin
        while (it.hasNext()) {

            Map.Entry<Integer, Integer> me = it.next();

            //if there is a coin in the float
            if(me.getValue() > 0) {

                int coin = me.getKey();

                //if the coin can be used
                if (changeRequired / coin >= 1) {
                    changeRequired = changeRequired - coin;

                    //add to list of change
                    coinList.add(coin);

                    //if still more change required
                    if (changeRequired > 0 ) {

                        Map<Integer, Integer> modifiedFloat = removeCoinFromFloat(vendingFloat, coin);
                        calculateChange(coinList, modifiedFloat, changeRequired);

                    }

                    if (coinList.isEmpty()) {
                        return Optional.empty();
                    }

                    return Optional.of(coinList);

                }
            }
        }

        //got to end of float with possible coins and more change needed
        if (changeRequired > 0 && !coinList.isEmpty() ) {

            Integer lastCoin = coinList.get(coinList.size() - 1);

            //remove a coin from change list to check other possibilities
            coinList.remove(coinList.size() - 1);
            changeRequired = changeRequired + lastCoin;

            return calculateChange(coinList, vendingFloat, changeRequired);
        }

        return Optional.empty();
    }

    private Map<Integer, Integer> removeCoinFromFloat(Map<Integer, Integer> vendingFloat, int coin) {

        Map<Integer, Integer> modifiedMap = new TreeMap<>(Collections.reverseOrder());

        modifiedMap.putAll(vendingFloat);

        modifiedMap.merge(coin, -1, Integer::sum);

        return modifiedMap;

    }


}