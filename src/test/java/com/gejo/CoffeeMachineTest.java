package com.gejo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by GEJO on 1/14/2015.
 */
public class CoffeeMachineTest {

    @Test
    public void test_brew_coffee() {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        HardwareInterface hardwareInterface = new HardwareInterface() {
            @Override
            public String getPotStatus() {
                return CoffeeMachine.POT_STATUS_AVAILABLE;
            }

            @Override
            public int getWaterLevel() {
                return 20;
            }
        };
        coffeeMachine.setHardwareInterface(hardwareInterface);
        coffeeMachine.startBrew();
        assertEquals(CoffeeMachine.STATUS_BREWING, coffeeMachine.getStatus());
    }
}
