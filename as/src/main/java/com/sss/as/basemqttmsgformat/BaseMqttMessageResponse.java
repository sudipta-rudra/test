package com.sss.as.basemqttmsgformat;

import com.google.gson.annotations.SerializedName;
import com.sss.as.response.GetDateTimeResponse;

import java.util.Date;

import static com.sss.as.util.NiceASUtil.getDateTime;

public class BaseMqttMessageResponse {

    @SerializedName("DateTimeStamp")
    public String date;

    @SerializedName("SourceEndPointID")
    public String sourceEndPoint;

    @SerializedName("DestinationEndPointID")
    public String destEndPoint;

    @SerializedName("EncryptionOn")
    public boolean encryption;

    @SerializedName("ReplyID")
    public int replyID;

    @SerializedName("Version")
    public String version;

    @SerializedName("AccessToken")
    public String accessToken;

    @SerializedName("CommandType")
    public String commandType;

    @SerializedName("MessageType")
    public String messageType;

    public BaseMqttMessageResponse() {
        this.date = getDateTime();
        this.sourceEndPoint = "bddea2bf-This_IDis_N_AS-fc6a6ded63cd";
        this.destEndPoint = "TklDRSBBUFAgRU5DT0RFIEZST00gU0FSRA==";
        this.encryption = false;
        this.replyID = 1;
        this.version = "1.0";
        this.accessToken = "yYMwGYcQeBw6rxcPxVjy6HTZR97vwJcI1dtJXJSs";
        this.commandType = "/1.0/bed4d898-This_IDis_NAPP-fe1e7ac3dbdd/management/";
        this.messageType = "response";
    }

    public BaseMqttMessageResponse(Date date, String sourceEndPoint, String destEndPoint, boolean encryption, int replyID,
                                   String version, String accessToken, String commandType, String messageType) {
        this.date = date.toString();
        this.sourceEndPoint = sourceEndPoint;
        this.destEndPoint = destEndPoint;
        this.encryption = encryption;
        this.replyID = replyID;
        this.version = version;
        this.accessToken = accessToken;
        this.commandType = commandType;
        this.messageType = messageType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSourceEndPoint() {
        return sourceEndPoint;
    }

    public void setSourceEndPoint(String sourceEndPoint) {
        this.sourceEndPoint = sourceEndPoint;
    }

    public String getDestEndPoint() {
        return destEndPoint;
    }

    public void setDestEndPoint(String destEndPoint) {
        this.destEndPoint = destEndPoint;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public int getReplyID() {
        return replyID;
    }

    public void setReplyID(int replyID) {
        this.replyID = replyID;
    }

    @Override
    public String toString() {
        return "BaseMQTTMessage{" +
                "date='" + date + '\'' +
                ", sourceEndPoint='" + sourceEndPoint + '\'' +
                ", destEndPoint='" + destEndPoint + '\'' +
                ", encryption='" + encryption + '\'' +
                ", commandId='" + replyID + '\'' +
                ", version='" + version + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", commandType='" + commandType + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}
