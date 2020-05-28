/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as;

import com.sss.as.mqtt.MqttSubPubAS;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AsApplication {

    @Value("${mqtt.broker}")
    private String BROKER;

    @Value("${mqtt.client_id}")
    private String CLIENT_ID;

    @Value("${mqtt.version}")
    private String VERSION;

    @Value("${ds.id}")
    public String DS_ID;

    @Value("${as.id}")
    public String AS_ID;

    public static void main(String[] args) throws MqttException {
        SpringApplication.run(AsApplication.class, args);
    }

    @Bean
    public MqttSubPubAS initMqtt() throws MqttException {
        return new MqttSubPubAS(BROKER, CLIENT_ID, VERSION, AS_ID, DS_ID);
    }
}
