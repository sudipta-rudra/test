/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.response;

import com.google.gson.annotations.SerializedName;

import static com.sss.as.util.NiceASUtil.getDateTime;

public class GetDateTimeResponse {

    @SerializedName("DateTimeStamp")
    public String date = getDateTime();

    @SerializedName("SourceEndPointID")
    public String sourceEndPoint;

    @SerializedName("DestinationEndPointID")
    public String destEndPoint;

    @SerializedName("EncryptionOn")
    public boolean encryption= false;

    @SerializedName("Version")
    public String version="1.0";

    @SerializedName("MessageType")
    public String messageType="response";

    @SerializedName("ReplyErrorCode")
    public int replyErrorCode = 0;

    @SerializedName("ReplyID")
    public int replyID = 2;

    @SerializedName("ReplyStatusMessage")
    public String replyStatusMessage = "OK";

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

    public boolean isEncryption() {
        return encryption;
    }

    public void setEncryption(boolean encryption) {
        this.encryption = encryption;
    }

    public int getReplyErrorCode() {
        return replyErrorCode;
    }

    public void setReplyErrorCode(int replyErrorCode) {
        this.replyErrorCode = replyErrorCode;
    }

    public int getReplyID() {
        return replyID;
    }

    public void setReplyID(int replyID) {
        this.replyID = replyID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }


    public String getReplyStatusMessage() {
        return replyStatusMessage;
    }

    public void setReplyStatusMessage(String replyStatusMessage) {
        this.replyStatusMessage = replyStatusMessage;
    }

}