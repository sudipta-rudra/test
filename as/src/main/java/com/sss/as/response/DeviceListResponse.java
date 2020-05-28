/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.response;

import com.sss.as.basemqttmsgformat.BaseMqttMessageResponse;
import com.sss.as.mqtt.devicelist.DeviceList;


public class DeviceListResponse extends BaseMqttMessageResponse {
    public Payload Payload = new Payload();

    public static class Payload{
        public DeviceList Body = new DeviceList();
    }
}
