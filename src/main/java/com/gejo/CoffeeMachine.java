package com.gejo;

/**
 * Created by GEJO on 1/14/2015.
 */
public class CoffeeMachine {
    public static final String STATUS_BREWING = "BREWING";
    public static final String POT_STATUS_AVAILABLE = "POT_AVAILABLE";
    private HardwareInterface hardwareInterface;
    private String status;

    public void setHardwareInterface(HardwareInterface hardwareInterface) {
        this.hardwareInterface = hardwareInterface;
    }

    public HardwareInterface getHardwareInterface() {
        return hardwareInterface;
    }

    public void startBrew() {
        setStatus(STATUS_BREWING);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
