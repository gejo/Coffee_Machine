package com.gejo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Created by GEJO on 1/14/2015.
 */
public class CoffeeMachineTest {

    private HardwareInterface hardware;
    private CoffeeMachine coffeeMachine;

    @Before
    public void setUp() {
        createCoffeeMachine();
    }

    @After
    public void tearDown() {
        coffeeMachine.stop();
    }

    @Test
    public void should_not_brew_without_water() {
        assertEquals(CoffeeMachine.STATUS_STOPPED, coffeeMachine.getStatus());
    }

    @Test
    public void should_brew_coffee_with_water() throws Exception {

        setWaterLevel(20);
        pushBrewButton();

        assertEquals(CoffeeMachine.STATUS_BREWING, coffeeMachine.getStatus());
        Mockito.verify(hardware, Mockito.times(1)).turnOnBoiler();
    }

    private void pushBrewButton() throws Exception {
        Thread.currentThread().sleep(200);
    }

    private void createCoffeeMachine() {
        hardware = Mockito.mock(HardwareInterface.class);
        setWaterLevel(0);
        Mockito.when(hardware.getBrewButtonStatus()).thenReturn(CoffeeMachine.BREW_BUTTON_PUSHED);
        coffeeMachine = new CoffeeMachine(hardware);
        coffeeMachine.start();
    }

    private void setWaterLevel(int waterLevel) {
        Mockito.when(hardware.getWaterLevel()).thenReturn(waterLevel);
    }

}
