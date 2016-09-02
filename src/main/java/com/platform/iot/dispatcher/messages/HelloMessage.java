package com.platform.iot.dispatcher.messages;

/**
 * Created by ioan.vranau on 9/2/2016.
 */

public class HelloMessage {

    private String name;

    public HelloMessage() {
    }

    public HelloMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}