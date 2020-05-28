package com.sss.as.mqtt.appcontrolds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AccessToken {

    /**
     * Actual Token to be used to access End Point
     * (Required)
     * 
     */
    @SerializedName("Token")
    @Expose
    private String token;
    /**
     * Date before which the token is invalid. Date in ISO8601 format.
     * 
     */
    @SerializedName("StartDateTime")
    @Expose
    private String startDateTime;
    /**
     * Date after which the Token is not valid.
     * 
     */
    @SerializedName("EndDateTime")
    @Expose
    private String endDateTime;

    /**
     * Actual Token to be used to access End Point
     * (Required)
     * 
     */
    public String getToken() {
        return token;
    }

    /**
     * Actual Token to be used to access End Point
     * (Required)
     * 
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Date before which the token is invalid. Date in ISO8601 format.
     * 
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     * Date before which the token is invalid. Date in ISO8601 format.
     * 
     */
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * Date after which the Token is not valid.
     * 
     */
    public String getEndDateTime() {
        return endDateTime;
    }

    /**
     * Date after which the Token is not valid.
     * 
     */
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AccessToken.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("token");
        sb.append('=');
        sb.append(((this.token == null)?"<null>":this.token));
        sb.append(',');
        sb.append("startDateTime");
        sb.append('=');
        sb.append(((this.startDateTime == null)?"<null>":this.startDateTime));
        sb.append(',');
        sb.append("endDateTime");
        sb.append('=');
        sb.append(((this.endDateTime == null)?"<null>":this.endDateTime));
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
        result = ((result* 31)+((this.startDateTime == null)? 0 :this.startDateTime.hashCode()));
        result = ((result* 31)+((this.endDateTime == null)? 0 :this.endDateTime.hashCode()));
        result = ((result* 31)+((this.token == null)? 0 :this.token.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AccessToken) == false) {
            return false;
        }
        AccessToken rhs = ((AccessToken) other);
        return ((((this.startDateTime == rhs.startDateTime)||((this.startDateTime!= null)&&this.startDateTime.equals(rhs.startDateTime)))&&((this.endDateTime == rhs.endDateTime)||((this.endDateTime!= null)&&this.endDateTime.equals(rhs.endDateTime))))&&((this.token == rhs.token)||((this.token!= null)&&this.token.equals(rhs.token))));
    }

}
