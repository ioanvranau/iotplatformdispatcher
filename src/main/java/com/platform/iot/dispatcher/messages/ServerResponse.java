package com.platform.iot.dispatcher.messages;

/**
 * Created by ioan.vranau on 9/2/2016.
 */
public class ServerResponse {

    private String content;

    public ServerResponse() {
    }

    public ServerResponse(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}