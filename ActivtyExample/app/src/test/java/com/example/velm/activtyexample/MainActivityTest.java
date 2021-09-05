package com.example.velm.activtyexample;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by velmmuru on 9/19/2017.
 */
public class MainActivityTest {
    @Test
    public void getMultiple() throws Exception {

        int input = 3;

        int expected = 10*10*10;

        MainActivity mainActivity = new MainActivity();

        int output = mainActivity.getMultiple(input);

        assertEquals(expected,output);

    }

}