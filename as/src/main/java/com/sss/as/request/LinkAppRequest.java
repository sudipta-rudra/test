/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.request;

import com.google.gson.annotations.SerializedName;

public class LinkAppRequest {
    @SerializedName("AppID")
    public String AppID;

    @SerializedName("AppDeveloperID")
    public String AppDeveloperID;

    @SerializedName("AppInstanceID")
    public String AppInstanceID;

    @Override
    public String toString() {
        return "LinkAppRequest{" +
                "AppID='" + AppID + '\'' +
                ", AppDeveloperID='" + AppDeveloperID + '\'' +
                ", AppInstanceID='" + AppInstanceID + '\'' +
                '}';
    }
}
