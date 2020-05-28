package com.sss.as.mqtt.appcontrolds;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * AppControl
 * <p>
 * This object sets up the permissions and end points for the Control and Data Layers.
 * 
 */
public class AppControl {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Version")
    @Expose
    private Version version;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("AppID")
    @Expose
    private String appID;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("AppInstanceID")
    @Expose
    private String appInstanceID;
    /**
     * Control End Points are where the App can send control commands to Devices. Each Control End Point Object contains the parameters for the end points and an indication of which Device IDs are behind the End Point. 
     * (Required)
     * 
     */
    @SerializedName("ControlEndPoints")
    @Expose
    private Set<ControlEndPoint> controlEndPoints = new LinkedHashSet<ControlEndPoint>();
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("DataEndPoints")
    @Expose
    private Set<DataEndPoint> dataEndPoints = new LinkedHashSet<DataEndPoint>();

    /**
     * 
     * (Required)
     * 
     */
    public Version getVersion() {
        return version;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getAppID() {
        return appID;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setAppID(String appID) {
        this.appID = appID;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getAppInstanceID() {
        return appInstanceID;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setAppInstanceID(String appInstanceID) {
        this.appInstanceID = appInstanceID;
    }

    /**
     * Control End Points are where the App can send control commands to Devices. Each Control End Point Object contains the parameters for the end points and an indication of which Device IDs are behind the End Point. 
     * (Required)
     * 
     */
    public Set<ControlEndPoint> getControlEndPoints() {
        return controlEndPoints;
    }

    /**
     * Control End Points are where the App can send control commands to Devices. Each Control End Point Object contains the parameters for the end points and an indication of which Device IDs are behind the End Point. 
     * (Required)
     * 
     */
    public void setControlEndPoints(Set<ControlEndPoint> controlEndPoints) {
        this.controlEndPoints = controlEndPoints;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Set<DataEndPoint> getDataEndPoints() {
        return dataEndPoints;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setDataEndPoints(Set<DataEndPoint> dataEndPoints) {
        this.dataEndPoints = dataEndPoints;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AppControl.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("version");
        sb.append('=');
        sb.append(((this.version == null)?"<null>":this.version));
        sb.append(',');
        sb.append("appID");
        sb.append('=');
        sb.append(((this.appID == null)?"<null>":this.appID));
        sb.append(',');
        sb.append("appInstanceID");
        sb.append('=');
        sb.append(((this.appInstanceID == null)?"<null>":this.appInstanceID));
        sb.append(',');
        sb.append("controlEndPoints");
        sb.append('=');
        sb.append(((this.controlEndPoints == null)?"<null>":this.controlEndPoints));
        sb.append(',');
        sb.append("dataEndPoints");
        sb.append('=');
        sb.append(((this.dataEndPoints == null)?"<null>":this.dataEndPoints));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.appInstanceID == null)? 0 :this.appInstanceID.hashCode()));
        result = ((result* 31)+((this.controlEndPoints == null)? 0 :this.controlEndPoints.hashCode()));
        result = ((result* 31)+((this.dataEndPoints == null)? 0 :this.dataEndPoints.hashCode()));
        result = ((result* 31)+((this.version == null)? 0 :this.version.hashCode()));
        result = ((result* 31)+((this.appID == null)? 0 :this.appID.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AppControl) == false) {
            return false;
        }
        AppControl rhs = ((AppControl) other);
        return ((((((this.appInstanceID == rhs.appInstanceID)||((this.appInstanceID!= null)&&this.appInstanceID.equals(rhs.appInstanceID)))&&((this.controlEndPoints == rhs.controlEndPoints)||((this.controlEndPoints!= null)&&this.controlEndPoints.equals(rhs.controlEndPoints))))&&((this.dataEndPoints == rhs.dataEndPoints)||((this.dataEndPoints!= null)&&this.dataEndPoints.equals(rhs.dataEndPoints))))&&((this.version == rhs.version)||((this.version!= null)&&this.version.equals(rhs.version))))&&((this.appID == rhs.appID)||((this.appID!= null)&&this.appID.equals(rhs.appID))));
    }

    public enum Version {

        @SerializedName("1.0")
        _1_0("1.0");
        private final String value;
        private final static Map<String, Version> CONSTANTS = new HashMap<String, Version>();

        static {
            for (Version c: values()) {
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

}
