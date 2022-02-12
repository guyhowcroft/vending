package com.landg.vending.controller;

import com.landg.vending.controller.VendingController;
import com.landg.vending.service.VendingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendingControllerTest {

    @Mock
    private VendingService vendingService;

    @InjectMocks
    private VendingController toTest;

    @Before
    public void setup() {
        toTest = new VendingController();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void initialiseTest() {

        ResponseEntity result = toTest.initialise(List.of(1,2,2,10));

        verify(vendingService, times(1)).initialise(any(List.class));
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void transactTest() {

        ResponseEntity result = toTest.transact(List.of(1,2,2,10));

        verify(vendingService, times(1)).register(any(List.class));
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void changeNoChangeTest() {

        when(vendingService.change(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<List<Integer>> result = toTest.change(10);

        verify(vendingService, times(1)).change(anyInt());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void changeTest() {

        List<Integer> change = new ArrayList<>();
        change.add(10);
        change.add(50);

        when(vendingService.change(anyInt())).thenReturn(Optional.of(change));

        ResponseEntity result = toTest.change(60);

        verify(vendingService, times(1)).change(anyInt());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(change, result.getBody());
    }

}