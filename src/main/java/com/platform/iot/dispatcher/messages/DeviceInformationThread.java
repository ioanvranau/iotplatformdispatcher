package com.platform.iot.dispatcher.messages;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import com.platform.iot.dispatcher.controller.DeviceController;
import static com.platform.iot.dispatcher.mqtt.MqttConnection.*;

/**
 * Created by ioan.vranau on 9/2/2016.
 */
public class DeviceInformationThread implements Runnable {

    private DeviceController listener;

    private boolean running = true;

    private MqttMessage receivedMessage = null;
    private String receivedTopic = "";

    public DeviceInformationThread(DeviceController listener) {
        this.listener = listener;
    }

    public void initCallBack(MqttClient mqttClient) {
        final MqttCallback callback = new MqttCallback() {
            public void messageArrived(String topic, MqttMessage msg)
                    throws Exception {
                receivedMessage = msg;
                receivedTopic = topic;
                System.out.println("Received from topic:" + topic);
                System.out.println("Received:" + new String(msg.getPayload()));
            }

            public void deliveryComplete(IMqttDeliveryToken arg0) {
                System.out.println("Delivery complete");
            }

            public void connectionLost(Throwable arg0) {
                arg0.printStackTrace();
                // TODO Auto-generated method stub
            }
        };
        mqttClient.setCallback(callback);
    }

    @Override
    public void run() {
        MqttClient mqttClient = null;
        try {
            mqttClient = getMqttClientConnection(CLIENT_ID);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(USER_NAME);
            connOpts.setPassword(PASSWORD.toCharArray());
            if(!mqttClient.isConnected()) {
                mqttClient.connect(connOpts);
            }
            initCallBack(mqttClient);
            mqttClient.subscribe(TOPIC, QOS);
        } catch (MqttException e) {
            e.printStackTrace();
        }

        while (running) {
            if (running) {
                String message = "No message received!";
                if (receivedMessage != null) {
                    message = new String(receivedMessage.getPayload());
                    receivedMessage = null;
                    listener.sendMessageToTopic("/topic/accSensor", message);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            disconnectMqttClientConnection(mqttClient);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
    }
}