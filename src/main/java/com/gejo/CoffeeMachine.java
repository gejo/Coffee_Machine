package com.gejo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by GEJO on 1/14/2015.
 */
public class CoffeeMachine {
    public static final String STATUS_BREWING = "BREWING";
    public static final String POT_EMPTY = "POT_EMPTY";
    public static final String BREW_BUTTON_PUSHED = "PUSHED";
    public static final String BREW_BUTTON_NOT_PUSHED = "PUSHED";
    public static final String STATUS_STOPPED = "STOPPED";
    public static final String POT_NOT_PRESENT = "POT_NOT_PRESENT";
    public static final String POT_NOT_EMPTY = "PORT_NOT_EMPTY";
    private HardwareInterface hardwareInterface;
    private String status = STATUS_STOPPED;
    private ScheduledExecutorService scheduledExecutorService;

    public HardwareInterface getHardware() {
        return hardwareInterface;
    }

    public CoffeeMachine(HardwareInterface hardware) {
        this.hardwareInterface = hardware;
    }


    private Runnable getCheckBrewButtonTask() {
        return new Runnable() {
            @Override
            public void run() {
                if (hardwareInterface.getBrewButtonStatus().equals(BREW_BUTTON_PUSHED)) {
                    startBrew();
                }
            }
        };
    }

    public void start() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(getCheckBrewButtonTask(), 0, 10, TimeUnit.MILLISECONDS);
    }

    public void startBrew() {
        if (hardwareInterface.getWaterLevel() <= 1 || isBrewing()) {
            return;
        }
        hardwareInterface.turnOnBoiler();

        if (POT_EMPTY.equals(hardwareInterface.getPotStatus())) {
            hardwareInterface.closeVelvo();
        }else {
            hardwareInterface.openVelvo();
        }
        setStatus(STATUS_BREWING);
    }

    private boolean isBrewing() {
        return STATUS_BREWING.equals(getStatus());
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void stop() {
        scheduledExecutorService.shutdown();
    }

    public void warmCoffee() {
        if (hardwareInterface.getPotStatus().equals(POT_EMPTY)) {
            hardwareInterface.closeWarmer();
        }else {
            hardwareInterface.turnOnWarmer();
        }
    }
}
