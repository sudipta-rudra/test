/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.mqtt;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sss.as.basemqttmsgformat.BaseMqttMessage;
import com.sss.as.mqtt.appcontrolds.*;
import com.sss.as.request.AppSecurityObjectRequest;
import com.sss.as.request.ControlSessionRequest;
import com.sss.as.request.DeviceListRequest;
import com.sss.as.request.GetDateTimeRequest;
import com.sss.as.request.appcontroldevice.ControlEndPointDevice;
import com.sss.as.response.*;
import com.sss.as.services.Paho;
import com.sss.as.util.Constants;
import com.sss.as.util.NiceASUtil;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

public class MqttSubPubAS implements MqttCallback {
    private static final Logger LOGGER = LoggerFactory.getLogger(MqttSubPubAS.class);

    private static Constants constants = Constants.getInstance();

    private Paho paho;

    private static final String COMMAND_MANAGEMENT = "management";
    private static final String COMMAND_CONTROL = "control";
    private String TOPIC_PUBLISH_MANAGEMENT;
    private String TOPIC_PUBLISH_CONTROL;
    private static final String API_GET_SECURITY_OBJECT = "GetSecurityObject";
    public static final String API_GET_DATE_TIME = "GetDateTime";
    public static final String API_GET_DEVICES = "GetDevices";
    public static final String API_GET_CONTROL_OBJECT = "GetControlObject";
    public static final String API_SET_CONTROL_OBJECT = "SetControlObject";
    public String NICE_ACCOUNT_SERVICE_ID;
    public String NICE_ACCOUNT_SERVICE_URI;

    @Value("${ds.id}")
    String DS_ID;

    public MqttSubPubAS(String broker,
                        String clientId,
                        String version,
                        String sourceEndPoint,
                        String destinationEndPoint) throws MqttException {

        this.paho = new Paho(broker, clientId, version, sourceEndPoint, destinationEndPoint);

        TOPIC_PUBLISH_MANAGEMENT = paho.getTOPIC_PUBLISH();
        NICE_ACCOUNT_SERVICE_ID = paho.getID_SOURCE_ENDPOINT();
        NICE_ACCOUNT_SERVICE_URI = paho.getBROKER();
        TOPIC_PUBLISH_CONTROL = constants.VERSION + "/" + paho.getID_DESTINATION_ENDPOINT() + "/" + COMMAND_CONTROL;
        paho.setCallback(this);
        paho.subscribe(paho.getTOPIC_SUBSCRIBE_MANAGEMENT());
        paho.subscribe(paho.getTOPIC_SUBSCRIBE_CONTROL_DEVICE());
        paho.subscribe(paho.getTOPIC_SUBSCRIBE_CONTROL());
    }

    @Override
    public void connectionLost(Throwable throwable) {
        LOGGER.info("MQTT Connection lost");
    }

    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws MqttException, InterruptedException {
        LOGGER.info("Message Received : " + topic);
        String[] commands = topic.split("/");
        if (commands.length >= 3) {
            switch (commands[2]) {
                case COMMAND_MANAGEMENT:
                    managementMessage(topic, mqttMessage);
                    break;
                case COMMAND_CONTROL:
                    controlMessage(topic, mqttMessage);
                    break;
            }
        } else {
            LOGGER.warn("Invalid Topic : " + topic);
        }
    }

