package com.platform.iot.dispatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.platform.iot.dispatcher.messages.FireGreeting;
import com.platform.iot.dispatcher.messages.Greeting;
import com.platform.iot.dispatcher.messages.HelloMessage;

/**
 * Created by ioan.vranau on 9/2/2016.
 */
@Controller
public class GreetingController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    @CrossOrigin(origins = "*")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        // added this part
        FireGreeting r = new FireGreeting( this );
        new Thread(r).start();

        return new Greeting("Hello, " + message.getName() + "!");
    }

    public void fireGreeting() {
        System.out.println("Message from server! " + Math.random());
        this.template.convertAndSend("/topic/greetings", new Greeting("Fire"));
    }

}