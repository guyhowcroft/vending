package com.landg.vending.controller;


import com.landg.vending.service.VendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class VendingController {

    @Autowired
    private VendingService vendingService;

    @PutMapping("initialise")
    public ResponseEntity initialise(@RequestBody List<Integer> coins) {

        vendingService.initialise(coins);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("register")
    public ResponseEntity register(@RequestBody List<Integer> coins) {

        vendingService.register(coins);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("change/{value}")
    public ResponseEntity<List<Integer>> change(@PathVariable Integer value) {

        Optional<List<Integer>> changeList = vendingService.change(value);

        if (changeList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        return new ResponseEntity<>(changeList.get(), HttpStatus.OK);
    }

}