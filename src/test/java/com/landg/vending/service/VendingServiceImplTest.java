package com.landg.vending.service;

import com.landg.vending.VendingMachine;
import com.landg.vending.service.impl.VendingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendingServiceImplTest {

    @Mock
    private ChangeService changeService;

    @Mock
    private FloatService floatService;

    @InjectMocks
    private VendingServiceImpl toTest;

    @Before
    public void setup() {
        toTest = new VendingServiceImpl();

        MockitoAnnotations.initMocks(this);

        List<Integer> coins = new ArrayList<>();
        coins.add(1);
        coins.add(2);
        coins.add(5);
        coins.add(10);
        coins.add(20);
        coins.add(50);
        coins.add(100);
        coins.add(200);

        ReflectionTestUtils.setField(toTest, "acceptedCoins", coins);
    }

    @Test
    public void initialiseTest() {

        toTest.initialise(getCoins());

        verify(floatService, times(1)).registerCoins(any(VendingMachine.class), anyList());
    }

    @Test
    public void initialiseWrongCoinsTest() {

        List<Integer> coins = getCoins();
        coins.add(110);

        toTest.initialise(coins);

        verify(floatService, times(0)).registerCoins(any(VendingMachine.class), anyList());
    }

    @Test
    public void registerTest() {

        ReflectionTestUtils.setField(toTest, "vendingMachine", new VendingMachine());

        toTest.register(getCoins());

        verify(floatService, times(1)).registerCoins(any(VendingMachine.class), anyList());
    }

    @Test
    public void registerWrongCoinsTest() {

        ReflectionTestUtils.setField(toTest, "vendingMachine", new VendingMachine());

        List<Integer> coins = getCoins();
        coins.add(220);

        toTest.register(coins);

        verify(floatService, times(0)).registerCoins(any(VendingMachine.class), anyList());
    }

    @Test
    public void changeNotEnoughChangeTest() {

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setTotalFloat(10);

        ReflectionTestUtils.setField(toTest, "vendingMachine", vendingMachine);

        Optional<List<Integer>> result = toTest.change(20);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void changeTest() {

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setTotalFloat(200);

        ReflectionTestUtils.setField(toTest, "vendingMachine", vendingMachine);

        List<Integer> changeList = new ArrayList<>();
        changeList.add(1);
        changeList.add(1);
        changeList.add(10);
        changeList.add(100);

        when(changeService.calculateChange(anyList(), any(Map.class), anyInt())).thenReturn(Optional.of(changeList));

        Optional<List<Integer>> result = toTest.change(112);

        verify(floatService, times(1)).removeCoins(any(VendingMachine.class), anyList());
        assertEquals(changeList, result.get());
    }

    @Test
    public void changeNoCorrectChangeTest() {

        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setTotalFloat(200);

        ReflectionTestUtils.setField(toTest, "vendingMachine", vendingMachine);

        when(changeService.calculateChange(anyList(), any(Map.class), anyInt())).thenReturn(Optional.empty());

        Optional<List<Integer>> result = toTest.change(112);

        verify(floatService, times(0)).removeCoins(any(VendingMachine.class), anyList());
        assertEquals(Optional.empty(), result);
    }

    private List<Integer> getCoins() {

        List<Integer> coins = new ArrayList<>();
        coins.add(10);
        coins.add(20);
        coins.add(10);
        coins.add(100);

        return coins;
    }

}