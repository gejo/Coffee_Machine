package com.gejo;

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

    @Test
    public void should_not_brew_without_water() {
        setWaterLevel(0);
        coffeeMachine.startBrew();

        assertEquals(CoffeeMachine.STATUS_STOPPED, coffeeMachine.getStatus());
    }

    @Test
    public void should_brew_coffee_with_water() throws Exception {
        setWaterLevel(20);
        coffeeMachine.startBrew();

        assertEquals(CoffeeMachine.STATUS_BREWING, coffeeMachine.getStatus());
        Mockito.verify(hardware, Mockito.times(1)).turnOnBoiler();
    }

    @Test
    public void should_deliver_water() {
        setWaterLevel(20);
        putCoffeePot();

        coffeeMachine.startBrew();
        Mockito.verify(hardware, Mockito.times(1)).closeVelvo();
    }

    @Test
    public void should_not_deliver_water() {
        setWaterLevel(20);
        removeCoffeePot();

        coffeeMachine.startBrew();
        Mockito.verify(hardware, Mockito.times(1)).openVelvo();
    }

    private void removeCoffeePot() {
        Mockito.when(hardware.getPotStatus()).thenReturn(CoffeeMachine.POT_NOT_PRESENT);
    }

    private void putCoffeePot() {
        Mockito.when(hardware.getPotStatus()).thenReturn(CoffeeMachine.POT_EMPTY);
    }

    private void createCoffeeMachine() {
        hardware = Mockito.mock(HardwareInterface.class);
        setWaterLevel(0);
        Mockito.when(hardware.getBrewButtonStatus()).thenReturn(CoffeeMachine.BREW_BUTTON_PUSHED);
        coffeeMachine = new CoffeeMachine(hardware);
    }

    private void setWaterLevel(int waterLevel) {
        Mockito.when(hardware.getWaterLevel()).thenReturn(waterLevel);
    }

}
