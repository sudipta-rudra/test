/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.services;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

public class Paho {
    private MqttClient client;
    private MqttClient pubClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(Paho.class);

    private String ID_SOURCE_ENDPOINT;
    private String ID_DESTINATION_ENDPOINT;

    private String TOPIC_PUBLISH;
    private String TOPIC_SUBSCRIBE_MANAGEMENT;
    private String TOPIC_SUBSCRIBE_CONTROL_DEVICE;
    private String TOPIC_SUBSCRIBE_CONTROL;
    private String BROKER;
    private String VERSION;
    public String API_GET_SECURITY_OBJECT = "GetSecurityObject";

    public void setID_SOURCE_ENDPOINT(String ID_SOURCE_ENDPOINT) {
        this.ID_SOURCE_ENDPOINT = ID_SOURCE_ENDPOINT;
    }

    public void setID_DESTINATION_ENDPOINT(String ID_DESTINATION_ENDPOINT) {
        this.ID_DESTINATION_ENDPOINT = ID_DESTINATION_ENDPOINT;
    }

    public void setTOPIC_PUBLISH(String TOPIC_PUBLISH) {
        this.TOPIC_PUBLISH = TOPIC_PUBLISH;
    }

    public void setTOPIC_SUBSCRIBE_MANAGEMENT(String TOPIC_SUBSCRIBE_MANAGEMENT) {
        this.TOPIC_SUBSCRIBE_MANAGEMENT = TOPIC_SUBSCRIBE_MANAGEMENT;
    }

    public String API_GET_DEVICES = "GetDevices";

    public interface MessageListener<T> {
        void onMessageReceived(T obj) throws MqttException;
    }

    public String getVERSION() {
        return VERSION;
    }

    public void setVERSION(String VERSION) {
        this.VERSION = VERSION;
    }

    public String getTOPIC_SUBSCRIBE_CONTROL_DEVICE() {
        return TOPIC_SUBSCRIBE_CONTROL_DEVICE;
    }

    public void setTOPIC_SUBSCRIBE_CONTROL_DEVICE(String TOPIC_SUBSCRIBE_CONTROL_DEVICE) {
        this.TOPIC_SUBSCRIBE_CONTROL_DEVICE = TOPIC_SUBSCRIBE_CONTROL_DEVICE;
    }

    public String getTOPIC_SUBSCRIBE_CONTROL() {
        return TOPIC_SUBSCRIBE_CONTROL;
    }

    public void setTOPIC_SUBSCRIBE_CONTROL(String TOPIC_SUBSCRIBE_CONTROL) {
        this.TOPIC_SUBSCRIBE_CONTROL = TOPIC_SUBSCRIBE_CONTROL;
    }

    public Paho() {
    }

    /**
     * this constructor intiates connection with mqtt broker.
     *
     * @param broker   - ip
     * @param clientId - clid
     */
    public Paho(
            String broker,
            String clientId,
            String version,
            String sourceEndPoint,
            String destinationEndPoint) {
        BROKER = broker;
        VERSION = version;
        ID_SOURCE_ENDPOINT = sourceEndPoint;
        ID_DESTINATION_ENDPOINT = destinationEndPoint;
        TOPIC_PUBLISH = version + "/" + ID_DESTINATION_ENDPOINT + "/management";
        TOPIC_SUBSCRIBE_MANAGEMENT = version + "/" + ID_SOURCE_ENDPOINT + "/management";
        TOPIC_SUBSCRIBE_CONTROL_DEVICE = version + "/" + ID_SOURCE_ENDPOINT + "/control" + "/0001";
        TOPIC_SUBSCRIBE_CONTROL = version + "/" + ID_SOURCE_ENDPOINT + "/control";
        try {
            LOGGER.info("ACCOUNT SERVICE Client trying to connect MQTT Broker: " + broker);
            MemoryPersistence persistence = new MemoryPersistence();
            client = new MqttClient(broker, clientId + "subscriber", persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            client.connectWithResult(connOpts);
            LOGGER.info("ACCOUNT SERVICE Client connected successfully to MQTT Broker." + clientId + "subscriber");

            LOGGER.info("ACCOUNT SERVICE Client trying to connect MQTT Broker: " + broker);
            MemoryPersistence pubPersistence = new MemoryPersistence();
            pubClient = new MqttClient(broker, clientId + "publisher", pubPersistence);
            MqttConnectOptions pubConnOpts = new MqttConnectOptions();
            pubConnOpts.setCleanSession(true);
            pubClient.connectWithResult(pubConnOpts);
            LOGGER.info("ACCOUNT SERVICE Client connected successfully to MQTT Broker." + clientId + "publisher");

        } catch (MqttException e) {
            LOGGER.info("ACCOUNT SERVICE Client connection error: " + e.getMessage());
        }
    }

    public String getID_SOURCE_ENDPOINT() {
        return ID_SOURCE_ENDPOINT;
    }

    public String getID_DESTINATION_ENDPOINT() {
        return ID_DESTINATION_ENDPOINT;
    }

    public String getTOPIC_PUBLISH() {
        return TOPIC_PUBLISH;
    }

    public String getTOPIC_SUBSCRIBE_MANAGEMENT() {
        return TOPIC_SUBSCRIBE_MANAGEMENT;
    }

    public String getBROKER() {
        return BROKER;
    }

    public void setBROKER(String BROKER) {
        this.BROKER = BROKER;
    }

    public boolean isConnected() {
        return client.isConnected();
    }

    public <T> void publish(String topic, String message) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        pubClient.publish(topic, mqttMessage.getPayload(), 0, false);
    }

    public <T> void publish(String topic, MqttMessage mqttMessage) throws MqttException {
        pubClient.publish(topic, mqttMessage.getPayload(), 0, false);
    }

    public <T> void publish(String topic, T t) throws MqttException {
        String message = new Gson().toJson(t);
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        pubClient.publish(topic, mqttMessage.getPayload(), 0, false);
    }

    public void subscribe(String topic, IMqttMessageListener listener) throws MqttException {
        client.subscribe(topic, listener);
    }

    public <T> void subscribe(String topic, Type type, MessageListener<T> listener)
            throws MqttException {
        client.subscribe(
                topic,
                (s, mqttMessage) ->
                        listener.onMessageReceived(new Gson().fromJson(mqttMessage.toString(), type)));
    }

    public void unSubscribe(String topic) throws MqttException {
        client.unsubscribe(topic);
    }

    public void setCallback(MqttCallback callback) {
        client.setCallback(callback);
    }

    public void subscribe(String topic) throws MqttException {
        client.subscribe(topic, 0);
    }
}
