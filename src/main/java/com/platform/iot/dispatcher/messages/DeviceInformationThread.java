package com.platform.iot.dispatcher.messages;

import com.platform.iot.dispatcher.controller.DeviceController;

/**
 * Created by ioan.vranau on 9/2/2016.
 */
public class DeviceInformationThread implements Runnable {

    private DeviceController listener;

    private boolean running = true;

    public DeviceInformationThread(DeviceController listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(3000);
                listener.fireGreeting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
    }
}