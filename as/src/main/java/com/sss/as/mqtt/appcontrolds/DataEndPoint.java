package com.sss.as.mqtt.appcontrolds;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEndPoint {

    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("EndPointURI")
    @Expose
    private String endPointURI;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("Protocol")
    @Expose
    private Set<Protocol> protocol = new LinkedHashSet<Protocol>();
    @SerializedName("MQTTQoS")
    @Expose
    private MQTTQoS mQTTQoS;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("EndPointID")
    @Expose
    private String endPointID;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("AccessToken")
    @Expose
    private AccessToken accessToken;
    /**
     * If true then the Data Objects transfered over this channel shall be encrypted and authenticated. The App requires an App Instance Security Object with the appropriate key and certificate.
     * (Required)
     * 
     */
    @SerializedName("EncryptionEnforced")
    @Expose
    private Boolean encryptionEnforced;
    /**
     * If encryption is on then the public key in this certificate shall be used to encrypt traffic and validate data objects. 
     * 
     */
    @SerializedName("EndPointCertificate")
    @Expose
    private String endPointCertificate;
    /**
     * 
     * (Required)
     * 
     */
    @SerializedName("DataType")
    @Expose
    private Set<DataType> dataType = new LinkedHashSet<DataType>();

    /**
     * 
     * (Required)
     * 
     */
    public String getEndPointURI() {
        return endPointURI;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setEndPointURI(String endPointURI) {
        this.endPointURI = endPointURI;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Set<Protocol> getProtocol() {
        return protocol;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setProtocol(Set<Protocol> protocol) {
        this.protocol = protocol;
    }

    public MQTTQoS getMQTTQoS() {
        return mQTTQoS;
    }

    public void setMQTTQoS(MQTTQoS mQTTQoS) {
        this.mQTTQoS = mQTTQoS;
    }

    /**
     * 
     * (Required)
     * 
     */
    public String getEndPointID() {
        return endPointID;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setEndPointID(String endPointID) {
        this.endPointID = endPointID;
    }

    /**
     * 
     * (Required)
     * 
     */
    public AccessToken getAccessToken() {
        return accessToken;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * If true then the Data Objects transfered over this channel shall be encrypted and authenticated. The App requires an App Instance Security Object with the appropriate key and certificate.
     * (Required)
     * 
     */
    public Boolean getEncryptionEnforced() {
        return encryptionEnforced;
    }

    /**
     * If true then the Data Objects transfered over this channel shall be encrypted and authenticated. The App requires an App Instance Security Object with the appropriate key and certificate.
     * (Required)
     * 
     */
    public void setEncryptionEnforced(Boolean encryptionEnforced) {
        this.encryptionEnforced = encryptionEnforced;
    }

    /**
     * If encryption is on then the public key in this certificate shall be used to encrypt traffic and validate data objects. 
     * 
     */
    public String getEndPointCertificate() {
        return endPointCertificate;
    }

    /**
     * If encryption is on then the public key in this certificate shall be used to encrypt traffic and validate data objects. 
     * 
     */
    public void setEndPointCertificate(String endPointCertificate) {
        this.endPointCertificate = endPointCertificate;
    }

    /**
     * 
     * (Required)
     * 
     */
    public Set<DataType> getDataType() {
        return dataType;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setDataType(Set<DataType> dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DataEndPoint.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("endPointURI");
        sb.append('=');
        sb.append(((this.endPointURI == null)?"<null>":this.endPointURI));
        sb.append(',');
        sb.append("protocol");
        sb.append('=');
        sb.append(((this.protocol == null)?"<null>":this.protocol));
        sb.append(',');
        sb.append("mQTTQoS");
        sb.append('=');
        sb.append(((this.mQTTQoS == null)?"<null>":this.mQTTQoS));
        sb.append(',');
        sb.append("endPointID");
        sb.append('=');
        sb.append(((this.endPointID == null)?"<null>":this.endPointID));
        sb.append(',');
        sb.append("accessToken");
        sb.append('=');
        sb.append(((this.accessToken == null)?"<null>":this.accessToken));
        sb.append(',');
        sb.append("encryptionEnforced");
        sb.append('=');
        sb.append(((this.encryptionEnforced == null)?"<null>":this.encryptionEnforced));
        sb.append(',');
        sb.append("endPointCertificate");
        sb.append('=');
        sb.append(((this.endPointCertificate == null)?"<null>":this.endPointCertificate));
        sb.append(',');
        sb.append("dataType");
        sb.append('=');
        sb.append(((this.dataType == null)?"<null>":this.dataType));
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
        result = ((result* 31)+((this.endPointURI == null)? 0 :this.endPointURI.hashCode()));
        result = ((result* 31)+((this.protocol == null)? 0 :this.protocol.hashCode()));
        result = ((result* 31)+((this.endPointCertificate == null)? 0 :this.endPointCertificate.hashCode()));
        result = ((result* 31)+((this.mQTTQoS == null)? 0 :this.mQTTQoS.hashCode()));
        result = ((result* 31)+((this.dataType == null)? 0 :this.dataType.hashCode()));
        result = ((result* 31)+((this.accessToken == null)? 0 :this.accessToken.hashCode()));
        result = ((result* 31)+((this.endPointID == null)? 0 :this.endPointID.hashCode()));
        result = ((result* 31)+((this.encryptionEnforced == null)? 0 :this.encryptionEnforced.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DataEndPoint) == false) {
            return false;
        }
        DataEndPoint rhs = ((DataEndPoint) other);
        return (((((((((this.endPointURI == rhs.endPointURI)||((this.endPointURI!= null)&&this.endPointURI.equals(rhs.endPointURI)))&&((this.protocol == rhs.protocol)||((this.protocol!= null)&&this.protocol.equals(rhs.protocol))))&&((this.endPointCertificate == rhs.endPointCertificate)||((this.endPointCertificate!= null)&&this.endPointCertificate.equals(rhs.endPointCertificate))))&&((this.mQTTQoS == rhs.mQTTQoS)||((this.mQTTQoS!= null)&&this.mQTTQoS.equals(rhs.mQTTQoS))))&&((this.dataType == rhs.dataType)||((this.dataType!= null)&&this.dataType.equals(rhs.dataType))))&&((this.accessToken == rhs.accessToken)||((this.accessToken!= null)&&this.accessToken.equals(rhs.accessToken))))&&((this.endPointID == rhs.endPointID)||((this.endPointID!= null)&&this.endPointID.equals(rhs.endPointID))))&&((this.encryptionEnforced == rhs.encryptionEnforced)||((this.encryptionEnforced!= null)&&this.encryptionEnforced.equals(rhs.encryptionEnforced))));
    }

    public enum MQTTQoS {

        @SerializedName("0")
        _0(0),
        @SerializedName("1")
        _1(1),
        @SerializedName("2")
        _2(2);
        private final Integer value;
        private final static Map<Integer, MQTTQoS> CONSTANTS = new HashMap<Integer, MQTTQoS>();

        static {
            for (MQTTQoS c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private MQTTQoS(Integer value) {
            this.value = value;
        }

        public Integer value() {
            return this.value;
        }

        public static MQTTQoS fromValue(Integer value) {
            MQTTQoS constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException((value +""));
            } else {
                return constant;
            }
        }

    }

}
