package com.sss.as.request.appcontroldevice;

import com.google.gson.annotations.SerializedName;

public class AccessToken {

    /**
     * Actual Token to be used to access End Point
     * (Required)
     */
    @SerializedName("Token")
    private String token;
    /**
     * Date before which the token is invalid. Date in ISO8601 format.
     */
    @SerializedName("StartDateTime")
    private String startDateTime;
    /**
     * Date after which the Token is not valid.
     */
    @SerializedName("EndDateTime")
    private String endDateTime;

    public String getToken() {
        return token;
    }


    public void setToken(String token) {
        this.token = token;
    }


    public String getStartDateTime() {
        return startDateTime;
    }


    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }


    public String getEndDateTime() {
        return endDateTime;
    }


    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AccessToken.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("token");
        sb.append('=');
        sb.append(((this.token == null) ? "<null>" : this.token));
        sb.append(',');
        sb.append("startDateTime");
        sb.append('=');
        sb.append(((this.startDateTime == null) ? "<null>" : this.startDateTime));
        sb.append(',');
        sb.append("endDateTime");
        sb.append('=');
        sb.append(((this.endDateTime == null) ? "<null>" : this.endDateTime));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public AccessToken() {
        this.token = token;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }
}
