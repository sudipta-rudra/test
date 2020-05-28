package com.sss.as.request.appcontroldevice;

import com.google.gson.annotations.SerializedName;
import com.sss.as.mqtt.devicelist.Version;

import java.util.LinkedHashSet;
import java.util.Set;


/**
 * DeviceControl
 * <p>
 * This object sets up the permissions and end points for the Control communication.
 */
public class DeviceControl {

    @SerializedName("Version")
    private Version version;

    @SerializedName("DeviceID")
    private String deviceID;

    @SerializedName("RevokedJSONTokenIDs")
    private Set<String> revokedJSONTokenIDs = new LinkedHashSet<>();

    @SerializedName("ControlEndPoints")
    private Set<ControlEndPointDevice> controlEndPointDevices = new LinkedHashSet<>();

    /**
     * Each entry in the array contains a Root Certificate that is valid for TLS communication.
     * Only entries in this field shall be accepted by the Device.
     */
    @SerializedName("AllowedTLSRootCertificates")
    private Set<String> allowedTLSRootCertificates = new LinkedHashSet<>();

    /**
     * Root Certificates in this list are to be deleted from the Device.
     */
    @SerializedName("DeletedTLSRootCertificates")
    private Set<String> deletedTLSRootCertificates = new LinkedHashSet<>();

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    /**
     * List of revoked jti values
     */
    public Set<String> getRevokedJSONTokenIDs() {
        return revokedJSONTokenIDs;
    }

    /**
     * List of revoked jti values
     */
    public void setRevokedJSONTokenIDs(Set<String> revokedJSONTokenIDs) {
        this.revokedJSONTokenIDs = revokedJSONTokenIDs;
    }

    public Set<ControlEndPointDevice> getControlEndPointDevices() {
        return controlEndPointDevices;
    }

    public void setControlEndPointDevices(Set<ControlEndPointDevice> controlEndPointDevices) {
        this.controlEndPointDevices = controlEndPointDevices;
    }

    /**
     * Each entry in the array contains a Root Certificate that is valid for TLS communication.
     * Only entries in this field shall be accepted by the Device.
     */
    public Set<String> getAllowedTLSRootCertificates() {
        return allowedTLSRootCertificates;
    }

    /**
     * Each entry in the array contains a Root Certificate that is valid for TLS communication.
     * Only entries in this field shall be accepted by the Device.
     */
    public void setAllowedTLSRootCertificates(Set<String> allowedTLSRootCertificates) {
        this.allowedTLSRootCertificates = allowedTLSRootCertificates;
    }

    /**
     * Root Certificates in this list are to be deleted from the Device.
     */
    public Set<String> getDeletedTLSRootCertificates() {
        return deletedTLSRootCertificates;
    }

    /**
     * Root Certificates in this list are to be deleted from the Device.
     */
    public void setDeletedTLSRootCertificates(Set<String> deletedTLSRootCertificates) {
        this.deletedTLSRootCertificates = deletedTLSRootCertificates;
    }

    public DeviceControl() {
        this.version = Version._1_0;
        this.deviceID = "fcfb988f-e051-25f8-8bad-f071720e1819";

        this.revokedJSONTokenIDs.add("K4QfG_JCMz4TKd5XwYdY4w8NR-EaSWhQtSJVTyC7");
        this.revokedJSONTokenIDs.add("BhBUzMA4Y_PeMB9EaMayV6VaUpbpUNX6JPwfaEL4");

        this.controlEndPointDevices.add(new ControlEndPointDevice());

        this.deletedTLSRootCertificates.add("CGCZeJk3NbNzhaqYW_yeJ64NFX5W65qd8LzRr3F7");
        this.deletedTLSRootCertificates.add("HCJxjLaGRWbSpJeY5dBGabM8V5eQz_Nc4YxAzcz6");

        this.allowedTLSRootCertificates.add("JwyTdVZwqH2TMEJ9YjDqfze93LbkjD4y53_gHKw7");
        this.allowedTLSRootCertificates.add("SXkFzWrSkVQRzyz_fcL5MYFaeQJLjD9Xp-ZcNyj2");
    }
}

