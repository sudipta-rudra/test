package com.sss.as.mqtt.devicelist;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public enum MqttQosLevel {
  @SerializedName("0")
  _0(0),
  @SerializedName("1")
  _1(1),
  @SerializedName("2")
  _2(2);
  private final Integer value;
  private static final Map<Integer, MqttQosLevel> CONSTANTS = new HashMap<>();

  static {
    for (MqttQosLevel c : values()) {
      CONSTANTS.put(c.value, c);
    }
  }

  private MqttQosLevel(Integer value) {
    this.value = value;
  }

  public Integer value() {
    return this.value;
  }

  public static MqttQosLevel fromValue(Integer value) {
    MqttQosLevel constant = CONSTANTS.get(value);
    if (constant == null) {
      throw new IllegalArgumentException((value + ""));
    } else {
      return constant;
    }
  }
}
