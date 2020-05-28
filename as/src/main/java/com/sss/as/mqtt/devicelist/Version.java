package com.sss.as.mqtt.devicelist;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public enum Version {
  @SerializedName("1.0")
  _1_0("1.0");
  private final String value;
  private static final Map<String, Version> CONSTANTS = new HashMap<>();

  static {
    for (Version c : values()) {
      CONSTANTS.put(c.value, c);
    }
  }

  private Version(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public String value() {
    return this.value;
  }

  public static Version fromValue(String value) {
    Version constant = CONSTANTS.get(value);
    if (constant == null) {
      throw new IllegalArgumentException(value);
    } else {
      return constant;
    }
  }



}
