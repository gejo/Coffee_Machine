package com.gejo;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Created by GEJO on 1/14/2015.
 */
public class CoffeeMachineTest {

    @Test
    public void test_brew_coffee() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        HardwareInterface hardware = Mockito.mock(HardwareInterface.class);
        Mockito.when(hardware.getPotStatus()).thenReturn(CoffeeMachine.POT_STATUS_EMPTY);
        Mockito.when(hardware.getWaterLevel()).thenReturn(20);

        coffeeMachine.setHardwareInterface(hardware);
        coffeeMachine.startBrew();
        assertEquals(CoffeeMachine.STATUS_BREWING, coffeeMachine.getStatus());
        Mockito.verify(hardware, Mockito.times(1)).turnOnBoiler();
    }
}
