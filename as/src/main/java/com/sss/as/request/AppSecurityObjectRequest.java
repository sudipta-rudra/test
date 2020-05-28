/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.request;

import com.sss.as.basemqttmsgformat.BaseMqttMessage;

public class AppSecurityObjectRequest extends BaseMqttMessage {
    public Payload Payload = new Payload();

    public AppSecurityObjectRequest.Payload getPayload() {
        return Payload;
    }

    public void setPayload(AppSecurityObjectRequest.Payload payload) {
        Payload = payload;
    }

    public static class Body {
        public String AppID;
        public String AppDeveloperID;

        @Override
        public String toString() {
            return "Body{" +
                    "AppID='" + AppID + '\'' +
                    ", AppDeveloperID='" + AppDeveloperID + '\'' +
                    '}';
        }
    }

    public static class Payload {
        public Body Body = new Body();

        @Override
        public String toString() {
            return "Payload{" +
                    "Body=" + Body.toString() +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AppSecurityObjectRequest{" +
                "Payload=" + Payload.toString() +
                ", date='" + date + '\'' +
                ", sourceEndPoint='" + sourceEndPoint + '\'' +
                ", destEndPoint='" + destEndPoint + '\'' +
                ", encryption='" + encryption + '\'' +
                ", commandId='" + commandId + '\'' +
                ", version='" + version + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", commandType='" + commandType + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
