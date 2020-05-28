/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.util;

public class Constants {

    public String BROKER;
    public String CLIENT_ID;

    public String AS_BASE_URL;
    public String VERSION;

    public String ID_SOURCE_ENDPOINT;
    public String ID_DESTINATION_ENDPOINT;

    public String TOPIC_SUBSCRIBE;
    public String TOPIC_PUBLISH;

    public String API_GET_SECURITY_OBJECT;

    public String ACCESSTOKEN_1;
    public String ACCESSTOKEN_2;

    public String DEVICE_ID_1;
    public String NODE_ID;

    public String DEVICE_ID_2;
    public String DEVICE_ID_3;

    private static Constants instance;


    public Constants() {
        BROKER = "tcp://43.88.77.158:8883";
        CLIENT_ID = "AS-SERVER";

        AS_BASE_URL = "http://localhost:8080/";

        VERSION = "1.0";
        ID_SOURCE_ENDPOINT = "bddea2bf-This_IDis_N_AS-fc6a6ded63cd";
        ID_DESTINATION_ENDPOINT = "ID_DESTINATION_ENDPOINT";

        TOPIC_SUBSCRIBE = "1.0/" + ID_DESTINATION_ENDPOINT + "/management";
        TOPIC_PUBLISH = "1.0/" + ID_SOURCE_ENDPOINT + "/management";

        API_GET_SECURITY_OBJECT = "GetSecurityObject";
        ACCESSTOKEN_1 = "dummy-access-token1";
        ACCESSTOKEN_2 = "dummy-access-token2";

        DEVICE_ID_1 = "fcfb988f-e051-25f8-8bad-f071720e1819";
        NODE_ID = "0001";

        DEVICE_ID_2 = "fcfb988f-e051-25f8-8bad-f071720e1820";
        DEVICE_ID_3 = "fcfb988f-e051-25f8-8bad-f071720e1821";
    }

    public void setConstants(String BROKER, String CLIENT_ID, String ID_SOURCE_ENDPOINT, String ID_DESTINATION_ENDPOINT) {

        this.BROKER = BROKER;
        this.CLIENT_ID = CLIENT_ID;

        VERSION = "1.0/";
        this.ID_SOURCE_ENDPOINT = ID_SOURCE_ENDPOINT;
        this.ID_DESTINATION_ENDPOINT = ID_DESTINATION_ENDPOINT;
        TOPIC_SUBSCRIBE = "1.0/" + ID_DESTINATION_ENDPOINT + "/management";
        TOPIC_PUBLISH = "1.0/" + ID_SOURCE_ENDPOINT + "/management";
        API_GET_SECURITY_OBJECT = "GetSecurityObject";
    }

    public static Constants getInstance() {
        if (instance == null) {
            instance = new Constants();
        }
        return instance;
    }

    public String[] getDevicesWithAccesToken(String accessToken) {
        if ( accessToken.equals(ACCESSTOKEN_1)) {
            String[] devices = new String[2];
            devices[0] = DEVICE_ID_1;
            devices[1] = DEVICE_ID_2;

            return devices;
        }
        else if(accessToken.equals(ACCESSTOKEN_2)){
            String[] devices = new String[2];
            devices[0] = DEVICE_ID_2;
            devices[1] = DEVICE_ID_3;
            return  devices;
        }
        return  null ;
    }
}
