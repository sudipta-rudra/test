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

public class AppSecurityObjectResponse extends BaseMqttMessageResponse {
    public Payload Payload = new Payload();

    public static class Body {
        public String AppInstanceID = "VGhpcyBpcyBOSUNFIEFQUCBTQVJEJ3MgQXBwSU5TVEFOQ0UgSUQ=";
        public String Version = "1.0";
        public String NICELARootCertificate = "hhh03_NICELARootCertificate";
        public String SecurityLevel = "ServerOnly";
        public String AppInstanceCertificate = "TlRZX1kKgrB2V3SHORHcCw==";
        public String AppID;
        public String AppDeveloperID;
        private PrivateKey AppInstancePrivateKey = new PrivateKey();
    }

    public static class PrivateKey {
        public String EncryptedKey = "";
        public String EncryptionKeyID = "";
    }

    public static class Payload {
        public Body Body = new Body();
    }
}
