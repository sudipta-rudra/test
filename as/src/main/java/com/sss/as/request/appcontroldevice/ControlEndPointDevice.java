package com.sss.as.request.appcontroldevice;

import com.google.gson.annotations.SerializedName;

public class ControlEndPointDevice {

    @SerializedName("EndPointURI")
    private String endPointURI;

    @SerializedName("Protocol")
    private Protocol protocol;

    @SerializedName("MQTTQoS")
    private int mQTTQoS ;

    @SerializedName("EndPointID")
    private String endPointID;

    @SerializedName("AccessToken")
    private AccessToken accessToken;
    /**
     * If true then the Control Objects transfered over this channel shall be encrypted and authenticated. The App requires an App Instance Security Object with the appropriate key and certificate.
     * (Required)
     */
   /* @SerializedName("EncryptionEnforced")
    private boolean encryptionEnforced;*/

    @SerializedName("EnforceEncryption")
    private boolean enforceEncryption;

    @SerializedName("EndPointCertificate")
    private String endPointCertificate;

    @SerializedName("Topic")
    private Topic topic;

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public int getmQTTQoS() {
        return mQTTQoS;
    }

    public void setmQTTQoS(int mQTTQoS) {
        this.mQTTQoS = mQTTQoS;
    }

    public boolean isEnforceEncryption() {
        return enforceEncryption;
    }

    public void setEnforceEncryption(boolean enforceEncryption) {
        this.enforceEncryption = enforceEncryption;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getEndPointURI() {
        return endPointURI;
    }

    public void setEndPointURI(String endPointURI) {
        this.endPointURI = endPointURI;
    }


    public String getEndPointID() {
        return endPointID;
    }

    public void setEndPointID(String endPointID) {
        this.endPointID = endPointID;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }


    public String getEndPointCertificate() {
        return endPointCertificate;
    }

    public void setEndPointCertificate(String endPointCertificate) {
        this.endPointCertificate = endPointCertificate;
    }


    public ControlEndPointDevice() {
        this.endPointURI = "mqtt://localhost:8883/";
        this.protocol = Protocol.MQTT;
        this.mQTTQoS = 0;
        this.enforceEncryption = false;
        this.endPointCertificate = "HCJxjLaGRWbSpJeY5dBGabM8V5eQz_Nc4YxAzcz6";
        this.topic = new Topic();
    }

    public static class Topic {
        public PubSub Subscribe;
        public PubSub Publish;

        public Topic() {
            Subscribe = new PubSub("1.0/fcfb988f-e051-25f8-8bad-f071720e1819/control/0001", 0);
            Publish = new PubSub("1.0/bddea2bf-This_IDis_N_AS-fc6a6ded63cd/control/0001", 0);
        }

        public PubSub getSubscribe() {
            return Subscribe;
        }

        public void setSubscribe(PubSub subscribe) {
            Subscribe = subscribe;
        }

        public PubSub getPublish() {
            return Publish;
        }

        public void setPublish(PubSub publish) {
            Publish = publish;
        }
    }

    public static class PubSub {
        public String Name;
        public int QoS;

        public PubSub(String name, int qoS) {
            Name = name;
            QoS = qoS;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public int getQoS() {
            return QoS;
        }

        public void setQoS(int qoS) {
            QoS = qoS;
        }
    }
}
