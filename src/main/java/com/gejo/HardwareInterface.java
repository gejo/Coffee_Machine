package com.gejo;

/**
 * Created by GEJO on 1/14/2015.
 */
public interface HardwareInterface {
    String getPotStatus();

    void turnOnBoiler();

    int getWaterLevel();

    String getBrewButtonStatus();
}
