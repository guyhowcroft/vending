package com.landg.vending.service;

import java.util.List;
import java.util.Optional;

public interface VendingService {

    void intialise(List<Integer> coins);

    void register(List<Integer> coins);

    Optional<List<Integer>> change(Integer value);
}
