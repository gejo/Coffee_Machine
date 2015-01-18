package com.gejo;

/**
 * Created by GEJO on 1/14/2015.
 */
public interface HardwareInterface {
    String getPotStatus();

    void turnOnBoiler();

    int getWaterLevel();

    String getBrewButtonStatus();

    void closeVelvo();

    void openVelvo();

    void closeWarmer();

    void turnOnWarmer();
}
