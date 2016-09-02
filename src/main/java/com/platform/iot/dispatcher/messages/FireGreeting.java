package com.platform.iot.dispatcher.messages;

import com.platform.iot.dispatcher.controller.GreetingController;

/**
 * Created by ioan.vranau on 9/2/2016.
 */
public class FireGreeting implements Runnable {

    private GreetingController listener;

    public FireGreeting(GreetingController listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
                listener.fireGreeting();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}