package com.landg.vending.service;

import com.landg.vending.service.impl.ChangeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ChangeServiceImplTest {

    private TreeMap<Integer, Integer> initialFloat;
    private int change;
    private Optional<List<Integer>> expected;

    private ChangeServiceImpl toTest = new ChangeServiceImpl();

    public ChangeServiceImplTest(TreeMap<Integer, Integer> initialFloat, int change, Optional<List<Integer>> expected) {
        this.initialFloat = initialFloat;
        this.change = change;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection testParameters() {
        return Arrays.asList(

            new Object[][]{
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
                        put(100, 1);
                        put(50, 1);
                        put(20, 1);
                        put(10, 1);
                        put(5, 1);
                    }},
                    150, Optional.of(Arrays.asList(100, 50))
                },
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
                        put(100, 1);
                        put(50, 1);
                        put(20, 1);
                        put(10, 1);
                        put(2, 22);
                    }},
                    44, Optional.of(Arrays.asList(20,10,2,2,2,2,2,2,2))
                },
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
                        put(100, 1);
                        put(50, 1);
                        put(20, 5);
                        put(5, 0);
                        put(2, 100);
                    }},
                    76, Optional.of(Arrays.asList(50,20,2,2,2))
                },
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
                        put(20, 4);
                        put(10, 1);
                        put(5, 2);
                        put(2, 3);
                    }},
                    81, Optional.of(Arrays.asList(20,20,20,10,5,2,2,2))
                },
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
                        put(20, 4);
                        put(10, 1);
                        put(5, 2);
                        put(2, 3);
                        put(1, 4);
                    }},
                    28, Optional.of(Arrays.asList(20,5,2,1))
                },
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
                        put(20, 4);
                        put(10, 1);
                        put(5, 2);
                        put(2, 3);
                    }},
                    83, Optional.empty()
                },
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
                        put(20, 4);
                        put(5, 3);
                        put(2, 3);
                    }},
                    88, Optional.empty()
                },
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
                        put(50, 2);
                        put(20, 4);
                        put(10, 1);
                        put(5, 2);
                        put(2, 1);
                    }},
                    81, Optional.empty()
                },
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()) {{
                        put(100, 1);
                        put(50, 1);
                        put(20, 5);
                        put(5, 0);
                        put(2, 100);
                    }},
                    101, Optional.empty()
                },
                {
                    new TreeMap<Integer, Integer>(Collections.reverseOrder()),
                    101, Optional.empty()
                }
            }
        );
    }

    @Test
    public void calculateChange_success() {

        List<Integer> coinList = new ArrayList<>();

        Optional<List<Integer>> result = toTest.calculateChange(coinList, initialFloat,  change);

        assertEquals(expected, result);

    }

}