package com.gejo;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.Assert.assertEquals;

/**
 * Created by GEJO on 1/14/2015.
 */
public class CoffeeMachineTest {

    private HardwareInterface hardware;
    private CoffeeMachine coffeeMachine;

    @Test
    public void should_not_brew_without_water() {
        coffeeMachine = createCoffeeMachine(0);

        assertEquals(CoffeeMachine.STATUS_STOPPED, coffeeMachine.getStatus());
    }

    @Test
    public void should_brew_coffee_with_water() throws Exception {
        coffeeMachine = createCoffeeMachine(20);

        pushBrewButton();

        assertEquals(CoffeeMachine.STATUS_BREWING, coffeeMachine.getStatus());
        Mockito.verify(coffeeMachine.getHardware(), Mockito.times(1)).turnOnBoiler();
    }

    private void pushBrewButton() throws InterruptedException {
        Thread.currentThread().sleep(200);
    }

    private CoffeeMachine createCoffeeMachine(int waterLevel) {
        hardware = Mockito.mock(HardwareInterface.class);
        Mockito.when(hardware.getWaterLevel()).thenReturn(waterLevel);
        Mockito.when(hardware.getBrewButtonStatus()).thenReturn(CoffeeMachine.BREW_BUTTON_PUSHED);
        CoffeeMachine coffeeMachine = new CoffeeMachine(hardware);
        coffeeMachine.start();
        return coffeeMachine;
    }

}
