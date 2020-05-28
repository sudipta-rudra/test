package com.sss.as.mqtt.devicelist;

import com.google.gson.annotations.SerializedName;
import com.sss.as.util.Constants;

import java.util.HashMap;
import java.util.Map;


public class Device {
  @SerializedName("DeviceID")
  private String deviceID;

  @SerializedName("Status")
  private Status status;

  @SerializedName("Description")
  private String description;

  @SerializedName("BrokerURI")
  private String brokerUri;

  @SerializedName("MQTT QOS Level")
  private MqttQosLevel mqttqosLevel;

  public String getDeviceID() {
    return deviceID;
  }

  public void setDeviceID(String deviceID) {
    this.deviceID = deviceID;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBrokerUri() {
    return brokerUri;
  }

  public void setBrokerUri(String brokerUri) {
    this.brokerUri = brokerUri;
  }

  public MqttQosLevel getMqttQosLevel() {
    return mqttqosLevel;
  }

  public void setMqttQosLevel(MqttQosLevel mqttQosLevel) {
    this.mqttqosLevel = mqttQosLevel;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(Device.class.getName())
        .append('@')
        .append(Integer.toHexString(System.identityHashCode(this)))
        .append('[');
    sb.append("deviceID");
    sb.append('=');
    sb.append(((this.deviceID == null) ? "<null>" : this.deviceID));
    sb.append(',');
    sb.append("status");
    sb.append('=');
    sb.append(((this.status == null) ? "<null>" : this.status));
    sb.append(',');
    sb.append("description");
    sb.append('=');
    sb.append(((this.description == null) ? "<null>" : this.description));
    sb.append(',');
    sb.append("brokerURI");
    sb.append('=');
    sb.append(((this.brokerUri == null) ? "<null>" : this.brokerUri));
    sb.append(',');
    sb.append("mQTTQOSLevel");
    sb.append('=');
    sb.append(((this.mqttqosLevel == null) ? "<null>" : this.mqttqosLevel));
    sb.append(',');
    if (sb.charAt((sb.length() - 1)) == ',') {
      sb.setCharAt((sb.length() - 1), ']');
    } else {
      sb.append(']');
    }
    return sb.toString();
  }

  public enum Status {
    @SerializedName("Connected")
    CONNECTED("Connected"),
    @SerializedName("Unconnected")
    UNCONNECTED("Unconnected");
    private final String value;
    private static final Map<String, Status> CONSTANTS = new HashMap<>();

    static {
      for (Status c : values()) {
        CONSTANTS.put(c.value, c);
      }
    }

    private Status(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return this.value;
    }

    public String value() {
      return this.value;
    }

    public static Status fromValue(String value) {
      Status constant = CONSTANTS.get(value);
      if (constant == null) {
        throw new IllegalArgumentException(value);
      } else {
        return constant;
      }
    }
  }

  public Device(String deviceID ) {
    this.deviceID = deviceID;
    this.description = "description";
    this.brokerUri = Constants.getInstance().BROKER;
    this.status = Status.CONNECTED;
  }

  public Device(String deviceID, Status status, String description, String brokerUri, MqttQosLevel mqttqosLevel) {
    this.deviceID = deviceID;
    this.status = status;
    this.description = description;
    this.brokerUri = brokerUri;
    this.mqttqosLevel = mqttqosLevel;
  }
}
