package com.platform.iot.dispatcher.messages;

/**
 * Created by ioan.vranau on 9/2/2016.
 */
public class Greeting {

    private String content;

    public Greeting() {
    }

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}