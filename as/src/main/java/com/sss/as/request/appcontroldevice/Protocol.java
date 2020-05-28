package com.sss.as.request.appcontroldevice;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public enum Protocol {
    @SerializedName("MQTT")
    MQTT("MQTT"),
    @SerializedName("WebAPI")
    WEB_API("WebAPI"),
    @SerializedName("WebRTC")
    WEB_RTC("WebRTC");
    private final String value;
    private final static Map<String, Protocol> CONSTANTS = new HashMap<String, Protocol>();

    static {
        for (Protocol c: values()) {
            CONSTANTS.put(c.value, c);
        }
    }

    private Protocol(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public String value() {
        return this.value;
    }

    public static Protocol fromValue(String value) {
        Protocol constant = CONSTANTS.get(value);
        if (constant == null) {
            throw new IllegalArgumentException(value);
        } else {
            return constant;
        }
    }

}
