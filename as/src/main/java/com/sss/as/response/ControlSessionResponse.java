package com.sss.as.response;

import com.sss.as.basemqttmsgformat.BaseMqttMessageResponse;
import com.sss.as.mqtt.appcontrolds.AppControl;
import com.sss.as.request.appcontroldevice.DeviceControl;

public class ControlSessionResponse extends BaseMqttMessageResponse {

    public Payload Payload = new Payload();

    public static class Payload {

        public AppControl Body = new AppControl();

        public AppControl getBody() {
            return Body;
        }

        public void setBody(AppControl body) {
            Body = body;
        }
    }


    public Payload getPayload() {
        return Payload;
    }

    public void setPayload(Payload payload) {
        Payload = payload;
    }
}