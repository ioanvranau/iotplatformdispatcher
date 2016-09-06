package com.platform.iot.dispatcher.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Created by ioan.vranau on 9/3/2016.
 */
public class MqttConnection {

    public static final String USER_NAME = "uigwlndd";
    public static final String PASSWORD = "Z5aN2Y-FjLPv";
    public static final String SERVER = "m21.cloudmqtt.com";
    public static final int PORT = 14761;
    public static final int QOS = 1;
    public static final String PROTOCOL = "tcp";
    public static final String BROKER = PROTOCOL + "://" + SERVER + ":" + PORT;
    public static final String TOPIC = "iotandroid";
    public static final String CLIENT_ID = "ClientId_1";
    private static MqttClient mqttClient;

    public static void main(String[] args) {


        //MQTT client id to use for the device. "" will generate a client id automatically

        try {

            Thread.sleep(10000);


            System.exit(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printException(MqttException me) {
        System.out.println("reason " + me.getReasonCode());
        System.out.println("msg " + me.getMessage());
        System.out.println("loc " + me.getLocalizedMessage());
        System.out.println("cause " + me.getCause());
        System.out.println("excep " + me);
        me.printStackTrace();
    }

    public static void disconnectMqttClientConnection(MqttClient mqttClient) throws MqttException {
        try {
            sendMessageToTopic("disconnected", mqttClient);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        mqttClient.disconnect();


    }

    public static void sendMessageToTopic(String content, MqttClient mqttClient) throws MqttException {
//        mqttClient.subscribe(TOPIC, QOS);
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(QOS);
        System.out.println("Publish message: " + message);
        mqttClient.publish(TOPIC, message);
    }


    public static MqttClient getMqttClientConnection(String clientId) throws MqttException {
        if (mqttClient == null) {
            MemoryPersistence persistence = new MemoryPersistence();
            mqttClient = new MqttClient(BROKER, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setUserName(USER_NAME);
            connOpts.setPassword(PASSWORD.toCharArray());
            mqttClient.connect(connOpts);
        }
        return mqttClient;
    }

}
