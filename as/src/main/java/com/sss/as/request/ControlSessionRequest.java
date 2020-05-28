package com.sss.as.request;

import com.sss.as.basemqttmsgformat.BaseMqttMessage;

public class ControlSessionRequest extends BaseMqttMessage {
    public Payload Payload = new Payload();

    public static class Body {
        public String AppID;
        public String AppDeveloperID;
        public String AppInstanceID;
        public String AccessToken;
        public String DeviceID;

        public String getAppID() {
            return AppID;
        }

        public String getAppDeveloperID() {
            return AppDeveloperID;
        }

        public String getAppInstanceID() {
            return AppInstanceID;
        }

        public String getAccessToken() {
            return AccessToken;
        }

        public String getDeviceID() {
            return DeviceID;
        }
    }

    public static class Payload {
        public Body Body = new Body();

        public ControlSessionRequest.Body getBody() {
            return Body;
        }

        public void setBody(ControlSessionRequest.Body body) {
            Body = body;
        }
    }

    public ControlSessionRequest.Payload getPayload() {
        return Payload;
    }
}
