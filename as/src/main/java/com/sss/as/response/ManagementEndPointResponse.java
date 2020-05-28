/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.response;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.sss.as.basemqttmsgformat.BaseMqttMessageResponse;


public class ManagementEndPointResponse extends BaseMqttMessageResponse {

    @SerializedName("Payload")
    public Payload payload = new Payload();


    public static class Payload {
        public transient Body body = new Body();
        public String Body;
        public void stringify() {
            Body = new Gson().toJson(body);
        }
    }

    public static class Body {
        public String EndPointURI;
        public String Protocol;
        public String Version;
        public String MQTTQoS;

        public Body() {
            EndPointURI = "tcp://43.88.77.158:8883";
            Protocol = "MQTT";
            Version = "1.0";
            MQTTQoS = "0";
        }

        public static class Topic {
            public PubSub Subscribe;
            public PubSub Publish;

            public static class PubSub {
                public String Name;
                public String QoS;

                public PubSub(String name, String qoS) {
                    Name = name;
                    QoS = qoS;
                }
            }

            public Topic() {
                Subscribe = new PubSub("1.0/fcfb988f-e051-25f8-8bad-f071720e1819/management\\", "0");
                Publish = new PubSub("1.0/3b0fdfd1-This_IDis_N_LA-f47dd26aa54b/management\\", "0");
            }
        }
    }
}