package com.sss.as.mqtt.devicelist;

import com.google.gson.annotations.SerializedName;
import com.sss.as.util.Constants;

import java.util.LinkedHashSet;
import java.util.Set;

public class DeviceList {
    @SerializedName("Version")
    private Version version = Version._1_0;

    @SerializedName("AccountID")
    private String accountID;

    @SerializedName("DeviceList")
    private Set<Device> deviceList = new LinkedHashSet<>();

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Set<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(Set<Device> deviceList) {
        this.deviceList = deviceList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DeviceList.class.getName())
                .append('@')
                .append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null) ? "<null>" : this.version));
        sb.append(',');
        sb.append("accountID");
        sb.append('=');
        sb.append(((this.accountID == null) ? "<null>" : this.accountID));
        sb.append(',');
        sb.append("deviceList");
        sb.append('=');
        sb.append(((this.deviceList == null) ? "<null>" : this.deviceList));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public DeviceList(String accountID) {
        this.accountID = "00001";
    }

    public DeviceList() {
        this.accountID = "dummy-account-id";
        // add two device for single account

        deviceList.add(new Device(Constants.getInstance().DEVICE_ID_2));
        deviceList.add(new Device(Constants.getInstance().DEVICE_ID_1));
        deviceList.add(new Device(Constants.getInstance().DEVICE_ID_3));
    }
}