    private void controlMessage(String topic, MqttMessage mqttMessage) throws InterruptedException, MqttException {
        try {
            BaseMqttMessage mqttMsg = new Gson().fromJson(mqttMessage.toString(), BaseMqttMessage.class);
            String[] commands = new String[]{};
            if (mqttMsg.getCommandType() != null) {
                commands = mqttMsg.commandType.split("/");
            }

            if (mqttMessage.toString().contains("18950")) {
                BaseMqttMessage request = new Gson().fromJson(mqttMessage.toString(), BaseMqttMessage.class);
                LOGGER.warn("Received for : " + topic);
                LOGGER.warn("Set Control Object Ack :\n" + request.toString());

                return;
            }

            if (commands.length >= 4) {
                String API = commands[commands.length - 1];
                if (API.equals(API_GET_DATE_TIME)) {
                    GetDateTimeRequest request = new Gson().fromJson(mqttMessage.toString(), GetDateTimeRequest.class);
                    GetDateTimeResponse response = getDateTimeResponse(topic, request);
                    response.setDestEndPoint(request.sourceEndPoint);
                    response.setSourceEndPoint(request.destEndPoint);
                    MqttMessage message = new MqttMessage();
                    String json = new Gson().toJson(response);
                    message.setPayload(json.getBytes());
                    // TODO: Need to be removed
                    Thread.sleep(100);
                    String pubTopic = constants.VERSION + "/" + response.destEndPoint + "/" + COMMAND_CONTROL + "/" + "0001";
                    paho.publish(pubTopic, message);
                    LOGGER.warn("Published : " + pubTopic);
                    sendControlObject(pubTopic, request);
                } else if (API.equals(API_GET_CONTROL_OBJECT)) {
                    ControlSessionRequest controlSessionRequest = new Gson().fromJson(mqttMessage.toString(),
                            ControlSessionRequest.class);
                    String requestDeviceID = controlSessionRequest.getPayload().getBody().getDeviceID();

                    ControlSessionResponse controlSessionResponse = new ControlSessionResponse();
                    controlSessionResponse.setDestEndPoint(controlSessionRequest.getSourceEndPoint());
                    controlSessionResponse.setSourceEndPoint(controlSessionRequest.getDestEndPoint());
                    String commandType = constants.VERSION + "/" + constants.ID_DESTINATION_ENDPOINT + "/" +
                            COMMAND_CONTROL + "/" + API_GET_CONTROL_OBJECT;
                    controlSessionResponse.setCommandType(commandType);
                    controlSessionResponse.setReplyID(controlSessionRequest.getCommandId());
                    controlSessionResponse.setAccessToken(controlSessionRequest.getAccessToken());

                    AppControl appControl = new AppControl();
                    appControl.setAppID(controlSessionRequest.getPayload().getBody().getAppID());
                    appControl.setAppInstanceID(controlSessionRequest.getPayload().getBody().getAppInstanceID());
                    appControl.setVersion(AppControl.Version._1_0);

                    String token = controlSessionRequest.getPayload().getBody().getAccessToken();
                    String[] devices = constants.getDevicesWithAccesToken(token);
                    AccessToken accessToken = new AccessToken();
                    accessToken.setToken(controlSessionRequest.getAccessToken());
                    accessToken.setStartDateTime(NiceASUtil.getDateTime());
                    accessToken.setEndDateTime(NiceASUtil.getDateTime());

                    for (int i = 0; i < devices.length ; i++) {
                        ControlEndPoint controlEndPoint = new ControlEndPoint();
                        controlEndPoint.setAccessToken(accessToken);
                        controlEndPoint.setEncryptionEnforced(false);
                        controlEndPoint.getProtocol().add(Protocol.MQTT);
                        controlEndPoint.setMQTTQoS(ControlEndPoint.MQTTQoS._1);
                        controlEndPoint.setEndPointCertificate("dummy");
                        controlEndPoint.setEndPointURI(NICE_ACCOUNT_SERVICE_URI);
                        controlEndPoint.setEndPointID(devices[i]);

                        appControl.getControlEndPoints().add(controlEndPoint);

                        DataEndPoint dataEndPoint = new DataEndPoint();
                        dataEndPoint.setAccessToken(accessToken);
                        dataEndPoint.setEncryptionEnforced(false);
                        dataEndPoint.getProtocol().add(Protocol.MQTT);
                        dataEndPoint.setMQTTQoS(DataEndPoint.MQTTQoS._1);
                        dataEndPoint.setEndPointCertificate("dummy");
                        dataEndPoint.setEndPointURI(NICE_ACCOUNT_SERVICE_URI);
                        dataEndPoint.setEndPointID(devices[i]);
                        dataEndPoint.getDataType().add(DataType.SCENE_MARK);
                        dataEndPoint.getDataType().add(DataType.SCENE_DATA);

                        appControl.getDataEndPoints().add(dataEndPoint);
                    }

                    controlSessionResponse.getPayload().setBody(appControl);

                    MqttMessage message = new MqttMessage();
                    String json = new Gson().toJson(controlSessionResponse);
                    message.setPayload(json.getBytes());
                    paho.publish(TOPIC_PUBLISH_CONTROL, message);
                    LOGGER.warn("Published to the topic: " + TOPIC_PUBLISH_CONTROL);
                }
            } else {
                LOGGER.warn("Invalid CommandType : " + mqttMsg.commandType);
            }
        } catch (JsonSyntaxException e) {
            LOGGER.warn("Invalid Message Format : " + mqttMessage.toString());
        } catch (MqttException | InterruptedException e) {
            LOGGER.error("Something went wrong : " + e.getMessage());
        }
    }

