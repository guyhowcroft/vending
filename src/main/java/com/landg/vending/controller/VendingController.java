package com.landg.vending.controller;


import com.landg.vending.service.VendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class VendingController {

    @Autowired
    private VendingService vendingService;

    @PutMapping("initialise")
    public ResponseEntity intialise(@RequestBody List<Integer> coins) {

        vendingService.intialise(coins);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("register")
    public ResponseEntity transact(@RequestBody List<Integer> coins) {

        vendingService.register(coins);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("change/{value}")
    public ResponseEntity<List<Integer>> change(@PathVariable Integer value) {

        Optional<List<Integer>> changeList = vendingService.change(value);

        if (changeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(changeList.get(), HttpStatus.OK);
    }

}