package com.landg.vending.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ChangeService {

    Optional<List<Integer>> calculateChange(List<Integer> coinList, Map<Integer, Integer> vendingFloat, int changeRequired);


}