    private void managementMessage(String topic, MqttMessage mqttMessage) {
        try {
            BaseMqttMessage mqttMsg = new Gson().fromJson(mqttMessage.toString(), BaseMqttMessage.class);
            String[] commands = new String[]{};
            if (mqttMsg.getCommandType() != null) {
                commands = mqttMsg.commandType.split("/");
            }

            if (commands.length >= 4) {
                String API = commands[commands.length - 1];
                if (API.equals(API_GET_SECURITY_OBJECT)) {
                    AppSecurityObjectRequest request = new Gson().fromJson(mqttMessage.toString(), AppSecurityObjectRequest.class);
                    AppSecurityObjectResponse response = getAppSecurityObject(API, request);
                    response.setDestEndPoint(request.sourceEndPoint);
                    response.setSourceEndPoint(request.destEndPoint);
                    MqttMessage message = new MqttMessage();
                    String json = new Gson().toJson(response);
                    message.setPayload(json.getBytes());
                    Thread.sleep(100);
                    paho.publish(TOPIC_PUBLISH_MANAGEMENT, message);
                    LOGGER.warn("Published to the topic: " + paho.getTOPIC_PUBLISH());
                } else if (API.equals(API_GET_DEVICES)) {
                    DeviceListRequest request = new Gson().fromJson(mqttMessage.toString(), DeviceListRequest.class);
                    DeviceListResponse response = new DeviceListResponse();
                    MqttMessage message = new MqttMessage();
                    String json = new Gson().toJson(response);
                    message.setPayload(json.getBytes());
                    Thread.sleep(100);
                    paho.publish(TOPIC_PUBLISH_MANAGEMENT, message);
                    LOGGER.warn("Published : " + paho.getTOPIC_PUBLISH());
                }
            } else {
                LOGGER.warn("Invalid CommandType : " + mqttMsg.commandType);
            }
        } catch (JsonSyntaxException e) {
            LOGGER.warn("Invalid Message Format : " + mqttMessage.toString());
        } catch (MqttException | InterruptedException e) {
            LOGGER.error("Something went wrong : " + e.getMessage());
        }
    }

    private AppSecurityObjectResponse getAppSecurityObject(String API, AppSecurityObjectRequest request) {
        // TODO: 10-02-2020 Validate the AppID & AppDeveloperID
        AppSecurityObjectResponse response = new AppSecurityObjectResponse();
        response.sourceEndPoint = request.destEndPoint;
        response.destEndPoint = request.sourceEndPoint;
        response.commandType = "1.0/" + response.sourceEndPoint + "/management/" + API;
        response.Payload.Body.AppID = request.Payload.Body.AppID;
        response.Payload.Body.AppDeveloperID = request.Payload.Body.AppDeveloperID;
        return response;
    }

    private GetDateTimeResponse getDateTimeResponse(String API, GetDateTimeRequest request) {
        GetDateTimeResponse response = new GetDateTimeResponse();
        response.sourceEndPoint = request.destEndPoint;
        response.destEndPoint = request.sourceEndPoint;
        return response;
    }

    public void sendControlObject(String topic, GetDateTimeRequest getDateTimeRequest) {
        SetControlObjectSendRequest request = new SetControlObjectSendRequest();
        request.setDestEndPoint(getDateTimeRequest.getSourceEndPoint());
        request.setSourceEndPoint(getDateTimeRequest.getDestEndPoint());
        request.messageType = "request";
        request.setCommandType("/" + constants.VERSION + "/" + getDateTimeRequest.sourceEndPoint + "/" + COMMAND_CONTROL + "/0000/"
                + API_SET_CONTROL_OBJECT);
        request.setCommandId(18950);
        request.setAccessToken("gR3hdeJYnfSZfZqHhpue9aRRa4fFYCr7YTsL5AaP");
        Set<ControlEndPointDevice> controlEndPointDevices = request.getPayload().getDeviceControl().getControlEndPointDevices();
        String pubTopicToDS = constants.VERSION + "/" + DS_ID + "/" + COMMAND_CONTROL + "/" + constants.NODE_ID;
        String subTopicForDevice = constants.VERSION + "/" + getDateTimeRequest.sourceEndPoint + "/" + COMMAND_CONTROL;
        controlEndPointDevices.forEach((e) -> {
            e.setEndPointURI(NICE_ACCOUNT_SERVICE_URI);
            e.setEndPointID(NICE_ACCOUNT_SERVICE_ID);
            e.getTopic().getPublish().setName(pubTopicToDS);
            e.getTopic().getSubscribe().setName(subTopicForDevice);
        });
        request.getPayload().stringify();
        MqttMessage message = new MqttMessage();
        String json = new Gson().toJson(request);
        message.setPayload(json.getBytes());

        try {
            paho.publish(topic, message);
        } catch (MqttException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}
