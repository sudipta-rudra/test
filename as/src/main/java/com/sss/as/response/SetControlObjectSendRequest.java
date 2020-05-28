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
import com.sss.as.basemqttmsgformat.BaseMqttMessage;
import com.sss.as.request.appcontroldevice.DeviceControl;

public class SetControlObjectSendRequest extends BaseMqttMessage {

    public Payload Payload = new Payload();

    public static class Payload {

        public transient DeviceControl deviceControl = new DeviceControl();
        public String Body = deviceControl.toString();

        @Override
        public String toString() {
            return "Payload{" +
                    "Body=" + Body.toString() +
                    '}';
        }


        public void stringify() {
            Body = new Gson().toJson(deviceControl);
        }

        public DeviceControl getDeviceControl() {
            return deviceControl;
        }

        public void setDeviceControl(DeviceControl deviceControl) {
            this.deviceControl = deviceControl;
        }
    }



    public Payload getPayload() {
        return Payload;
    }

    public void setPayload(Payload payload) {
        Payload = payload;
    }

    public SetControlObjectSendRequest() {
        super.setAccessToken("R2sRLdHmPMPzU0RDm6gRc7UCHMC8YmZ2i8o7kh8F");
    }
}
